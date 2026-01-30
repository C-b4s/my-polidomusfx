package ec.edu.epn.mypolidomus.BusinessLogic.Sistema;

import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Conecta la aplicación Java con el Arduino/ESP32 por Serial (USB).
 */
public class ArduinoConector {

    public static final int BAUDRATE_DEFAULT = 115200;

    public static final String CMD_OPEN = "OPEN";
    public static final String CMD_CLOSE = "CLOSE";
    public static final String CMD_DENY = "DENY";
    public static final String CMD_BUZZER_ON = "BUZZER_ON";
    public static final String CMD_BUZZER_OFF = "BUZZER_OFF";

    private final SensorProximidad sensorProximidad;
    private final TecladoMatricialHandler tecladoHandler;

    private SerialPort port;
    private OutputStream output;
    private Thread readerThread;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public ArduinoConector(SensorProximidad sensorProximidad, TecladoMatricialHandler tecladoHandler) {
        this.sensorProximidad = sensorProximidad;
        this.tecladoHandler = tecladoHandler;
        configurarListeners();
    }

    private void configurarListeners() {
        // Callback para proximidad → activar ingreso de clave
        sensorProximidad.setListener(() -> Platform.runLater(tecladoHandler::activarIngresoClave));

        // Callbacks del teclado
        tecladoHandler.setAccesoResultadoListener(new TecladoMatricialHandler.AccesoResultadoListener() {
            @Override
            public void onAccesoConcedido() {
                Platform.runLater(() -> {
                    enviarBuzzerOff();
                    enviarAbrirPuerta();
                    tecladoHandler.reiniciarSistema();
                });
            }

            @Override
            public void onAccesoDenegado() {
                Platform.runLater(ArduinoConector.this::enviarDenegado);
            }
        });
    }

    /** Conecta al puerto Serial con un baud rate específico */
    public boolean conectar(String nombrePuerto, int baudRate) {
        if (running.get()) desconectar();

        port = SerialPort.getCommPort(nombrePuerto);
        port.setBaudRate(baudRate);
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 200, 0);

        if (!port.openPort()) {
            System.err.println("No se pudo abrir el puerto: " + nombrePuerto);
            return false;
        }

        output = port.getOutputStream();
        running.set(true);

        // Inicia el hilo que lee Serial
        readerThread = new Thread(this::leerSerial, "ArduinoConector-Reader");
        readerThread.setDaemon(true);
        readerThread.start();

        System.out.println("Conectado al puerto: " + nombrePuerto);
        return true;
    }

    /** Conecta usando el baud rate por defecto */
    public boolean conectar(String nombrePuerto) {
        return conectar(nombrePuerto, BAUDRATE_DEFAULT);
    }

    /** Desconecta y cierra el puerto Serial */
    public void desconectar() {
        running.set(false);
        if (readerThread != null) {
            readerThread.interrupt();
            try {
                readerThread.join(2000);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
            readerThread = null;
        }

        if (output != null) {
            try { output.close(); } catch (IOException ignored) {}
            output = null;
        }

        if (port != null && port.isOpen()) {
            port.closePort();
            port = null;
        }
        System.out.println("Puerto desconectado.");
    }

    public boolean estaConectado() {
        return port != null && port.isOpen();
    }

    /** Envia una línea al Arduino con salto de línea */
    private void enviarLinea(String linea) {
        if (output == null || !estaConectado()) return;
        try {
            output.write((linea + "\n").getBytes(StandardCharsets.UTF_8));
            output.flush();
        } catch (IOException e) {
            System.err.println("Error al enviar '" + linea + "': " + e.getMessage());
        }
    }

    public void enviarAbrirPuerta() { enviarLinea(CMD_OPEN); }
    public void enviarCerrarPuerta() { enviarLinea(CMD_CLOSE); }
    public void enviarBuzzerOn() { enviarLinea(CMD_BUZZER_ON); }
    public void enviarBuzzerOff() { enviarLinea(CMD_BUZZER_OFF); }
    public void enviarDenegado() { enviarLinea(CMD_DENY); }

    /** Lee continuamente las líneas del Serial en un hilo separado */
    private void leerSerial() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(port.getInputStream(), StandardCharsets.UTF_8))) {

            String linea;
            while (running.get() && port != null && port.isOpen()) {
                try {
                    linea = reader.readLine();
                } catch (IOException e) {
                    if (running.get())
                        System.err.println("Error leyendo Serial: " + e.getMessage());
                    break;
                }

                if (linea == null) break;

                String t = linea.trim();
                if (t.isEmpty()) continue;

                if (SensorProximidad.esComandoProximidadOMovimiento(t)) {
                    Platform.runLater(() -> sensorProximidad.procesarLinea(t));
                } else if (tecladoHandler.procesarLineaTecla(t)) {
                    // ya procesado en handler
                } else {
                    System.out.println("Línea no reconocida: " + t);
                }
            }
        } catch (IOException e) {
            if (running.get()) System.err.println("Error en reader: " + e.getMessage());
        } finally {
            running.set(false);
        }
    }

    /** Lista todos los puertos Serial disponibles */
    public static SerialPort[] listarPuertos() {
        return SerialPort.getCommPorts();
    }
}
