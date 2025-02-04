//package gui;

import javax.swing.*;
import java.awt.*;
//import models.Medicine;

public class MedicineFrame extends JFrame {
    private JLabel lblName, lblPrice, lblQuantity;
    private JButton btnClose;

    public MedicineFrame(Medicine medicine) {
        super("Medicine Details");
        setSize(400, 300);
        setLayout(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblName = new JLabel("Name: " + medicine.getName());
        lblPrice = new JLabel("Price: $" + medicine.getPrice());
        lblQuantity = new JLabel("Quantity: " + medicine.getQuantity());

        btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());

        add(lblName);
        add(lblPrice);
        add(lblQuantity);
        add(btnClose);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
