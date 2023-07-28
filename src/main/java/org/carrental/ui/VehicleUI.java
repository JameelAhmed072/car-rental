package org.carrental.ui;

import org.carrental.service.CustomerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class VehicleUI {
    private final VehicleService vehicleService = new VehicleService();
    public VehicleUI() {
        JFrame frame = new JFrame("Car Rental APP - VEHICLE Panel");



        JPanel tblAndSearchPanel = new JPanel();
        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JTextField searchTf = new JTextField(30);



        String data[][]= vehicleService.getAllVehicleForJTabel();

        String column[]={"ID","V_NAME","V_Model","V_Brand","V_Color","Owner_Name"};

        DefaultTableModel dtm = new DefaultTableModel(data,column);


        JTable jt = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jt);

        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(sp);



        //   2nd panel

        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JButton addVehicleButton = new JButton("ADD");
        JButton editVehicleButton = new JButton("EDIT");
        JButton deleteVehicleButton = new JButton("DELETE");
        JButton back = new JButton("BACK");


        actionButtonPanel.add(addVehicleButton);
        actionButtonPanel.add(editVehicleButton);
        actionButtonPanel.add(deleteVehicleButton);
        actionButtonPanel.add(back);

        addVehicleButton.addActionListener(e->{
            frame.dispose();
            new AddVehicleUI();
        });
        editVehicleButton.addActionListener(e->{});
        editVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jt.getSelectedRow();
                // Check if the selected column is the ID column (index 0)

                Integer id = Integer.parseInt((String) jt.getValueAt(selectedRow,0));

                if (selectedRow != -1) {
                    frame.dispose();
                    new EditVehicleUI(id);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a row to Update.");
                }
            }
        });

        back.addActionListener(e->{
            frame.dispose();
            new HomeUI();
        });
        deleteVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jt.getSelectedRow();
                if (selectedRow != -1) {
                    int column = 0;
                    int row = jt.getSelectedRow();
                    String value = jt.getModel().getValueAt(row, column).toString();
                    vehicleService.delete(Long.valueOf(value));
                    dtm.removeRow(selectedRow);

                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
                }
            }
        });

        searchTf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String[][] data = vehicleService.searchByName(searchTf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, column);
                jt.setModel(dtm);

            }
        });

        frame.setLayout(new GridLayout(1,2,150,5));

        frame.add(tblAndSearchPanel);
        frame.add(actionButtonPanel);



        // basic properties
        frame.setSize(1300,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }
}
