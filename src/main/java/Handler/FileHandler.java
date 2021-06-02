package Handler;

import java.io.*;
        import java.net.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Locale;

import com.sun.net.httpserver.*;

public class FileHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {


        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                String url = exchange.getRequestURI().toString();

                if (url.length() == 1) {
                    String urlPath = new String("web/index.html");
                    Path filePath = FileSystems.getDefault().getPath(urlPath);

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    Files.copy(filePath, exchange.getResponseBody());

                    exchange.getResponseBody().close();
                }
                else {
                    String urlPath = "web" + url;
                    File file = new File(urlPath);
                    if (!file.exists()) {
                        urlPath = new String("web/HTML/404.html");
                        Path filePath = FileSystems.getDefault().getPath(urlPath);

                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);

                        Files.copy(filePath, exchange.getResponseBody());

                        exchange.getResponseBody().close();
                        return;
                    }

                    Path filePath = FileSystems.getDefault().getPath(urlPath);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    Files.copy(filePath, exchange.getResponseBody());
                    exchange.getResponseBody().close();
                }
                success = true;
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();

            e.printStackTrace();
        }
    }
}