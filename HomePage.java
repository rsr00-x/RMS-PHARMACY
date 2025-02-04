//package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame {
	private JPanel panel;
    public HomePage() {
        setTitle("RMS - Home");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

		this.add(panel);
		this.setLocationRelativeTo(null);
        
        JButton manageButton = new JButton("Manage Medicines");
        manageButton.addActionListener(e -> {
            new ManagementPage();
            dispose();
        });
        
        panel.add(manageButton);
        this.add(panel);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
