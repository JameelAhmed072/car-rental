package org.carrental.ui;

import com.toedter.calendar.JCalendar;
import org.carrental.service.BookingService;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.util.Properties;

public class AddBookingUI {
    private final BookingService service = new BookingService();

    public AddBookingUI() {
        JFrame frame = new JFrame("Rental Car App | Add Booking");
        frame.setLayout(new GridLayout(6, 2, 10, 30));
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel customerIdLbl = new JLabel("Customer");
        String[] customerIdOptions = service.getCustomer();
        JComboBox<String> dropdownCustomerId = new JComboBox<>(customerIdOptions);

        JLabel vehicleIdLbl = new JLabel("Vehicle ID");
        String[] vehicleIdOptions = service.getVehicle();
        JComboBox<String> dropdownvehicleId = new JComboBox<>(vehicleIdOptions);

        JLabel date = new JLabel("Date");
        JCalendar datePicker = new JCalendar();

        JLabel amount = new JLabel("Amount");
        JTextField amountTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(customerIdLbl);
        frame.add(dropdownCustomerId);
        frame.add(vehicleIdLbl);
        frame.add(dropdownvehicleId);
        frame.add(date);
        frame.add(datePicker);
        frame.add(amount);
        frame.add(amountTf);
        frame.add(save);
        frame.add(back);


        back.addActionListener(e -> {
            frame.dispose();
            new BookingUI();
        });

        save.addActionListener(e -> {
            String inputVehicle = (String) dropdownvehicleId.getSelectedItem();
            String[] partsVehicle = inputVehicle.split(",");
            Integer vehicleId = Integer.valueOf(partsVehicle[0]);

            String inputCustomer = (String) dropdownCustomerId.getSelectedItem();
            String[] partsCustomer = inputCustomer.split(",");
            Integer customerId = Integer.valueOf(partsCustomer[0]);

            Boolean flag = true;
            if (Integer.valueOf(amountTf.getText()) < 0) {
                JOptionPane.showMessageDialog(frame, "Amount can not be negative = '" + amountTf.getText() + "'");
                flag = false;
                amountTf.setText("");
            } else {
            }
            if (flag) {
                service.add(customerId, vehicleId, datePicker.getDate(), Integer.valueOf(amountTf.getText()));
                frame.dispose();
                new BookingUI();
            }
        });
    }
}