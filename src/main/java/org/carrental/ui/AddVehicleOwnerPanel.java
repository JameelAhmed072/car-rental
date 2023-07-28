package org.carrental.ui;

import org.carrental.service.CustomerService;
import org.carrental.service.VehicleOwnerService;

import javax.swing.*;
import java.awt.*;

public class AddVehicleOwnerPanel {
    private final VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();

    public AddVehicleOwnerPanel() {

        JFrame frame = new JFrame("Car Rental APP - ADD VEHICLE OWNER");
        frame.setLayout(new GridLayout(6,2,10,10));


        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel cnicLb = new JLabel("CNIC");
        JTextField cnicTf = new JTextField(20);


        JLabel phoneNumberLb = new JLabel("PHONE_NUMBER");
        JTextField phoneNumberTf = new JTextField(20);


        JLabel addressLb = new JLabel("ADDRESS");
        JTextField addressTf = new JTextField(20);


        JLabel commissionLb = new JLabel("COMMISSION");
        JTextField commissionTf = new JTextField(20);



        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");


        //   adding content to the frame

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(cnicLb);
        frame.add(cnicTf);
        frame.add(phoneNumberLb);
        frame.add(phoneNumberTf);
        frame.add(addressLb);
        frame.add(addressTf);
        frame.add(commissionLb);
        frame.add(commissionTf);
        frame.add(save);
        frame.add(back);



        back.addActionListener(e->{
            frame.dispose();
            new CustomerPanelUi();
        });

        save.addActionListener(e->{
            try {
                vehicleOwnerService.save(nameTf.getText(),cnicTf.getText() ,phoneNumberTf.getText(),
                         addressTf.getText(), Double.valueOf(commissionTf.getText()));
                frame.dispose();
                new VehicleOwnerUI();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,"Unable to save");
            }
        });


        // basic properties
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
