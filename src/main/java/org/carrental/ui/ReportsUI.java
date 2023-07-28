package org.carrental.ui;

import javax.swing.*;
import java.awt.*;

public class ReportsUI {
    public ReportsUI() {


        JFrame frame = new JFrame("Car Rental APP - Reports Panel");


        JButton monthlyReport = new JButton("Montly Reports");
        JButton commissionReport = new JButton("Commission Report");
        JButton carAvailabilityReport = new JButton("Car Availability Report");
        JButton back = new JButton("Back");

        frame.add(monthlyReport);
        frame.add(commissionReport);
        frame.add(carAvailabilityReport);
        frame.add(back);

        monthlyReport.addActionListener(e->{
            frame.dispose();
            new MonthlyReportUI();
        });
        commissionReport.addActionListener(e->{
            frame.dispose();
            new CommissionReportUI();
        });

        carAvailabilityReport.addActionListener(e->{
            frame.dispose();
            new CarAvailabilityReport();
        });

        back.addActionListener(e->{
            frame.dispose();
            new HomeUI();
        });
        frame.setLayout(new GridLayout(2,2));

        // basic properties
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}



