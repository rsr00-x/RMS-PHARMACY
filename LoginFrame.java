//package gui;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//import models.User;

public class LoginFrame extends JFrame implements ActionListener {
    JLabel usernameLabel, emailLabel, passLabel, regLabel, imgLabel;
    JTextField usernameTF, emailTF;
    JPasswordField passPF;
    JButton loginBtn, regBtn, showPassBtn;
    JPanel panel;
    Font font1, font2;
    ImageIcon img, icon, eyeIcon, eyeIconSlash;
    boolean isPasswordVisible = false;

    private UserService userService = new UserService();

    public LoginFrame() {
        super("RMS - A PHARMACY THAT NEVER BREAKS YOUR TRUST");
        this.setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        icon = new ImageIcon("images/LOGO.jpeg");
        this.setIconImage(icon.getImage());
        
        panel = new JPanel();
        panel.setLayout(null);
    
        font1 = new Font("Arial Black",Font.BOLD, 23);
        font2 = new Font("Arial Black",Font.BOLD, 20);
        
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(320, 300, 200, 30);
        usernameLabel.setFont(font1);
        panel.add(usernameLabel);

        usernameTF = new JTextField();
        usernameTF.setBounds(520, 300, 300, 30);
        usernameTF.setFont(font2);
        panel.add(usernameTF);

        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(320, 350, 200, 30);
        emailLabel.setFont(font1);
        panel.add(emailLabel);

        emailTF = new JTextField();
        emailTF.setBounds(520, 350, 300, 30);
        emailTF.setFont(font2);
        panel.add(emailTF);

        passLabel = new JLabel("Password: ");
        passLabel.setBounds(320, 400, 200, 30);
        passLabel.setFont(font1);
        panel.add(passLabel);

        eyeIcon = new ImageIcon("images/eye.png");
        eyeIconSlash = new ImageIcon("images/eye_slash.png");
        showPassBtn = new JButton(eyeIcon);
        showPassBtn.setBounds(830, 400, 30, 30);
        showPassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passPF.getEchoChar() == '*') {
                    passPF.setEchoChar((char) 0);
                    showPassBtn.setText("Hide");
                } else {
                    passPF.setEchoChar('*');
                    showPassBtn.setText("Show");
                }
            }
        });
        panel.add(showPassBtn);

        passPF = new JPasswordField();
        passPF.setBounds(520, 400, 300, 30);
        passPF.setFont(font2);
        passPF.addActionListener(this);
        panel.add(passPF);
        
        loginBtn = new JButton("Login");
        loginBtn.setBounds(620, 450, 120, 40);
        loginBtn.addActionListener(this);
        panel.add(loginBtn);
        
        regLabel = new JLabel("Do not have an account?");
        regLabel.setBounds(300, 550, 500, 30);
        regLabel.setFont(font1);
        panel.add(regLabel);

        regBtn = new JButton("Register");
        regBtn.setBounds(700, 550, 120, 40);
        regBtn.addActionListener(this);
        panel.add(regBtn);
        
        this.add(panel);
        this.setLocationRelativeTo(null);

        ImageIcon img = new ImageIcon(getClass().getResource("/images/homepage.jpg"));
        imgLabel = new JLabel(img);
        imgLabel.setBounds(0,0,1280,720);
        panel.add(imgLabel);
        panel.setComponentZOrder(imgLabel, panel.getComponentCount() - 1);
        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginBtn) {
            login();
        } else if (ae.getSource() == regBtn) {
            new Register().setVisible(true);
            this.dispose();
        }
    }
    
    private void login() {
    String username = usernameTF.getText();
    String password = new String(passPF.getPassword());
    User user = userService.login(username, password);
    if (user != null) {
        JOptionPane.showMessageDialog(this, "Login successful");
        new Dashboard(user).setVisible(true);
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Invalid credentials. Try again.");
    }
}
}

