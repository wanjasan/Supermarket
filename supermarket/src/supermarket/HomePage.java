package supermarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener {
    private JFrame frame;
    private JButton adminLoginButton;
    private JButton cashierLoginButton;
    private IDandPasswords idandPasswords;
    private JLabel messageLabel;
    
    HomePage(IDandPasswords idandPasswords) {
    this.idandPasswords = idandPasswords;
    frame = new JFrame("Supermarket billing system");
    frame.setSize(721, 430);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(null);

    JLabel welcomeLabel = new JLabel("Welcome To Supermarket Billing System");
    welcomeLabel.setBounds(150, 50, 400, 30);
    welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
    welcomeLabel.setForeground(Color.BLACK);
    
    messageLabel = new JLabel();
    messageLabel.setBounds(125, 250, 250, 35);
    messageLabel.setFont(new Font(null, Font.ITALIC, 15));
    
    String imagePath = System.getProperty("user.home") + "/Desktop/picha/logo.png";
    ImageIcon imageIcon = new ImageIcon(imagePath);
    JLabel imageLabel = new JLabel(imageIcon);

    int labelWidth = (int) (frame.getWidth() * 0.6);
    int labelHeight = (int) (frame.getHeight() * 0.6);
    int labelXPos = (frame.getWidth() - labelWidth) / 2 - (labelWidth / 4);
    int labelYPos = (frame.getHeight() - labelHeight) / 2;

    imageLabel.setBounds(labelXPos, labelYPos, labelWidth, labelHeight);

    adminLoginButton = new JButton("Admin Login");
    adminLoginButton.setBounds(labelXPos + labelWidth + 20, labelYPos + 30, 150, 40);
    adminLoginButton.setFocusable(false);

    // Use the class-level cashierLoginButton
    cashierLoginButton = new JButton("Cashier Login");
    cashierLoginButton.setBounds(labelXPos + labelWidth + 20, labelYPos + 90, 150, 40);
    cashierLoginButton.setFocusable(false);

    panel.add(welcomeLabel);
    panel.add(imageLabel);
    panel.add(adminLoginButton);
    panel.add(cashierLoginButton);
    panel.add(messageLabel);

    frame.getContentPane().add(panel);
    adminLoginButton.addActionListener(this);
    cashierLoginButton.addActionListener(this);

    frame.setVisible(true);
    frame.setResizable(false);
    panel.setBackground(new Color(207, 159, 255));
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminLoginButton) {
            AdminLogin adminlogin = new AdminLogin(idandPasswords.getAdmininfo()); // Open the Admin Login window
            messageLabel.setText("Login sucess");
            messageLabel.setForeground(Color.green);
            frame.dispose(); // Close the current Home Page window
        }
        else if (e.getSource()== cashierLoginButton) {
        	CashierLogin cashierlogin = new CashierLogin(idandPasswords.getAdmininfo());
        	messageLabel.setText("Login sucess");
            messageLabel.setForeground(Color.green);
            frame.dispose();
        }
        else {
        	messageLabel.setForeground(Color.red);
        	messageLabel.setText("Incorrect Password Or Username");
        }
    }

    public static void main(String[] args) {
    	IDandPasswords idandPasswords = new IDandPasswords();
    	SwingUtilities.invokeLater(() -> new HomePage(idandPasswords));
    }
}