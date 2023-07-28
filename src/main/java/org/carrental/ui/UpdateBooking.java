package org.carrental.ui;

import com.toedter.calendar.JDateChooser;
import org.carrental.service.BookingService;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class UpdateBooking {
    private final BookingService service = new BookingService();

    public UpdateBooking(String id, String cusId, String vehId, Date date, String amount, String status) {
        JFrame frame = new JFrame("Rental Car App | Update Booking");
        frame.setLayout(new GridLayout(6, 2, 10, 30));
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel customerIdLbl = new JLabel("Customer ID");
        String[] customerIdOptions = service.getCustomer();
        JComboBox<String> dropdownCustomerId = new JComboBox<>(customerIdOptions);

        JLabel vehicleIdLbl = new JLabel("Vehicle ID");
        String[] vehicleIdOptions = service.getVehicle();
        JComboBox<String> dropdownvehicleId = new JComboBox<>(vehicleIdOptions);

        JLabel bookingDate = new JLabel("Booking date");
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");

        JLabel bookingAmount = new JLabel("Amount");
        JTextField amountTf = new JTextField(20);

        JLabel statusLbl = new JLabel("Status");
        String[] statusOptions = {"Open", "Completed"};
        JComboBox<String> dropdownstatus = new JComboBox<>(statusOptions);

        JButton back = new JButton("BACK");
        JButton update = new JButton("UPDATE");

        frame.add(customerIdLbl);
        frame.add(dropdownCustomerId);
        frame.add(vehicleIdLbl);
        frame.add(dropdownvehicleId);
        frame.add(bookingDate);
        frame.add(dateChooser);
        frame.add(bookingAmount);
        frame.add(amountTf);
        frame.add(statusLbl);
        frame.add(dropdownstatus);
        frame.add(update);
        frame.add(back);

        dropdownCustomerId.setSelectedItem(cusId);
        dropdownvehicleId.setSelectedItem(vehId);
        dateChooser.setDate(date);
        amountTf.setText(amount);
        dropdownstatus.setSelectedItem(status);

        update.addActionListener(e -> {
            String inputCustomer = (String) dropdownCustomerId.getSelectedItem();
            String[] partsCustomer = inputCustomer.split(",");
            Integer customerId = Integer.valueOf(partsCustomer[0]);

            String inputVehicle = (String) dropdownvehicleId.getSelectedItem();
            String[] partsVehicle = inputVehicle.split(",");
            Integer vehicleId = Integer.valueOf(partsVehicle[0]);

            service.updateBooking(id, customerId, vehicleId, dateChooser.getDate(), amountTf.getText(), (String) dropdownstatus.getSelectedItem());
            frame.dispose();
            new BookingUI();
        });

        back.addActionListener(e -> {
            frame.dispose();
            new BookingUI();
        });
    }
}