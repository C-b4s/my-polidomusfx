package ec.edu.epn.mypolidomus.Infrastructure;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Escribe y lee el archivo HistorialAccesos.txt con líneas
 * "<FechaYHora> ACCESO CORRECTO" o "<FechaYHora> ACCESO DENEGADO".
 */
public final class HistorialAccesosService {

    private static final DateTimeFormatter FECHA_HORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private HistorialAccesosService() {}

    /**
     * Registra un intento de acceso en el archivo HistorialAccesos.txt.
     */
    public static void registrarAcceso(boolean accesoCorrecto) {
        String path = AppConfig.getHistorialAccesosPath();
        if (path == null || path.isBlank()) return;

        String fechaHora = LocalDateTime.now().format(FECHA_HORA);
        String linea = accesoCorrecto
            ? fechaHora + " ACCESO CORRECTO"
            : fechaHora + " ACCESO DENEGADO";

        Path file = Paths.get(path);
        try {
            Files.createDirectories(file.getParent());
            Files.write(file, (linea + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            ec.edu.epn.mypolidomus.Infrastructure.Tools.CMD.printlnError(
                "HistorialAccesosService: no se pudo escribir: " + e.getMessage());
        }
    }

    /**
     * Lee todas las líneas del historial (para mostrar en la vista).
     */
    public static List<String> leerLineas() {
        String path = AppConfig.getHistorialAccesosPath();
        if (path == null || path.isBlank()) return new ArrayList<>();

        Path file = Paths.get(path);
        if (!Files.isRegularFile(file)) return new ArrayList<>();

        try {
            return Files.readAllLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            ec.edu.epn.mypolidomus.Infrastructure.Tools.CMD.printlnError(
                "HistorialAccesosService: no se pudo leer: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
