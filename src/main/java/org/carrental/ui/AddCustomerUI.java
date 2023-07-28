package org.carrental.ui;

import org.carrental.service.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddCustomerUI {
    private final CustomerService customerService = new CustomerService();
    public AddCustomerUI() {
        JFrame frame = new JFrame("Car Rental APP - ADD CUSTOMER");
        frame.setLayout(new GridLayout(6,2,10,10));

        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel phoneLb = new JLabel("PHONE");
        JTextField phoneTf = new JTextField(20);

        JLabel cnicLb = new JLabel("CNIC");
        JTextField cnicTf = new JTextField(20);

        JLabel addressLb = new JLabel("ADDRESS");
        JTextField addressTf = new JTextField(20);

        JLabel refLb = new JLabel("REF_PHONE");
        JTextField refTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        //   adding content to the frame

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(phoneLb);
        frame.add(phoneTf);
        frame.add(cnicLb);
        frame.add(cnicTf);
        frame.add(addressLb);
        frame.add(addressTf);
        frame.add(refLb);
        frame.add(refTf);
        frame.add(save);
        frame.add(back);

        back.addActionListener(e->{
            frame.dispose();
            new CustomerPanelUi();
        });


        nameTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (nameTf.getText().length() >= 100) {
                    e.consume(); // Ignore the input if the length exceeds 100
                }
            }
        });
        phoneTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || phoneTf.getText().length() >= 15) {
                    e.consume(); // Ignore non-numeric input or if the length exceeds 15
                }
            }
        });

        cnicTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || cnicTf.getText().length() >= 50) {
                    e.consume(); // Ignore non-numeric input or if the length exceeds 50
                }
            }
        });

        refTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || refTf.getText().length() >= 15) {
                    e.consume(); // Ignore non-numeric input or if the length exceeds 15
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    customerService.save(nameTf.getText(), phoneTf.getText(), cnicTf.getText(), addressTf.getText(), refTf.getText());
                    frame.dispose();
                    new CustomerPanelUi();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,"Unable to save");
                }
            }
        });
        // basic properties
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
