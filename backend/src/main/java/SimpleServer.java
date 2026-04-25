import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new MyHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server started at http://localhost:8080");
    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String path = exchange.getRequestURI().getPath();

            if (path.equals("/")) {
                sendFile(exchange, "index.html", "text/html");
            }
            else if (path.equals("/dashboard")) {
                sendFile(exchange, "dashboard.html", "text/html");
            }
            else if (path.equals("/place")) {
                sendFile(exchange, "place.html", "text/html");
            }
            else if (path.equals("/track")) {
                sendFile(exchange, "track.html", "text/html");
            }
            else if (path.equals("/style.css")) {
                sendFile(exchange, "style.css", "text/css");
            }
            else if (path.equals("/script.js")) {
                sendFile(exchange, "script.js", "application/javascript");
            }
            else {
                String notFound = "<h1>404 Page Not Found</h1>";
                byte[] bytes = notFound.getBytes(StandardCharsets.UTF_8);

                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(404, bytes.length);

                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.close();
            }
        }

        public void sendFile(HttpExchange exchange, String fileName, String type) throws IOException {

            File file = new File(fileName);

            if (!file.exists()) {
                String msg = "<h1>File Not Found</h1>";
                byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);

                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(404, bytes.length);

                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.close();
                return;
            }

            byte[] bytes = Files.readAllBytes(Paths.get(fileName));

            exchange.getResponseHeaders().set("Content-Type", type + "; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }
}