package supermarket;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class AdminLogin implements ActionListener {
	
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JTextField AdminIDField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel AdminIDLabel = new JLabel("Admin ID");
	JLabel passwordLabel = new JLabel("password");
	JCheckBox displayCheckBox = new JCheckBox("show password");
	
	HashMap<String,String> Admininfo = new HashMap<String,String>();
	
	
	AdminLogin(HashMap<String,String> AdmininfoOriginal){
		
		Admininfo = AdmininfoOriginal;
		
		AdminIDLabel.setBounds(50, 100, 75, 25);
		AdminIDLabel.setFont(new Font("Arial", Font.PLAIN,15));
		
		passwordLabel.setBounds(50, 150, 75, 25);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN,15));
		
		AdminIDField.setBounds(125, 100, 200, 25);
		passwordField.setBounds(125, 150, 200, 25);
		
		displayCheckBox.setBounds(125 ,200 ,200 ,30 );
		displayCheckBox.setFont(new Font("Arial", Font.PLAIN,15));
		displayCheckBox.addActionListener(this);
		
		loginButton.setBounds(125, 250, 100, 30);
		loginButton.setFont(new Font("Arial", Font.PLAIN,15));
		loginButton.addActionListener(this);
		loginButton.setFocusable(false);
		
		
		
		frame.add(AdminIDLabel);
		frame.add(passwordLabel);
		frame.add(AdminIDField);
		frame.add(passwordField);
		frame.add(displayCheckBox);
		frame.add(loginButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(207, 159, 255));
}
	@Override
    public void actionPerformed(ActionEvent e) {
        // Check if it is a checkbox action event
        if (e.getSource() == displayCheckBox) {
            // If checkbox is selected
            if (displayCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Set echoChar to '\0' to display plaintext
            } else {
                passwordField.setEchoChar('*'); // Set echoChar back to '*' to hide plaintext
            }
        }
        
        // Check if it is the login button
        if (e.getSource() == loginButton) {
            String AdminID = AdminIDField.getText();
            String password = String.valueOf(passwordField.getPassword());
            
            if (Admininfo.containsKey(AdminID) && Admininfo.get(AdminID).equals(password)) {
                // Display a success message
                JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Create a Timer for delaying the opening of the dashboard
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Close the login frame
                        frame.dispose();

                        // Open the dashboard
                        Dashboard dashboard = new Dashboard();
                    }
                });
                timer.setRepeats(false); // Ensure that the timer only fires once
                timer.start(); // Start the timer
            } else {
                // Display an error message
                JOptionPane.showMessageDialog(frame, "Login unsuccessful! Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}