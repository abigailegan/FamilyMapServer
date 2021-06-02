package Handler;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

import RequestResult.*;
import Service.ClearService;
import Service.EventIDService;
import Service.PersonFamilyService;
import Service.PersonIDService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import passoffmodels.Person;

public class PersonHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        PersonRequest request;
        PersonResult result = null;

        Gson gson = new Gson();
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                if (exchange.getRequestHeaders().containsKey("Authorization")) {
                    String authtoken = exchange.getRequestHeaders().getFirst("Authorization");

                    String urlRequest = exchange.getRequestURI().toString();
                    StringBuilder url = new StringBuilder(urlRequest);
                    url.deleteCharAt(0);

                    String[] arguments = url.toString().split("/");

                    if (arguments.length > 2 || arguments.length < 1) {
                        result = new PersonResult("Error: Bad request.", false);
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        String json = gson.toJson(result);
                        OutputStream os = exchange.getResponseBody();
                        writeString(json, os);
                        os.close();
                    }
                    else if (arguments.length == 2) {
                        PersonIDService personIDService = new PersonIDService();
                        request = new PersonRequest(arguments[1], authtoken);
                        result = personIDService.person(request);

                        if (result.isSuccess()) {
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
                    else {
                        PersonFamilyService personFamilyService = new PersonFamilyService();
                        request = new PersonRequest(authtoken);
                        result = personFamilyService.person(request);

                        if (result.isSuccess()) {
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
            }
        }
        catch (IOException | SQLException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            String message = "Error: Internal server error.";
            result = new PersonResult(message, false);
            String json = gson.toJson(result);
            OutputStream os = exchange.getResponseBody();
            writeString(json, os);

            e.printStackTrace();
        }
    }

    private void writeString(String myString, OutputStream os) throws  IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
        outputStreamWriter.write(myString);
        outputStreamWriter.flush();
    }
}