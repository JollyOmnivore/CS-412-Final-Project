import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

    public class Server {

        public static void main(String[] args) {
            double x;
            String op;
            double y;
            String answer;

            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                while (true) {
                    System.out.println("SERVER: waiting for client...");
                    Socket conn = serverSocket.accept();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    PrintWriter printWriter = new PrintWriter(conn.getOutputStream());

                    String line = bufferedReader.readLine();

}
