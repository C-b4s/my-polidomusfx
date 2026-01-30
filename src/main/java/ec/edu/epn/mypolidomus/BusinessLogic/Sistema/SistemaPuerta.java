package ec.edu.epn.mypolidomus.BusinessLogic.Sistema;

import ec.edu.epn.mypolidomus.DataAccess.DAOs.UsuarioClienteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

/**
 * Coordinador del sistema de puerta segura con Arduino.
 * Integra {@link SensorProximidad} (ultrasónico/PIR), {@link TecladoMatricialHandler},
 * {@link ArduinoConector} y {@link SeguroPuerta}, alineado con {@code arduino/code.cc}.
 * <p>
 * Uso:
 * <pre>
 * SistemaPuerta sistema = new SistemaPuerta();
 * sistema.inicializar();  // puede lanzar AppException
 * sistema.conectar("COM3");
 * sistema.getSeguro().abrir();  // opcional, manual
 * // Desconectar al salir: sistema.desconectar();
 * </pre>
 */
public class SistemaPuerta {

    private SensorProximidad sensorProximidad;
    private TecladoMatricialHandler tecladoHandler;
    private ArduinoConector arduinoConector;
    private SeguroPuerta seguroPuerta;
    private boolean inicializado;

    public SistemaPuerta() {
        this.inicializado = false;
    }

    /**
     * Crea sensor, teclado, conector y seguro, y enlaza listeners.
     *
     * @throws AppException si falla la creación del DAO (p. ej. BD).
     */
    public void inicializar() throws AppException {
        if (inicializado) return;

        UsuarioClienteDAO usuarioDAO = new UsuarioClienteDAO();
        this.sensorProximidad = new SensorProximidad();
        this.tecladoHandler = new TecladoMatricialHandler(usuarioDAO);
        this.arduinoConector = new ArduinoConector(sensorProximidad, tecladoHandler);
        this.seguroPuerta = new SeguroPuerta(arduinoConector);

        this.inicializado = true;
    }

    /**
     * Abre el puerto Serial al Arduino/ESP32.
     *
     * @param nombrePuerto Ej. "COM3", "/dev/ttyUSB0".
     * @return true si la conexión se abrió correctamente.
     */
    public boolean conectar(String nombrePuerto) {
        if (!inicializado) return false;
        return arduinoConector.conectar(nombrePuerto);
    }

    public void desconectar() {
        if (arduinoConector != null) {
            arduinoConector.desconectar();
        }
    }

    public boolean estaConectado() {
        return arduinoConector != null && arduinoConector.estaConectado();
    }

    public SensorProximidad getSensorProximidad() {
        return sensorProximidad;
    }

    public TecladoMatricialHandler getTecladoHandler() {
        return tecladoHandler;
    }

    public ArduinoConector getArduinoConector() {
        return arduinoConector;
    }

    public SeguroPuerta getSeguroPuerta() {
        return seguroPuerta;
    }

    public boolean isInicializado() {
        return inicializado;
    }
}
