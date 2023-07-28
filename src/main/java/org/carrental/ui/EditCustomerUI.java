package org.carrental.ui;

import org.carrental.domain.Customer;
import org.carrental.service.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditCustomerUI {

    private final CustomerService customerService = new CustomerService();
    public EditCustomerUI(Integer id) {

        JFrame frame = new JFrame("Car Rental APP - Edit CUSTOMER");
        frame.setLayout(new GridBagLayout());

        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel phoneNumberLb = new JLabel("PHONE-NUMBER");
        JTextField phoneNumberTf = new JTextField(20);

        JLabel cnicLb = new JLabel("CNIC");
        JTextField cnicTf = new JTextField(20);

        JLabel addressLb = new JLabel("ADDRESS");
        JTextField addressTf = new JTextField(20);

        JLabel refPhoneNumberLb = new JLabel("REF-PHONE-NUMBER");
        JTextField refPhoneNumberTf = new JTextField(20);


        JLabel idLb = new JLabel("ID");
        JTextField idTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton update = new JButton("UPDATE");

        if(id != null){
            Customer customer = customerService.getById(id);
            nameTf.setText(customer.getName());
            phoneNumberTf.setText(customer.getPhoneNumber());
            cnicTf.setText(customer.getCnic());
            addressTf.setText(customer.getAddress());
            refPhoneNumberTf.setText(customer.getReferencePhoneNumber());
            idTf.setText(customer.getId().toString());
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(nameLb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(nameTf, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(phoneNumberLb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(phoneNumberTf, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(cnicLb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(cnicTf, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(addressLb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        frame.add(addressTf, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        frame.add(refPhoneNumberLb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        frame.add(refPhoneNumberTf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        frame.add(back, gbc);
        gbc.gridx = 0;
        gbc.gridy = 14;
        frame.add(update, gbc);

        back.addActionListener(e->{
            frame.dispose();
            new CustomerPanelUi();
        });
        JLabel nameErrorMsg = new JLabel();
        nameErrorMsg.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 11;
        frame.add(nameErrorMsg, gbc);
        nameTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String name = nameTf.getText();
                if (name.length() >= 100) {
                    e.consume(); // Ignore the input if the length exceeds 100
                    nameErrorMsg.setText("Name should be within 100 characters.");
                }
                else {
                    nameErrorMsg.setText(""); // Clear the error message if the input is within limit
                }
            }
        });
        phoneNumberTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();
                if (!Character.isDigit(c) || phoneNumberTf.getText().length() >= 15) {
                    e.consume(); // Ignore non-numeric input or if the length exceeds 15
                    nameErrorMsg.setText("Write Number and PhoneNumber should be within 15 numbers.");
                }else {
                    nameErrorMsg.setText(""); // Clear the error message if the input is within limit
                }
            }
        });

        cnicTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || cnicTf.getText().length() >= 50) {
                    e.consume(); // Ignore non-numeric input or if the length exceeds 50
                    nameErrorMsg.setText("Write Numbers and cnic should be within 50 numbers.");
                }else {
                    nameErrorMsg.setText(""); // Clear the error message if the input is within limit
                }
            }
        });

        refPhoneNumberTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || refPhoneNumberTf.getText().length() >= 15) {
                    e.consume(); // Ignore non-numeric input or if the length exceeds 15
                    nameErrorMsg.setText("Write Numbers and phone number should be within 15 numbers.");
                }else {
                    nameErrorMsg.setText(""); // Clear the error message if the input is within limit
                }
            }
        });

        update.addActionListener(e->{
            try {
                customerService.editRow(idTf.getText(),nameTf.getText(),phoneNumberTf.getText(),cnicTf.getText(),addressTf.getText(),
                        refPhoneNumberTf.getText());
                frame.dispose();
                new CustomerPanelUi();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,"Unable to Update");
            }
        });



        // basic properties
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
