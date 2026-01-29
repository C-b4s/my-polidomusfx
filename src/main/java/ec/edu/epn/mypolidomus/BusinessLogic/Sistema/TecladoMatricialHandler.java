package ec.edu.epn.mypolidomus.BusinessLogic.Sistema;

import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class TecladoMatricialHandler {

    private StringBuilder claveIngresada = new StringBuilder();
    private boolean esperandoClave = false;

    private final UsuarioClienteDAO usuarioDAO;

    // Usuario para verificar (puede venir de configuración o login previo)
    private final String usuarioActual;

    public TecladoMatricialHandler(UsuarioClienteDAO usuarioDAO, String usuarioActual) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioActual = usuarioActual;
    }

    /**
     * Inicia la captura de la clave tras detectar movimiento.
     */
    public void activarIngresoClave() {
        claveIngresada.setLength(0);
        esperandoClave = true;
        // Aquí se puede integrar con LCD para mostrar "Ingrese clave"
        System.out.println("MOVIMIENTO DETECTADO: Esperando clave...");
    }

    /**
     * Maneja la tecla presionada en el teclado matricial.
     * @param tecla La tecla ingresada ('0'-'9', '*', '#')
     */
    public void manejarTecla(char tecla) {
        if (!esperandoClave) return;

        switch (tecla) {
            case '*': // Reiniciar intento
                claveIngresada.setLength(0);
                System.out.println("Clave borrada.");
                break;

            case '#': // Verificar clave
                verificarClave();
                break;

            default: // Construir la clave
                if (claveIngresada.length() < 4) {
                    claveIngresada.append(tecla);
                    System.out.println("Clave parcial: " + "*".repeat(claveIngresada.length()));
                }
                break;
        }
    }

    /**
     * Verifica la clave ingresada contra la base de datos.
     */
    private void verificarClave() {
        try {
            UsuarioClienteDTO usuario = usuarioDAO.readByUsuario(usuarioActual);

            if (usuario != null && usuario.getContrasena().equals(claveIngresada.toString())) {
                accesoConcedido();
            } else {
                accesoDenegado();
            }

        } catch (AppException e) {
            System.err.println("Error al acceder a la base de datos: " + e.getMessage());
            accesoDenegado();
        }
    }

    /**
     * Acciones a realizar si la clave es correcta.
     */
    private void accesoConcedido() {
        esperandoClave = false;
        claveIngresada.setLength(0);

        System.out.println("CLAVE CORRECTA: ACCESO CONCEDIDO");
        // TODO: Integrar con servo, LCD y desactivar alarma
    }

    /**
     * Acciones a realizar si la clave es incorrecta.
     */
    private void accesoDenegado() {
        claveIngresada.setLength(0);
        System.out.println("CLAVE INCORRECTA: CASA EN PELIGRO");
        // TODO: Activar alarma, buzzer, notificaciones y LCD
    }

    /**
     * Reinicia el sistema para un nuevo intento.
     */
    public void reiniciarSistema() {
        esperandoClave = false;
        claveIngresada.setLength(0);
        System.out.println("Sistema listo para nuevo intento.");
        // TODO: Resetear LCD y buzzer
    }
}
