package SDJ2Exam;
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException{
        final int PORT =6789;
        ServerSocket welcomeSocket = new ServerSocket(PORT);
        while (true)
        {
            Socket socket = welcomeSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            String request = in.readLine();
            out.println("happy to see you back");
            in.close();out.close();socket.close();
        }
    }



}