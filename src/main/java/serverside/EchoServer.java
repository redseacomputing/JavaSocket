package serverside;
import java.io.*;
import java.net.ServerSocket;

public class EchoServer {
    private static final int PORT = 34522;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Session session = new Session(server.accept());
                session.start(); // it does not block this thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Important, that we do not use try-with-resources for server.accept() because we create a socket in one thread
    //and close it in another thread. In this case, try-with-resources is very error-prone because one thread may
    //close the socket while another thread wants to use it.
}