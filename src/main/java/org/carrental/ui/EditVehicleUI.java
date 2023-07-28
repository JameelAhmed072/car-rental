package org.carrental.ui;

import org.carrental.domain.Vehicle;
import org.carrental.service.VehicleOwnerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditVehicleUI {

    private final VehicleService vehicleService = new VehicleService();
    private final VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();

    public EditVehicleUI(Integer id) {
        JFrame frame = new JFrame("Car Rental APP - Edit CUSTOMER");
        frame.setLayout(new GridBagLayout());

        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel modelLb = new JLabel("MODEL");
        JTextField modelTf = new JTextField(20);

        JLabel brandLb = new JLabel("BRAND");
        JTextField brandTf = new JTextField(20);

        JLabel colorLb = new JLabel("COLOR");
        JTextField colorTf = new JTextField(20);

        JLabel ownerLb = new JLabel("OWNER");
        String[] vehicleOwnerOptions = vehicleOwnerService.getAllVehicleOwners();
        JComboBox<String> dropdownvehicleOwner = new JComboBox<>(vehicleOwnerOptions);


        JTextField idTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton update = new JButton("UPDATE");

        if(id != null){
            Vehicle vehicle = vehicleService.getById(id);
            nameTf.setText(vehicle.getName());
            modelTf.setText(String.valueOf(vehicle.getModel()));
            brandTf.setText(vehicle.getBrand());
            colorTf.setText(vehicle.getColor());
            dropdownvehicleOwner.setSelectedItem(vehicle.getOwnerId());
            idTf.setText(vehicle.getId().toString());
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
        frame.add(modelLb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(modelTf, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(brandLb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(brandTf, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(colorLb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        frame.add(colorTf, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        frame.add(ownerLb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        frame.add(dropdownvehicleOwner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        frame.add(back, gbc);
        gbc.gridx = 0;
        gbc.gridy = 14;
        frame.add(update, gbc);






        back.addActionListener(e->{
            frame.dispose();
            new VehicleUI();
        });

        update.addActionListener(e->{
            String inputVehicleOwner = (String) dropdownvehicleOwner.getSelectedItem();
            String[] partsVehicle = inputVehicleOwner.split(",");
            Integer vehicleOwnerId = Integer.valueOf(partsVehicle[0]);
            try {
                vehicleService.editRow(idTf.getText(),nameTf.getText(),modelTf.getText(),brandTf.getText(),colorTf.getText(), Long.valueOf(vehicleOwnerId));
                frame.dispose();
                new VehicleUI();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,"Unable to Update");
            }
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
        modelTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();
                if (!Character.isDigit(c) || modelTf.getText().length() >= 15) {
                    e.consume(); // Ignore non-numeric input or if the length exceeds 15
                    nameErrorMsg.setText("Write Number and Model should be within 15 numbers.");
                }else {
                    nameErrorMsg.setText(""); // Clear the error message if the input is within limit
                }
            }
        });



        brandTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String name = brandTf.getText();
                if (name.length() >= 100) {
                    e.consume(); // Ignore the input if the length exceeds 100
                    nameErrorMsg.setText("Brand should be within 100 characters.");
                }
                else {
                    nameErrorMsg.setText(""); // Clear the error message if the input is within limit
                }
            }
        });
        colorTf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String name = colorTf.getText();
                if (name.length() >= 100) {
                    e.consume(); // Ignore the input if the length exceeds 100
                    nameErrorMsg.setText("Color should be within 100 characters.");
                }
                else {
                    nameErrorMsg.setText(""); // Clear the error message if the input is within limit
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
