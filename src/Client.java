import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
Socket socket;

        public static String sendData(String input) {
            try {
                Socket socket = new Socket("127.0.0.1", 5000);
                PrintWriter socketWriter = new PrintWriter(socket.getOutputStream());
                socketWriter.println(input);
                socketWriter.flush();

                BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = socketReader.readLine();
                return line;

            } catch (IOException e) {
                e.printStackTrace();
                return("invalid connection");
            }
        }

    public static void main(String[] args) {
        System.out.println("hi");
        sendData("A,Carter");


    }
}




