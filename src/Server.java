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
                    System.out.println("New Client connected");
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            BufferedReader bufferedReader;
                            try {
                                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
                                String data = bufferedReader.readLine();
                                System.out.println(data);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    thread.start();
                    System.out.println("spawned thread" + thread.getId());


                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

