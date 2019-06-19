package SDJ2Exam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        final int PORT =6789;
        final String HOST ="localhost";
        Socket socket = new Socket(HOST,PORT);
        BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        out.println("hello");
        String reply = in.readLine();
        out.println("Jamie");
        reply = in.readLine();
        socket.close();
    }

}