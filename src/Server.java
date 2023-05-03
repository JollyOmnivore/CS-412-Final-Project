import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
    public class Server {

        Model model;
        public static void main(String[] args) {
            double x;
            String op;
            double y;
            String answer;
            ArrayList<Socket> socketRecord = new ArrayList<>();

            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                while (true) {
                    Model.Createdb();
                    System.out.println("SERVER: waiting for client...");
                    Socket conn = serverSocket.accept();
                    socketRecord.add(conn);
//                    Thread LeaderThread = new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            while (true) {
//                                for (Socket socket : socketRecord) {
//                                    try {
//                                        PrintWriter LeaderWriter = new PrintWriter(socket.getOutputStream(), true);
//                                        LeaderWriter.println("L" + Model.LeaderBoard());
//                                        LeaderWriter.flush();
//                                    } catch (IOException e) {
//                                        throw new RuntimeException("Client Disconnected from Server");
//
//                                    }
//                                }
//                            }
//                        }
//                    });
                    System.out.println("New Client connected");
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                PrintWriter printWriter = new PrintWriter(conn.getOutputStream(), true);


                                while(true){
                                    try {

                                        String data = bufferedReader.readLine();
                                        if (data.substring(0, 1).equals("A")) {
                                            System.out.println(data.substring(0, 1));
                                            String[] dataArray = data.split(",");
                                            String Username = dataArray[1];
                                            int userBalance = Model.Auth(Username);
                                            System.out.println(userBalance);
                                            printWriter.println(userBalance);
                                        }


                                        else if (data.substring(0, 1).equals("B")) {

                                            System.out.println(data.substring(0, 1));
                                            String[] dataArray = data.split(",");
                                            String Username = dataArray[1];
                                            int betAmount = Integer.parseInt(dataArray[2]);
                                            int balance = Integer.parseInt(dataArray[3]);

                                            System.out.println("bet num " + betAmount);

                                            System.out.println("balance " + balance);

                                            Random randomNum = new Random();
                                            int result = randomNum.nextInt(2);
                                            System.out.println("coin flip number " + result);
                                            if (result == 0) {
                                                balance = balance + betAmount;
                                                System.out.println("You flipped Heads! you win");
                                                printWriter.println(balance);
                                                Model.Bet(Username,balance);

                                            }
                                            else {
                                                balance -= betAmount;
                                                System.out.println("You flipped Tails! you lose");
                                                printWriter.println(balance);
                                                Model.Bet(Username,balance);
                                            }



                                        }
                                        else if(data.substring(0, 1).equals("L")) {
                                            System.out.println("grabbing leaderboard data");
                                            printWriter.println("L" + Model.LeaderBoard());
                                        }




                                    }


                                    catch (IOException e){
                                        throw new RuntimeException("Client Disconnected from Server");
                                    }

                                }


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

