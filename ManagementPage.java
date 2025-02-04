//package gui;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
//import services.PharmacyManagement;
//import models.Medicine;

public class ManagementPage extends JFrame implements ActionListener {
    JLabel idLabel, nameLabel, priceLabel, qtyLabel, imgLabel;
    JTextField idField, nameField, priceField, qtyField;
    JButton addButton, backButton;
    JPanel panel;
    ImageIcon icon, img;
    PharmacyManagement pm;

    public ManagementPage() {
        setTitle("RMS - A PHARMACY THAT NEVER BREAKS YOUR TRUST");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        icon = new ImageIcon("images/LOGO.jpeg");
        this.setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        
        panel = new JPanel();
        panel.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(20, 220, 100, 30);
        backButton.addActionListener(this);
        panel.add(backButton);

        img = new ImageIcon("images/medadd.jpg");
        imgLabel = new JLabel(img);
        imgLabel.setBounds(250, 20, 300, 200);
        panel.add(imgLabel);

        idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 100, 30);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(130, 20, 100, 30);
        panel.add(idField);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 100, 30);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 60, 100, 30);
        panel.add(nameField);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 100, 100, 30);
        panel.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(130, 100, 100, 30);
        panel.add(priceField);

        qtyLabel = new JLabel("Quantity:");
        qtyLabel.setBounds(20, 140, 100, 30);
        panel.add(qtyLabel);

        qtyField = new JTextField();
        qtyField.setBounds(130, 140, 100, 30);
        panel.add(qtyField);

        addButton = new JButton("Add");
        addButton.setBounds(20, 180, 100, 30);
        addButton.addActionListener(this);
        panel.add(addButton);
        
        pm = new PharmacyManagement();
        
        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int qty = Integer.parseInt(qtyField.getText());

                pm.addMedicine(new Medicine(id, name, price, qty));
                JOptionPane.showMessageDialog(this, "Medicine Added Successfully");

                idField.setText("");
                nameField.setText("");
                priceField.setText("");
                qtyField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter correct values.");
            }
        });

        add(panel);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (backButton.getText().equals(command)) {
            new Dashboard(null).setVisible(true);
            this.dispose();
        }
    }
}
