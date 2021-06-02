package Handler;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

import RequestResult.*;
import Service.ClearService;
import Service.LoadService;
import Service.LoginService;
import Service.RegisterService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

public class RegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                RegisterService registerService = new RegisterService();

                InputStream is = exchange.getRequestBody();
                String json = readString(is);
                Gson gson = new Gson();
                RegisterRequest request = gson.fromJson(json, RegisterRequest.class);
                RegisterResult result = registerService.register(request);

                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }

                String myString = gson.toJson(result);
                OutputStream os = exchange.getResponseBody();
                writeString(myString, os);
                os.close();
            }
        }
        catch (IOException | SQLException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            String message = "Error: Internal server error.";
            Gson gson = new Gson();
            LoadResult result = new LoadResult(message, false);
            String json = gson.toJson(result.getMessage());
            OutputStream os = exchange.getResponseBody();
            writeString(json, os);
            os.close();
        }
    }

    private String readString(InputStream reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(reader);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private void writeString(String myString, OutputStream os) throws  IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
        outputStreamWriter.write(myString);
        outputStreamWriter.flush();
    }
}