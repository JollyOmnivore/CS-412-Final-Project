import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;


public class Client {
    String line;
    View view = new View();
    int balance = 0;
    BufferedReader socketReader;
    String username;
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
                socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                line = socketReader.readLine();
                setLeaderboard(line);
                return line;

            } catch (IOException e) {
                e.printStackTrace();
                return("invalid connection");
            }
        }
        public class LoginListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                username = view.auth.getUser();
                String StrBalance = sendData("A,"+view.auth.getUser());
                balance = Integer.parseInt(StrBalance);
                System.out.println("open bet screen");
                view.showBet();
                view.bet.setFlipButtonActionListener(new flipButton());
                view.bet.setBalance(balance);
            }
        }
        public class flipButton implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(balance);
                int betAmount = view.bet.getBetAmount();
                String sendBet = "B," + username+ "," + betAmount + "," + balance ;
                System.out.println(sendBet);
                balance = Integer.parseInt(sendData(sendBet));
                view.bet.setBalance(balance);
            }
        }

        public void setLeaderboard(String line) {
            if (line.substring(0, 1).equals("L")) {
                line = line.substring(1);
                view.bet.setLeaderboardList(line);
            } else {

            }
        }

        public void updater(){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep(1000);
                            String leaderboard;
                            leaderboard = sendData("L");
                            setLeaderboard(leaderboard);

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            });
            thread.start();
        }


    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
        client.updater();
    }
}




