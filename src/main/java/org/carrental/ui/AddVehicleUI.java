package org.carrental.ui;

import org.carrental.service.CustomerService;
import org.carrental.service.VehicleOwnerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;

public class AddVehicleUI {
    private final VehicleService vehicleService = new VehicleService();
    private final VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();

    public AddVehicleUI() {
        JFrame frame = new JFrame("Car Rental APP - ADD Vehicle");
        frame.setLayout(new GridLayout(6,2,10,10));


        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel modelLb = new JLabel("MODEL");
        JTextField modelTf = new JTextField(20);

        JLabel brandLb = new JLabel("BRAND");
        JTextField brandTf = new JTextField(20);


        JLabel colorLb = new JLabel("COLOR");
        JTextField colorTf = new JTextField(20);


        JLabel ownerIDLb = new JLabel("Owner-Name");
        String[] vehicleIdOptions = vehicleOwnerService.getAllVehicleOwners();
        JComboBox<String> dropdownvehicleOwner = new JComboBox<>(vehicleIdOptions);
//        JTextField ownerIdTf = new JTextField();



        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");


        //   adding content to the frame

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(modelLb);
        frame.add(modelTf);
        frame.add(brandLb);
        frame.add(brandTf);
        frame.add(colorLb);
        frame.add(colorTf);
        frame.add(ownerIDLb);
        frame.add(dropdownvehicleOwner);
        frame.add(save);
        frame.add(back);



        back.addActionListener(e->{
            frame.dispose();
            new VehicleUI();
        });

        save.addActionListener(e->{
            String inputVehicleOwner = (String) dropdownvehicleOwner.getSelectedItem();
            String[] partsVehicle = inputVehicleOwner.split(",");
            Integer vehicleOwnerId = Integer.valueOf(partsVehicle[0]);
            try {
                vehicleService.save(nameTf.getText(), modelTf.getText(),
                        brandTf.getText(), colorTf.getText(), Long.valueOf(vehicleOwnerId));
                frame.dispose();
                new VehicleUI();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,"Unable to save");
            }
        });
//        save.addActionListener(e->{
//            try {
//                vehicleService.save(nameTf.getText(),modelTf.getText(),brandTf.getText(),colorTf.getText(), Long.valueOf(ownerIdTf.getText()));
//            }catch (Exception ex){
//                JOptionPane.showMessageDialog(frame,"Unabel to save");
//            }
//        });


        // basic properties
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
