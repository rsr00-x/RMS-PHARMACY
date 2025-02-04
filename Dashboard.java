//package gui;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import models.User;

public class Dashboard extends JFrame implements ActionListener {
    JLabel label, imgLabel;
    JButton logoutBtn, btnManage;
    JPanel panel;
    ImageIcon img, icon;
    
    public Dashboard(User user) {
        super("Dashboard");
        this.setSize(560, 560);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        icon = new ImageIcon("images/LOGO.jpg");
        this.setIconImage(icon.getImage());
        
        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Welcome to Pharmacy dashboard, " + user.getUsername() + "!");
        label.setBounds(100, 50, 300, 30);
        panel.add(label);

        btnManage = new JButton("Manage Medicines");
        btnManage.setBounds(150, 100, 200, 30);
        btnManage.addActionListener(this);
        panel.add(btnManage);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(400, 50, 100, 30);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        ImagePanel imgPanel = new ImagePanel("images/LOGO.jpeg");
        imgPanel.setBounds(0, 0, 560, 560);
        panel.add(imgPanel);

        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        
        if (logoutBtn.getText().equals(command)) {
            new LoginFrame().setVisible(true);
            this.dispose();
        } else if (btnManage != null && btnManage.getText().equals(command)) {
            new ManagementPage().setVisible(true);
            this.dispose();
        }
    }
}
