package ec.edu.epn.mypolidomus.Infrastructure;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ec.edu.epn.mypolidomus.Infrastructure.Tools.CMDColor;

public class AppException extends Exception {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AppException(String showMsg) {
        super(valid(showMsg));
        saveLogFile(null, null, null);
    }

    public AppException(String showMsg, Exception e, Class<?> clase, String metodo) {
        super(valid(showMsg));
        saveLogFile(e == null ? null : e.getMessage(), clase, metodo);
    }

    /* =========================
       LOGGING SEGURO
       ========================= */
    private void saveLogFile(String logMsg, Class<?> clase, String metodo) {

        String timestamp  = LocalDateTime.now().format(FORMATTER);
        String className  = clase  == null ? AppConfig.MSG_DEFAULT_CLASS  : clase.getSimpleName();
        String methodName = metodo == null ? AppConfig.MSG_DEFAULT_METHOD : metodo;

        logMsg = (logMsg == null || logMsg.isBlank())
                ? AppConfig.MSG_DEFAULT_ERROR
                : logMsg;

        String finalMsg = String.format(
                "╭─💀─ SHOW ❱❱ %s%n╰──── LOG  ❱❱ %s | %s.%s | %s",
                getMessage(), timestamp, className, methodName, logMsg
        );

        try {
            Path logPath = resolveLogPath();
            Files.createDirectories(logPath.getParent());

            try (PrintWriter writer =
                         new PrintWriter(Files.newBufferedWriter(logPath))) {
                writer.println(finalMsg);
            }

            System.err.println(CMDColor.BLUE + finalMsg);

        } catch (IOException e) {
            System.err.println(CMDColor.RED +
                    "[AppException.saveLogFile] ❱ " + e.getMessage());
        } finally {
            System.out.print(CMDColor.RESET);
        }
    }

    private static Path resolveLogPath() {
        String relative = AppConfig.getLOGFILE(); // ej: .mypolidomus/logs/app.log
        return Paths.get(System.getProperty("user.home")).resolve(relative);
    }

    private static String valid(String msg) {
        return (msg == null || msg.isBlank())
                ? AppConfig.MSG_DEFAULT_ERROR
                : msg;
    }
}
