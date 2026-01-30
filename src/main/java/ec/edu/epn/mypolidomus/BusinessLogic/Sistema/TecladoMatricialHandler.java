package ec.edu.epn.mypolidomus.BusinessLogic.Sistema;

import ec.edu.epn.mypolidomus.DataAccess.DAOs.UsuarioClienteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

/**
 * Maneja el teclado matricial 4×4 para verificación de PIN en sistema de seguridad con Arduino.
 * Alineado con {@code arduino/code.cc}: teclas 0–9, *, #; PIN de 3–4 dígitos; * borra, # verifica.
 * Compatible con protocolo Serial: Arduino envía {@code K&lt;tecla&gt;} (ej. K1, K*, K#).
 */
public class TecladoMatricialHandler {

    /** Longitud máxima del PIN (Arduino: 4; claveCorrecta puede ser 3). */
    public static final int LONGITUD_PIN_MAX = 4;

    /** Longitud mínima para aceptar verificación (ej. 3). */
    public static final int LONGITUD_PIN_MIN = 3;

    /** Prefijo Serial para tecla: {@code K} + tecla. */
    public static final String PREFIJO_TECLA = "K";

    private final StringBuilder claveIngresada = new StringBuilder();
    private boolean esperandoClave = false;

    private final UsuarioClienteDAO usuarioDAO;
    private AccesoResultadoListener accesoResultadoListener;

    public TecladoMatricialHandler(UsuarioClienteDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public interface AccesoResultadoListener {
        void onAccesoConcedido();
        void onAccesoDenegado();
    }

    public void setAccesoResultadoListener(AccesoResultadoListener listener) {
        this.accesoResultadoListener = listener;
    }

    /**
     * Inicia la captura de clave (p. ej. tras «persona cerca» o movimiento).
     */
    public void activarIngresoClave() {
        claveIngresada.setLength(0);
        esperandoClave = true;
        System.out.println("PROXIMIDAD/MOVIMIENTO: Esperando clave...");
    }

    /**
     * Procesa una línea {@code K&lt;tecla&gt;} del Arduino. Acepta 0–9, *, #, A–D (A–D se ignoran al construir PIN).
     */
    public boolean procesarLineaTecla(String linea) {
        if (linea == null) return false;
        String t = linea.trim();
        if (!t.toUpperCase().startsWith(PREFIJO_TECLA) || t.length() != 2) return false;
        manejarTecla(t.charAt(1));
        return true;
    }

    /**
     * Maneja una tecla: * borra, # verifica, 0–9 construyen el PIN (máx. {@value #LONGITUD_PIN_MAX}). A–D se ignoran.
     */
    public void manejarTecla(char tecla) {
        if (!esperandoClave) return;

        switch (tecla) {
            case '*':
                claveIngresada.setLength(0);
                System.out.println("Clave borrada.");
                break;
            case '#':
                verificarClave();
                break;
            default:
                if (tecla >= '0' && tecla <= '9' && claveIngresada.length() < LONGITUD_PIN_MAX) {
                    claveIngresada.append(tecla);
                    System.out.println("Clave parcial: " + "*".repeat(claveIngresada.length()));
                }
                break;
        }
    }

    private void verificarClave() {
        String clave = claveIngresada.toString();
        if (clave.length() < LONGITUD_PIN_MIN) {
            System.out.println("Clave demasiado corta. Mínimo " + LONGITUD_PIN_MIN + " dígitos.");
            accesoDenegado();
            return;
        }

        try {
            if (usuarioDAO.validarClavePropietario(clave)) {
                accesoConcedido();
            } else {
                accesoDenegado();
            }
        } catch (AppException e) {
            System.err.println("Error al acceder a la base de datos: " + e.getMessage());
            accesoDenegado();
        }
    }

    private void accesoConcedido() {
        esperandoClave = false;
        claveIngresada.setLength(0);
        System.out.println("CLAVE CORRECTA: ACCESO CONCEDIDO");
        if (accesoResultadoListener != null) {
            accesoResultadoListener.onAccesoConcedido();
        }
    }

    private void accesoDenegado() {
        claveIngresada.setLength(0);
        System.out.println("CLAVE INCORRECTA");
        if (accesoResultadoListener != null) {
            accesoResultadoListener.onAccesoDenegado();
        }
    }

    public void reiniciarSistema() {
        esperandoClave = false;
        claveIngresada.setLength(0);
        System.out.println("Sistema listo para nuevo intento.");
    }

    public boolean isEsperandoClave() {
        return esperandoClave;
    }
}
