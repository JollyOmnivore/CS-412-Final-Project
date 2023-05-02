import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;


public class Client {
    View view = new View();
    private Socket socket;


        public void connect(){
            try {
                socket = new Socket("127.0.0.1", 5000);
                view.showAuth();
                view.auth.setLoginButtonActionListener(new LoginListener());
            }
            catch (UnknownHostException e){
                e.printStackTrace();
                System.out.println("invalid connection");
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("invalid connection");
            }

        }
        public String sendData(String input) {
            try {
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
        public class LoginListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                sendData("A,"+view.auth.getUser());
            }
        }



    public static void main(String[] args) {
        Client client = new Client();
        client.connect();






            //String balance = sendData("A,Jolly");
        //System.out.println(balance);
        //balance  = sendData("B,Jolly,100,"+ balance);
        // Bet script B,Name,100,1000
        //System.out.println(balance);

    }
}




