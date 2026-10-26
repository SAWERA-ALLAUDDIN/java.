import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * A small website served with nothing but the JDK.
 *
 *   Static pages   -> everything in the "public" folder
 *   GET  /api/time -> JSON with the server time
 *   POST /send-message -> saves the form to messages.txt, then redirects to /thanks.html
 */
public class Main {

    private static final Path PUBLIC_DIR = Path.of("public").toAbsolutePath().normalize();
    private static final Path MESSAGES_FILE = Path.of("messages.txt");

    private static final Map<String, String> MIME = Map.of(
            "html", "text/html; charset=utf-8",
            "css", "text/css; charset=utf-8",
            "js", "text/javascript; charset=utf-8",
            "json", "application/json; charset=utf-8",
            "svg", "image/svg+xml",
            "png", "image/png",
            "jpg", "image/jpeg",
            "ico", "image/x-icon"
    );
