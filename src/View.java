import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


public class View {

JFrame jFrame = new JFrame();

Auth auth = new Auth();

Bet bet = new Bet();

public View() {
    jFrame.setSize(500, 500);
    jFrame.setVisible(true);
}



public void showAuth() {
    jFrame.getContentPane().add(auth.getjPanel());
    jFrame.revalidate();
    jFrame.repaint();
}
public void showBet(){
    System.out.println("show bet worked");
    jFrame.getContentPane().removeAll();
    jFrame.getContentPane().add(bet.getjPanel());
    jFrame.revalidate();
    jFrame.repaint();
}


public class Auth{

    public Auth(){
        jPanel.add(jTextField);
        jPanel.add(jButton);
        jTextField.setPreferredSize(new Dimension(200, 30));
    }
    JPanel jPanel = new JPanel();
    JTextField jTextField = new JTextField();

    JButton jButton = new JButton("Login");
    public String getUser(){
        return jTextField.getText();
    }
    public JPanel getjPanel(){
        return jPanel;
    }
    public void setLoginButtonActionListener(ActionListener AL) {
        jButton.addActionListener(AL);
    }

}


public class Bet{

    JPanel bet = new JPanel();
    JLabel balanceLabel = new JLabel("Balance:");

    JLabel betAmountLabel = new JLabel("Bet Amount:");
    JComboBox betAmountComboBox = new JComboBox();

    JComboBox choiceComboBox = new JComboBox();

    JButton flipButton = new JButton("Flip");

    JLabel leaderboardLabel = new JLabel("Learderbaord");
    JList leaderboardList = new JList();

    public Bet(){


        betAmountComboBox.addItem(50);
        betAmountComboBox.addItem(100);
        betAmountComboBox.addItem(200);


        choiceComboBox.addItem("Heads");
        choiceComboBox.addItem("Tails");



        String[] leaderboardData = {"Player 1: $50", "Player 2: $30", "Player 3: $20", "Player 4: $10"};
        leaderboardList.setListData(leaderboardData);

        bet.add(balanceLabel);
        bet.add(betAmountLabel);
        bet.add(betAmountComboBox);
        bet.add(choiceComboBox);
        bet.add(flipButton);
        bet.add(leaderboardLabel);
        bet.add(leaderboardList);



    }


    public JPanel getjPanel(){
        return bet;
    }
    public void setFlipButtonActionListener(ActionListener AL) {
        flipButton.addActionListener(AL);
    }

    public void setBalance(int balance){
        balanceLabel.setText("Balance: " + balance);

    }
    public int getBetAmount(){
        int betAmount = (int) betAmountComboBox.getSelectedItem();
        return betAmount;
    }




}

    public static void main(String[] args) {
        View view = new View();
        view.showBet();


    }

}
