package ec.edu.epn.mypolidomus.BusinessLogic.Sistema;

/**
 * Maneja eventos de proximidad conectados al Arduino.
 * <p>
 * Compatible con:
 * <ul>
 *   <li><b>Ultrasónico</b> (TRIG/ECHO, &lt; 20 cm): Arduino envía {@code P} o {@code NEAR}</li>
 *   <li><b>PIR</b> (movimiento): Arduino envía {@code M} o {@code MOV}</li>
 * </ul>
 * Cualquiera de estos dispara «persona cerca» → activar ingreso de clave.
 */
public class SensorProximidad {

    private MovimientoListener listener;

    /** Comandos ultrasonido (persona cerca, distancia &lt; umbral). */
    public static final String CMD_PROXIMIDAD = "P";
    public static final String CMD_PROXIMIDAD_ALT = "NEAR";

    /** Comandos PIR (movimiento). */
    public static final String CMD_MOVIMIENTO = "M";
    public static final String CMD_MOVIMIENTO_ALT = "MOV";

    public interface MovimientoListener {
        void onMovimientoDetectado();
    }

    public void setListener(MovimientoListener listener) {
        this.listener = listener;
    }

    /**
     * Procesa un carácter recibido (ej. 'P' o 'M').
     */
    public void procesarDato(char dato) {
        if (dato == 'P' || dato == 'M') {
            notificarMovimiento();
        }
    }

    /**
     * Procesa una línea del Arduino: {@code P}, {@code NEAR}, {@code M}, {@code MOV}.
     */
    public void procesarLinea(String linea) {
        if (linea == null) return;
        String u = linea.trim().toUpperCase();
        if (CMD_PROXIMIDAD.equals(u) || CMD_PROXIMIDAD_ALT.equals(u)
                || CMD_MOVIMIENTO.equals(u) || CMD_MOVIMIENTO_ALT.equals(u)) {
            notificarMovimiento();
        }
    }

    private void notificarMovimiento() {
        if (listener != null) {
            listener.onMovimientoDetectado();
        }
    }

    /**
     * Indica si la línea es un comando de proximidad o movimiento (P, NEAR, M, MOV).
     */
    public static boolean esComandoProximidadOMovimiento(String linea) {
        if (linea == null) return false;
        String u = linea.trim().toUpperCase();
        return CMD_PROXIMIDAD.equals(u) || CMD_PROXIMIDAD_ALT.equals(u)
                || CMD_MOVIMIENTO.equals(u) || CMD_MOVIMIENTO_ALT.equals(u);
    }
}
