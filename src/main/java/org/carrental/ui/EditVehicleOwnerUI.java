package org.carrental.ui;

import org.carrental.domain.Customer;
import org.carrental.domain.VehicleOwner;
import org.carrental.service.CustomerService;
import org.carrental.service.VehicleOwnerService;

import javax.swing.*;
import java.awt.*;

public class EditVehicleOwnerUI {
    private final VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();

    public EditVehicleOwnerUI(Integer id) {

        JFrame frame = new JFrame("Car Rental APP - Edit CUSTOMER");
        frame.setLayout(new GridLayout(6,2,10,10));


        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel idLb = new JLabel("ID");
        JTextField idTf = new JTextField(20);




        JButton back = new JButton("BACK");
        JButton update = new JButton("UPDATE");

        if(id != null){
            VehicleOwner vehicleOwner = vehicleOwnerService.getById(id);
            nameTf.setText(vehicleOwner.getName());
            idTf.setText(vehicleOwner.getId().toString());
        }
        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(idLb);
        frame.add(idTf);
        frame.add(back);
        frame.add(update);





        back.addActionListener(e->{
            frame.dispose();
            new CustomerPanelUi();
        });

        update.addActionListener(e->{
            try {
                vehicleOwnerService.editRow(idTf.getText(),nameTf.getText());
                frame.dispose();
                new VehicleOwnerUI();

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
