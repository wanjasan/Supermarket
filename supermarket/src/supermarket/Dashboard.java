package supermarket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard implements ActionListener {
    private JFrame frame;
    private JToolBar toolBar;
    private JPanel mainPanel;
    private JPanel currentPanel;
    private JButton cashierButton;
    private JButton transactionButton;
    private JButton stockButton;
    private JButton salesButton;
    private JButton exitButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField cashierNameTextField;
    private JTextField mobileNumberTextField;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JLabel logoLabel;
    private JTable cashierTable;
    private DefaultTableModel model;

    Dashboard() {
        frame = new JFrame("Quick Bill");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\SANDRA\\Desktop\\picha\\imageedit_3_2017980709.png");
        logoLabel = new JLabel(logoIcon);
        toolBar.add(logoLabel);

        toolBar.add(Box.createHorizontalStrut(50));

        cashierButton = createButton("Cashier", "C:\\Users\\SANDRA\\Desktop\\picha\\an.png", "Cashier");
        cashierButton.addActionListener(this);
        toolBar.add(cashierButton);
        toolBar.add(Box.createHorizontalStrut(10));

        transactionButton = createButton("Transaction", "C:\\Users\\SANDRA\\Desktop\\picha\\tran.png", "Transaction");
        toolBar.add(transactionButton);
        toolBar.add(Box.createHorizontalStrut(10));

        stockButton = createButton("Stock", "C:\\Users\\SANDRA\\Desktop\\picha\\stock.png", "Stock");
        stockButton.addActionListener(this);
        toolBar.add(stockButton);
        toolBar.add(Box.createHorizontalStrut(10));

        salesButton = createButton("Sales", "C:\\Users\\SANDRA\\Desktop\\picha\\sale.png", "Sales");
        toolBar.add(salesButton);

        toolBar.add(Box.createHorizontalGlue());

        exitButton = createButton("Exit", "C:\\Users\\SANDRA\\Desktop\\picha\\exit.png", "Exit");
        exitButton.addActionListener(e -> System.exit(0));
        toolBar.add(exitButton);

        frame.add(toolBar, BorderLayout.NORTH);

        mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        currentPanel = null;

        showCashierPanel();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createButton(String text, String iconPath, String toolTip) {
        ImageIcon icon = new ImageIcon(iconPath);
        JButton button = new JButton(text, icon);
        button.setToolTipText(toolTip);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        adjustButtonSize(button);
        return button;
    }

    private void adjustButtonSize(JButton button) {
        Dimension size = button.getPreferredSize();
        size.width += 20;
        button.setPreferredSize(size);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashierButton) {
            if (currentPanel != null) {
                frame.remove(currentPanel);
                frame.revalidate();
                frame.repaint();
            }
            showCashierPanel();
        } else if (e.getSource() == stockButton) {
            if (currentPanel != null) {
                frame.remove(currentPanel);
                frame.revalidate();
                frame.repaint();
            }
            showStockPanel();
        }
    }

    private void showCashierPanel() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Cashier Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);

        formPanel.add(new JLabel("Cashier Name:"), gbc);
        cashierNameTextField = new JTextField(15);
        formPanel.add(cashierNameTextField, gbc);

        formPanel.add(new JLabel("Mobile Number:"), gbc);
        mobileNumberTextField = new JTextField(15);
        formPanel.add(mobileNumberTextField, gbc);

        formPanel.add(new JLabel("Address:"), gbc);
        addressTextField = new JTextField(15);
        formPanel.add(addressTextField, gbc);

        formPanel.add(new JLabel("Email-ID:"), gbc);
        emailTextField = new JTextField(15);
        formPanel.add(emailTextField, gbc);

        formPanel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        addButton = new JButton("Add New");
        addButton.addActionListener(e -> addNewCashier());
        buttonPanel.add(addButton);

        updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateCashier());
        buttonPanel.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteCashier());
        buttonPanel.add(deleteButton);

        String[] columns = {"Cashier ID", "Name", "Mobile", "Address", "Email"};
        model = new DefaultTableModel(columns, 0);
        cashierTable = new JTable(model);
        cashierTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(cashierTable);

        currentPanel = new JPanel(new BorderLayout());
        currentPanel.add(formPanel, BorderLayout.WEST);
        currentPanel.add(buttonPanel, BorderLayout.SOUTH);
        currentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(currentPanel, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void addNewCashier() {
        String name = cashierNameTextField.getText();
        String mobile = mobileNumberTextField.getText();
        String address = addressTextField.getText();
        String email = emailTextField.getText();
        String password = new String(passwordField.getPassword()); // This should be securely handled

        int newId = model.getRowCount() + 1; // Simple incremental ID
        Object[] row = {newId, name, mobile, address, email};
        model.addRow(row);

        clearFields();
    }

    private void updateCashier() {
        int selectedRow = cashierTable.getSelectedRow();
        if (selectedRow != -1) {
            model.setValueAt(cashierNameTextField.getText(), selectedRow, 1);
            model.setValueAt(mobileNumberTextField.getText(), selectedRow, 2);
            model.setValueAt(addressTextField.getText(), selectedRow, 3);
            model.setValueAt(emailTextField.getText(), selectedRow, 4);

            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to update.");
        }
    }

    private void deleteCashier() {
        int selectedRow = cashierTable.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);

            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
        }
    }

    private void showStockPanel() {
        // Placeholder for stock panel
    }

    private void clearFields() {
        cashierNameTextField.setText("");
        mobileNumberTextField.setText("");
        addressTextField.setText("");
        emailTextField.setText("");
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard());
    }
}
