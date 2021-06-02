package Handler;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

import RequestResult.ClearRequest;
import RequestResult.ClearResult;
import RequestResult.FillRequest;
import RequestResult.FillResult;
import Service.ClearService;
import Service.FillService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        FillRequest request;
        //FillResult result = null;
        FillService fillService = new FillService();
        Gson gson = new Gson();
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                String urlRequest = exchange.getRequestURI().toString();
                StringBuilder url = new StringBuilder(urlRequest);
                url.deleteCharAt(0);

                String[] arguments = url.toString().split("/");

                FillResult result;
                if (arguments.length <= 1 || arguments.length > 3) {
                    String message = "Error: Bad request.";
                    result = new FillResult(message, false);
                }
                else {
                    String username = arguments[1];
                    int generations = 4;
                    if (arguments.length == 3) {
                        generations = Integer.parseInt(arguments[2]);
                    }
                    request = new FillRequest(username, generations);
                    result = fillService.fill(request);
                }

                if (result != null && result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
                String json = gson.toJson(result);
                OutputStream os = exchange.getResponseBody();
                writeString(json, os);
                os.close();
            }
        }
        catch (IOException | SQLException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            String message = "Error: Internal server error.";
            FillResult result = new FillResult(message, false);
            String json = gson.toJson(result);
            OutputStream os = exchange.getResponseBody();
            writeString(json, os);
            os.close();

            e.printStackTrace();
        }
    }

    private void writeString(String myString, OutputStream os) throws  IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
        outputStreamWriter.write(myString);
        outputStreamWriter.flush();
    }
}