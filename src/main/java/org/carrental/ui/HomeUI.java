package org.carrental.ui;

import javax.swing.*;
import java.awt.*;

public class HomeUI {
    public HomeUI() {
        JFrame frame = new JFrame("Car Rental APP - HOME");

        JPanel btnPanel = new JPanel();  // This is a panel

        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        btnPanel.add(new JButton("1"));
        btnPanel.add(new JButton("2"));
        btnPanel.add(new JButton("3"));
        btnPanel.add(new JButton("4"));
        btnPanel.add(new JButton("5"));


        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));

        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};

        JTable jt=new JTable(data,column);
        JScrollPane sp=new JScrollPane(jt);
        tablePanel.add(sp,BorderLayout.CENTER);

        frame.setLayout(new BorderLayout(10,50));  // here we will add our layouts


        frame.add(btnPanel,BorderLayout.SOUTH);  // adding panel to frame
        frame.add(tablePanel,BorderLayout.CENTER);
        frame.add(new JButton("Hello"),BorderLayout.NORTH);

        // basic properties
        frame.setSize(1300,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
