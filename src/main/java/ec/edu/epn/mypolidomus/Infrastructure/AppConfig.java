package ec.edu.epn.mypolidomus.Infrastructure;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import ec.edu.epn.mypolidomus.Infrastructure.Tools.CMD;

public abstract class AppConfig {

    private static final Properties props = new Properties();

    private static final String APP_PROPERTIES =
            "ec/edu/epn/mypolidomus/app.properties";

    private static final String KEY_DB_NAME        = "db.File";
    private static final String KEY_FILE_LOG       = "df.logFile";
    private static final String KEY_FILE_DATA      = "df.AntCoor";
    private static final String KEY_FILE_ANTNEST   = "df.AntNest";
    private static final String KEY_FILE_ANTFOOD   = "df.AntFood";

    private static final String KEY_RES_IMG_MAIN   = "res.img.Main";
    private static final String KEY_RES_IMG_LOGO   = "res.img.Logo";
    private static final String KEY_RES_IMG_SPLASH = "res.img.Splash";

    /* =======================
       CARGA ESTÁTICA SEGURA
       ======================= */
    static {
        try (InputStream is =
    AppConfig.class.getResourceAsStream("/ec/edu/epn/mypolidomus/app.properties");
) {

            if (is == null)
                throw new RuntimeException("No se encontró app.properties");

            props.load(is);
            CMD.println("AppConfig ❱❱ app.properties cargado correctamente");

        } catch (Exception e) {
            CMD.printlnError("ERROR AppConfig ❱❱ " + e.getMessage());
        }
    }

    private AppConfig() {}

    /* =======================
       GETTERS DE CONFIG
       ======================= */
    public static String getDATABASE() { return get(KEY_DB_NAME); }
    public static String getLOGFILE()  { return get(KEY_FILE_LOG); }
    public static String getDATAFILE() { return get(KEY_FILE_DATA); }
    public static String getANTFOOD()  { return get(KEY_FILE_ANTFOOD); }
    public static String getANTNEST()  { return get(KEY_FILE_ANTNEST); }

    /* =======================
       RECURSOS
       ======================= */
    public static URL getImgMain()   { return getResource(KEY_RES_IMG_MAIN); }
    public static URL getImgLogo()   { return getResource(KEY_RES_IMG_LOGO); }
    public static URL getImgSplash() { return getResource(KEY_RES_IMG_SPLASH); }

    /* =======================
       INTERNOS
       ======================= */
    private static String get(String key) {
        String value = props.getProperty(key);
        if (value == null)
            CMD.printlnError("AppConfig ❱❱ clave no encontrada: " + key);
        return value;
    }

    private static URL getResource(String key) {
        String path = get(key);
        if (path == null) return null;
        
        try {
            // Intentar primero con el ClassLoader del thread actual
            URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
            
            // Si no funciona, intentar con AppConfig.class.getResource
            if (resource == null) {
                // Agregar / al inicio si no lo tiene
                if (!path.startsWith("/")) {
                    resource = AppConfig.class.getResource("/" + path);
                } else {
                    resource = AppConfig.class.getResource(path);
                }
            }
            
            if (resource == null) {
                CMD.printlnError("AppConfig ❱❱ Recurso no encontrado: " + path);
            } else {
                CMD.println("AppConfig ❱❱ Recurso cargado: " + path);
            }
            return resource;
        } catch (Exception e) {
            CMD.printlnError("AppConfig ❱❱ Error al cargar recurso " + path + ": " + e.getMessage());
            return null;
        }
    }

    /* =======================
       MENSAJES
       ======================= */
    public static final String MSG_DEFAULT_ERROR  =
            "Ups! Error inesperado. Por favor, contacte al administrador del sistema.";
    
    public static final String MSG_DEFAULT_CLASS  =
            "Ups! Error inesperado. Por favor, contacte al administrador del sistema.";
    
    public static final String MSG_DEFAULT_METHOD  =
            "Ups! Error inesperado. Por favor, contacte al administrador del sistema.";
    }
