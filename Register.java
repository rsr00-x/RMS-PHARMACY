//package gui;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//import services.UserService;
//import models.User;

public class Register extends JFrame implements ActionListener {
    private JLabel userLabel, emailLabel, passLabel, confirmPassLabel, dobLabel, genderLabel, roleLabel, imgLabel;
    private JTextField userTF, emailTF;
    private JPasswordField passPF, confirmPassPF;
    private JComboBox<String> dobDay, dobMonth, dobYear, genderCB, roleCB;
    private JButton regBtn, clearBtn, backBtn, showPassBtn;
    private JPanel panel;
    private Font font1, font2;
    private ImageIcon img, icon, eyeIcon, eyeIconSlash;

    private UserService userService = new UserService();

    public Register() {
        super("Registration");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        icon = new ImageIcon("images/LOGO.jpg");
        this.setIconImage(icon.getImage());

        panel = new JPanel();
        panel.setLayout(null);

        font1 = new Font("Arial Black", Font.BOLD, 23);
        font2 = new Font("Arial Black", Font.PLAIN, 20);

        userLabel = new JLabel("Username: ");
        userLabel.setBounds(320, 200, 200, 30);
        userLabel.setFont(font1);
        panel.add(userLabel);

        userTF = new JTextField();
        userTF.setBounds(520, 200, 300, 30);
        userTF.setFont(font2);
        panel.add(userTF);

        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(320, 250, 200, 30);
        emailLabel.setFont(font1);
        panel.add(emailLabel);

        emailTF = new JTextField();
        emailTF.setBounds(520, 250, 300, 30);
        emailTF.setFont(font2);
        panel.add(emailTF);

        passLabel = new JLabel("Password: ");
        passLabel.setBounds(320, 300, 200, 30);
        passLabel.setFont(font1);
        panel.add(passLabel);

        eyeIcon = new ImageIcon("images/eye.png");
        eyeIconSlash = new ImageIcon("images/eye_slash.png");
        showPassBtn = new JButton(eyeIcon);
        showPassBtn.setBounds(830, 300, 30, 30);

        passPF = new JPasswordField();
        passPF.setBounds(520, 300, 300, 30);
        passPF.setFont(font2);
        panel.add(passPF);

        confirmPassLabel = new JLabel("Confirm Password: ");
        confirmPassLabel.setBounds(320, 350, 250, 30);
        confirmPassLabel.setFont(font1);
        panel.add(confirmPassLabel);

        confirmPassPF = new JPasswordField();
        confirmPassPF.setBounds(520, 350, 300, 30);
        confirmPassPF.setFont(font2);
        panel.add(confirmPassPF);

        dobLabel = new JLabel("Date of Birth: ");
        dobLabel.setBounds(320, 400, 200, 30);
        dobLabel.setFont(font1);
        panel.add(dobLabel);

        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        String[] years = new String[36];
        for (int i = 1990; i <= 2025; i++) {
            years[i - 1990] = String.valueOf(i);
        }

        dobDay = new JComboBox<>(days);
        dobDay.setBounds(520, 400, 60, 30);
        dobDay.setFont(font2);
        panel.add(dobDay);

        dobMonth = new JComboBox<>(months);
        dobMonth.setBounds(590, 400, 80, 30);
        dobMonth.setFont(font2);
        panel.add(dobMonth);

        dobYear = new JComboBox<>(years);
        dobYear.setBounds(680, 400, 80, 30);
        dobYear.setFont(font2);
        panel.add(dobYear);

        genderLabel = new JLabel("Gender: ");
        genderLabel.setBounds(320, 450, 200, 30);
        genderLabel.setFont(font1);
        panel.add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        genderCB = new JComboBox<>(genders);
        genderCB.setBounds(520, 450, 200, 30);
        genderCB.setFont(font2);
        panel.add(genderCB);

        roleLabel = new JLabel("Role: ");
        roleLabel.setBounds(320, 500, 200, 30);
        roleLabel.setFont(font1);
        panel.add(roleLabel);

        String[] roles = {"Customer", "Owner", "Employee"};
        roleCB = new JComboBox<>(roles);
        roleCB.setBounds(520, 500, 200, 30);
        roleCB.setFont(font2);
        panel.add(roleCB);

        regBtn = new JButton("Register");
        regBtn.setBounds(620, 650, 120, 40);
        regBtn.setFont(font2);
        regBtn.addActionListener(this);
        panel.add(regBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(460, 650, 120, 40);
        clearBtn.setFont(font2);
        clearBtn.addActionListener(this);
        panel.add(clearBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(780, 650, 120, 40);
        backBtn.setFont(font2);
        backBtn.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });
        panel.add(backBtn);

        ImageIcon img = new ImageIcon(getClass().getResource("/images/registration.jpg"));
        imgLabel = new JLabel(img);
        imgLabel.setBounds(0,0,1280,720);
        panel.add(imgLabel);
        panel.setComponentZOrder(imgLabel, panel.getComponentCount() - 1);

        this.add(panel);
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (command.equals("Register")) {
            register();
        } else if (command.equals("Clear")) {
            clearFields();
        } else if (command.equals("Back")) {
            new LoginFrame().setVisible(true);
            this.dispose();
        }
    }

    private void register() {
    String name = userTF.getText();
    String email = emailTF.getText();
    String password = new String(passPF.getPassword());
    String confirmPassword = new String(confirmPassPF.getPassword());
    String dob = dobDay.getSelectedItem() + "-" + dobMonth.getSelectedItem() + "-" + dobYear.getSelectedItem();
    String gender = (String) genderCB.getSelectedItem();
    String role = (String) roleCB.getSelectedItem();

    if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill all the fields");
        return;
    }

    if (!password.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match");
        return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Userdata.txt", true))) {
        writer.write(name + "," + email + "," + password + "," + role + "\n");
        JOptionPane.showMessageDialog(this, "Registration Successful");
        new LoginFrame().setVisible(true);
        this.dispose();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

    private void clearFields() {
        userTF.setText("");
        emailTF.setText("");
        passPF.setText("");
        confirmPassPF.setText("");
        dobDay.setSelectedIndex(0);
        dobMonth.setSelectedIndex(0);
        dobYear.setSelectedIndex(0);
        genderCB.setSelectedIndex(0);
        roleCB.setSelectedIndex(0);
    }
}
