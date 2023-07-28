package org.carrental.ui;

import org.carrental.dao.BookingDAO;
import org.carrental.service.BookingService;
import org.carrental.service.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;

public class BookingUI {
    private final BookingDAO dao = new BookingDAO();
    private final BookingService service = new BookingService();


    public BookingUI() {
        JFrame frame = new JFrame("Rental Car App | Booking");

        frame.setSize(1500, 1000);
        frame.setLayout(new GridLayout(1, 2, 150, 5));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel tableAndSearchPanel = new JPanel();
        tableAndSearchPanel.setBackground(Color.GRAY);
        tableAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JTextField search = new JTextField(30);

        String[][] data = service.getAllBookings();
        String[] headers = {"Id", "Customer", "Vehicle", "Booking-date", "Amount", "Status"};
        DefaultTableModel dtm = new DefaultTableModel(data, headers);
        JTable jt = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jt);

        tableAndSearchPanel.add(search);
        tableAndSearchPanel.add(sp);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton add = new JButton("ADD");
        JButton edit = new JButton("EDIT");
        JButton complete = new JButton("COMPLETE BOOKING");
        JButton delete = new JButton("DELETE");
        JButton back = new JButton("BACK");

        buttonsPanel.add(add);
        buttonsPanel.add(edit);
        buttonsPanel.add(complete);
        buttonsPanel.add(delete);
        buttonsPanel.add(back);

        frame.add(tableAndSearchPanel);
        frame.add(buttonsPanel);

        search.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String[][] data = service.searchByDate(search.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, headers);
                jt.setModel(dtm);
            }
        });

        add.addActionListener(e -> {
            frame.dispose();
            new AddBookingUI();
        });

        edit.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                String cusId = (String) jt.getValueAt(jt.getSelectedRow(), 1);
                String vehId = (String) jt.getValueAt(jt.getSelectedRow(), 2);
                String date = (String) jt.getValueAt(jt.getSelectedRow(), 3);
                String amount = (String) jt.getValueAt(jt.getSelectedRow(), 4);
                String status = (String) jt.getValueAt(jt.getSelectedRow(), 5);
                frame.dispose();
                new UpdateBooking(id, cusId, vehId, Date.valueOf(date), amount, status);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the field");
            }
        });

        complete.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0));

                String vehId = (String) jt.getValueAt(jt.getSelectedRow(), 2);
                String[] partsVehicle = vehId.split(",");
                Integer vehicleId = Integer.valueOf(partsVehicle[0]);
                frame.dispose();
                new CompleteBookingDate(id, vehicleId);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a field");
            }
        });

        delete.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0));
                dao.softDelete(id);
                DefaultTableModel tableDelte = new DefaultTableModel(service.getAllBookings(), headers);
                jt.setModel(tableDelte);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a field");
            }
        });

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUI();
        });
    }
}