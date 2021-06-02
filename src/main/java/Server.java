import java.io.*;
import java.net.*;

import Handler.*;
import com.sun.net.httpserver.*;

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    // This method initializes and runs the server.
    // The "portNumber" parameter specifies the port number on which the
    // server should accept incoming client connections.
    private void run(String portNumber) {

        // Since the server has no "user interface", it should display "log"
        // messages containing information about its internal activities.
        // This allows a system administrator (or you) to know what is happening
        // inside the server, which can be useful for diagnosing problems
        // that may occur.
        System.out.println("Initializing HTTP Server");

        try {
            // Create a new HttpServer object.
            // Rather than calling "new" directly, we instead create
            // the object by calling the HttpServer.create static factory method.
            // Just like "new", this method returns a reference to the new object.
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Indicate that we are using the default "executor".
        // This line is necessary, but its function is unimportant for our purposes.
        server.setExecutor(null);

        // Log message indicating that the server is creating and installing
        // its HTTP handlers.
        // The HttpServer class listens for incoming HTTP requests.  When one
        // is received, it looks at the URL path inside the HTTP request, and
        // forwards the request to the handler for that URL path.
        System.out.println("Creating contexts");


        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/fill/", new FillHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person", new PersonHandler());
        server.createContext("/event", new EventHandler());
        server.createContext("/", new FileHandler());

        // Log message indicating that the HttpServer is about the start accepting
        // incoming client connections.
        System.out.println("Starting server");

        // Tells the HttpServer to start accepting incoming client connections.
        // This method call will return immediately, and the "main" method
        // for the program will also complete.
        // Even though the "main" method has completed, the program will continue
        // running because the HttpServer object we created is still running
        // in the background.
        server.start();

        // Log message indicating that the server has successfully started.
        System.out.println("Server started");
    }

    // "main" method for the server program
    // "args" should contain one command-line argument, which is the port number
    // on which the server should accept incoming client connections.
    public static void main(String[] args) {
        String portNumber = args[0];
        new Server().run(portNumber);
    }
}
