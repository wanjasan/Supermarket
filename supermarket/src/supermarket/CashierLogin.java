package supermarket;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class CashierLogin implements ActionListener {
	
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JTextField CashierIDField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel CashierIDLabel = new JLabel("Cashier ID");
	JLabel passwordLabel = new JLabel("password");
	JCheckBox displayCheckBox = new JCheckBox("show password");
	
	HashMap<String,String> Cashierinfo = new HashMap<String,String>();
	
	
	CashierLogin(HashMap<String,String> CashierinfoOriginal){
		
		Cashierinfo = CashierinfoOriginal;
		
		CashierIDLabel.setBounds(50, 100, 75, 25);
		CashierIDLabel.setFont(new Font("Arial", Font.PLAIN,15));
		
		passwordLabel.setBounds(50, 150, 75, 25);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN,15));
		
		CashierIDField.setBounds(125, 100, 200, 25);
		passwordField.setBounds(125, 150, 200, 25);
		
		displayCheckBox.setBounds(125 ,200 ,200 ,30 );
		displayCheckBox.setFont(new Font("Arial", Font.PLAIN,15));
		displayCheckBox.addActionListener(this);
		
		loginButton.setBounds(125, 250, 100, 30);
		loginButton.setFont(new Font("Arial", Font.PLAIN,15));
		loginButton.addActionListener(this);
		loginButton.setFocusable(false);
		
		
		
		frame.add(CashierIDLabel);
		frame.add(passwordLabel);
		frame.add(CashierIDField);
		frame.add(passwordField);
		frame.add(displayCheckBox);
		frame.add(loginButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);

		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == displayCheckBox) { // Check if it is a checkbox action event
		    if (displayCheckBox.isSelected()) { // If checkbox is selected
		        passwordField.setEchoChar((char)0); // Set echoChar to '\0' to display plaintext
		    } else {
		        passwordField.setEchoChar('*'); // Set echoChar back to '*' to hide plaintext
		    }
		}
		if(e.getSource()==loginButton) {
			String CashierID = CashierIDField.getText();
			String password = String.valueOf(passwordField.getPassword());
			
			if(Cashierinfo.containsKey(CashierID)) {
				if(Cashierinfo.get(CashierID).equals(password)) {
					Dashboard Dashboard = new Dashboard();
					
				}
			}
		}
	}
	
}
