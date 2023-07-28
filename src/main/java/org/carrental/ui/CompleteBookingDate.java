package org.carrental.ui;

import com.toedter.calendar.JCalendar;
import org.carrental.dao.BookingDAO;
import org.carrental.service.BookingService;

import javax.swing.*;
import java.awt.*;

public class CompleteBookingDate {
    private final BookingService bookingService = new BookingService();

    public CompleteBookingDate(String id, Integer vehicleId) {

        JFrame frame = new JFrame("Rental Car App | Complete Booking");
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(1, 2, 5, 3));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel datePanel = new JPanel();

        JCalendar completionDate = new JCalendar();

        datePanel.add(completionDate);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 0, 0));

        JButton done = new JButton("Done");
        JButton back = new JButton("Back");

        buttonsPanel.add(done);
        buttonsPanel.add(back);

        frame.add(datePanel);
        frame.add(buttonsPanel);

        done.addActionListener(e -> {
            bookingService.completeBooking(id, completionDate.getDate(), vehicleId);
            frame.dispose();
            new BookingUI();
        });

        back.addActionListener(e -> {
            frame.dispose();
            new BookingUI();
        });
    }
}