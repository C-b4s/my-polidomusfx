package ec.edu.epn.mypolidomus.BusinessLogic.Sistema;

/**
 * Control del seguro de la puerta (servo) vinculado al Arduino.
 * Alineado con {@code arduino/code.cc}: servo 0° = abrir, 90° = cerrar.
 * <p>
 * {@link #abrir()} envía OPEN al Arduino: abre (0°), espera unos segundos, cierra (90°).
 * {@link #cerrar()} envía CLOSE: cierra (90°).
 */
public class SeguroPuerta {

    private final ArduinoConector arduino;
    private EstadoPuerta estado = EstadoPuerta.CERRADA;

    public enum EstadoPuerta {
        ABIERTA,
        CERRADA,
        DESCONOCIDO
    }

    public SeguroPuerta(ArduinoConector arduino) {
        this.arduino = arduino;
    }

    /**
     * Abre el seguro (servo 0°) y luego el Arduino lo cierra tras un tiempo.
     */
    public void abrir() {
        if (arduino != null && arduino.estaConectado()) {
            arduino.enviarAbrirPuerta();
            estado = EstadoPuerta.ABIERTA;
        }
    }

    /**
     * Cierra el seguro (servo 90°).
     */
    public void cerrar() {
        if (arduino != null && arduino.estaConectado()) {
            arduino.enviarCerrarPuerta();
            estado = EstadoPuerta.CERRADA;
        }
    }

    public EstadoPuerta getEstado() {
        return estado;
    }

    public void setEstado(EstadoPuerta estado) {
        this.estado = estado;
    }
}
