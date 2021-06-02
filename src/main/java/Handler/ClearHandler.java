package Handler;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

import RequestResult.ClearRequest;
import RequestResult.ClearResult;
import Service.ClearService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

public class ClearHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {


        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                ClearService clearService = new ClearService();
                ClearRequest request = new ClearRequest();
                ClearResult result = clearService.clear(request);

                Gson gson = new Gson();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream response = exchange.getResponseBody();
                String json = gson.toJson(result);
                writeString(json, response);

                response.close();

                success = true;
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        }
        catch (IOException | SQLException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();

            e.printStackTrace();
        }
    }

    private void writeString(String myString, OutputStream os) throws  IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
        outputStreamWriter.write(myString);
        outputStreamWriter.flush();
    }
}