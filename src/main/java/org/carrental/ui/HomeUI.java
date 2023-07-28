package org.carrental.ui;

import javax.swing.*;
import java.awt.*;

public class HomeUI {
    public HomeUI() {
        JFrame frame = new JFrame("Car Rental APP - HOME");

        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        JButton customerBtn = new JButton("Customer");
        addImageOnButton(customerBtn,"src/main/resources/customer-icon.png",100,100);

        customerBtn.addActionListener(e->{
            frame.dispose();
            new CustomerPanelUi();
        });
        JButton vehicleBtn = new JButton("Vehicle");
        addImageOnButton(vehicleBtn,"src/main/resources/car-vehicle-insurance-icon.png",100,100);

        vehicleBtn.addActionListener(e->{
            frame.dispose();
            new VehicleUI();
        });

        JButton vehicleOwnerBtn = new JButton("VehicleOwner");
        addImageOnButton(vehicleOwnerBtn,"src/main/resources/owner.png",100,100);

        vehicleOwnerBtn.addActionListener(e->{
            frame.dispose();
            new VehicleOwnerUI();
        });

        JButton bookingBtn = new JButton("Booking");
        addImageOnButton(bookingBtn,"src/main/resources/booking.png",100,100);

        bookingBtn.addActionListener(e -> {
            frame.dispose();
            new BookingUI();
        });

        JButton userBtn = new JButton("User");
        addImageOnButton(userBtn,"src/main/resources/man.png",100,100);

        JButton reportBtn = new JButton("Report");
        addImageOnButton(reportBtn,"src/main/resources/seo-report.png",100,100);

        reportBtn.addActionListener(e->{
            frame.dispose();
            new ReportsUI();
        });

        JButton logOutBtn = new JButton("LogOut");
        addImageOnButton(logOutBtn,"src/main/resources/power-off.png",100,100);

        logOutBtn.addActionListener(e->{
            frame.dispose();
            new LoginUI();
        });




        frame.add(customerBtn);
        frame.add(vehicleBtn);
        frame.add(vehicleOwnerBtn);
        frame.add(bookingBtn);
        frame.add(userBtn);
        frame.add(logOutBtn);
        frame.add(reportBtn);




        // basic properties
        frame.setSize(1300,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addImageOnButton(JButton button, String imagePath, int height, int width) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));
    }


}
