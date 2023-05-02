import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


public class View {

JFrame jFrame = new JFrame();

Auth auth = new Auth();



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
    Bet bet = new Bet();
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
    JLabel Balance = new JLabel("Balance:");

    JLabel BetAmount = new JLabel("Bet Amount:");
    JComboBox jComboBox = new JComboBox();

    JComboBox choice = new JComboBox();

    JButton flip = new JButton();

    JLabel leaderboard = new JLabel("Learderbaord");
    JList learderboard = new JList();

    public JPanel getjPanel(){
        return bet;
    }

}

    public static void main(String[] args) {
        View view = new View();
        view.showAuth();


    }

}
