//package org.carrental.ui;
//
//import com.toedter.calendar.JDateChooser;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.carrental.service.BookingService;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.io.IOException;
//
//public class CommissionReportUI {
//    public final BookingService bookingService;
//    JDateChooser StartDateChooser = new JDateChooser();
//    JDateChooser EndDateChooser = new JDateChooser();
//    private JFrame frame;
//    private JPanel tblAndSearchPanel;
//    private JTable jt;
//    private JTextField nameTf;
//    public CommissionReportUI() {
//        bookingService = new BookingService();
//        JFrame frame = new JFrame("Car Rental APP - Customer Panel");
//
//        JPanel tblAndSearchPanel = new JPanel();
//        tblAndSearchPanel.setBackground(Color.GRAY);
//        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
//
//        JLabel nameLb = new JLabel("NAME");
//        nameTf = new JTextField(20);
//
//        JLabel startDateLb = new JLabel("Start Date");
//        StartDateChooser.setPreferredSize(new Dimension(150, StartDateChooser.getPreferredSize().height));
//
//        JLabel endDateLb = new JLabel("End Date");
//        EndDateChooser.setPreferredSize(new Dimension(150, EndDateChooser.getPreferredSize().height));
//
//
//
//        JButton generateRecord = new JButton("Generate");
//        JButton pdf = new JButton("Generate-PDF");
//        JButton back = new JButton("BACK");
//
//        tblAndSearchPanel.add(nameLb);
//        tblAndSearchPanel.add(nameTf);
//        tblAndSearchPanel.add(startDateLb);
//        tblAndSearchPanel.add(StartDateChooser);
//        tblAndSearchPanel.add(endDateLb);
//        tblAndSearchPanel.add(EndDateChooser);
//        tblAndSearchPanel.add(generateRecord);
//        tblAndSearchPanel.add(pdf);
//        tblAndSearchPanel.add(back);
//
//        frame.add(tblAndSearchPanel);
//
//        back.addActionListener(e->{
//            frame.dispose();
//            new ReportsUI();
//        });
//
//        nameTf.addKeyListener(new KeyAdapter() {
//            public void keyTyped(KeyEvent e) {
//                if (nameTf.getText().length() >= 100) {
//                    e.consume(); // Ignore the input if the length exceeds 100
//                }
//            }
//        });
//        generateRecord.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showTableData();
//            }
//        });
//
//        // basic properties
//        frame.setSize(1300,700);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//
//    }
//
//    private void showTableData() {
//        String data[][] = bookingService.getCommissionForJTabel(StartDateChooser.getDate(), EndDateChooser.getDate(),nameTf.getText());
//        String column[] = {"NAME", "DATE", "PRICE","Commission"};
//        DefaultTableModel dtm = new DefaultTableModel(data, column);
//        jt = new JTable(dtm);
//        JScrollPane sp = new JScrollPane(jt);
//        tblAndSearchPanel.add(sp);
//        tblAndSearchPanel.revalidate();
//        tblAndSearchPanel.repaint();
//    }
//}



package org.carrental.ui;


import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import org.carrental.service.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class CommissionReportUI {
    public final BookingService bookingService = new BookingService();
    JDateChooser StartDateChooser = new JDateChooser();
    JDateChooser EndDateChooser = new JDateChooser();
    private JFrame frame;
    private JPanel tblAndSearchPanel;
    private JTable jt;
    public CommissionReportUI() {
        frame = new JFrame("Car Rental APP - Monthly Report");
        tblAndSearchPanel = new JPanel(new GridBagLayout());
        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        Label startLb = new Label("Start Date");
        StartDateChooser.setPreferredSize(new Dimension(150, StartDateChooser.getPreferredSize().height));

        Label endLb = new Label("End Date");
        EndDateChooser.setPreferredSize(new Dimension(150, EndDateChooser.getPreferredSize().height));

        JButton generateRecord = new JButton("Generate");
        JButton pdf = new JButton("Generate-PDF");
        JButton back = new JButton("BACK");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 1;
        tblAndSearchPanel.add(startLb, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        tblAndSearchPanel.add(StartDateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        tblAndSearchPanel.add(endLb, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        tblAndSearchPanel.add(EndDateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        tblAndSearchPanel.add(generateRecord, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        tblAndSearchPanel.add(back, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        tblAndSearchPanel.add(pdf, gbc);
        generateRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTableData();
            }
        });
//        pdf.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                generatePDF();
//            }
//        });
        back.addActionListener(e -> {
            frame.dispose();
            new ReportsUI();
        });
        frame.add(tblAndSearchPanel);

        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
        private void showTableData() {
            Date startDate = new java.sql.Date(StartDateChooser.getDate().getTime());
            Date endDate = new java.sql.Date(EndDateChooser.getDate().getTime());

            String data[][] = bookingService.getCommissionForJTabel(startDate, endDate);
            String column[] = {"Owner-Name", "Total Commission"};
            DefaultTableModel dtm = new DefaultTableModel(data, column);
            jt = new JTable(dtm);
            JScrollPane sp = new JScrollPane(jt);
            tblAndSearchPanel.add(sp);
            frame.revalidate();
            frame.repaint();
        }
//        JFrame frame = new JFrame("Rental Car App | Monthly Report");
//
//        frame.setSize(1000, 700);
//        frame.setLayout(new GridLayout(1, 2, 40, 5));
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//
//        JPanel tablePanel = new JPanel();
//
//        String[][] data = {};
//        String[] headers = {"Owner-Name", "Total Commission"};
//        DefaultTableModel dtm = new DefaultTableModel(data, headers);
//        JTable jt = new JTable(dtm);
//        JScrollPane sp = new JScrollPane(jt);
//
//        tablePanel.add(sp);
//
//        JPanel datePanel = new JPanel(new GridLayout(2, 2, 10, 10));
//
//
//
//        JCalendar startDate = new JCalendar();
//        JCalendar endDate = new JCalendar();
//
//        JButton done = new JButton("Done");
//        JButton back = new JButton("Back");
//
//
//        datePanel.add(startDate);
//        datePanel.add(endDate);
//        datePanel.add(done);
//        datePanel.add(back);
//
//        frame.add(datePanel);
//        frame.add(tablePanel);
//
//        done.addActionListener(e -> {
//            java.util.Date startDateUtil = startDate.getDate();
//            java.util.Date endDateUtil = endDate.getDate();
//
//            // Convert java.util.Date to java.sql.Date
//            java.sql.Date startDateSql = new java.sql.Date(startDateUtil.getTime());
//            java.sql.Date endDateSql = new java.sql.Date(endDateUtil.getTime());
//            String[][] rows = bookingService.getCommissionForJTabel(startDateSql, endDateSql);
//            DefaultTableModel tableModel = new DefaultTableModel(rows, headers);
//            jt.setModel(tableModel);
//
//        });
//
//        back.addActionListener(e -> {
//            frame.dispose();
//            new ReportsUI();
//        });

    }
//    private void generatePDF() {
//        try {
//            if (jt == null) {
//                JOptionPane.showMessageDialog(frame, "Please generate the table first.", "No Table Data", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//
//            // Create a new PDF document
//            PDDocument document = new PDDocument();
//            PDPage page = new PDPage(PDRectangle.A4);
//            document.addPage(page);
//
//            // Create a PDF content stream
//            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//
//                // Set the font and position for writing the title
//                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
//                float titleX = 250; // Adjust the X coordinate for centering the title
//                float titleY = page.getMediaBox().getHeight() - 50; // Adjust the Y coordinate for the title position
//                contentStream.beginText();
//                contentStream.newLineAtOffset(titleX, titleY);
//                contentStream.showText("Commission Report"); // Title added here
//                contentStream.endText();
//
//                // Add a gap of 30 points between the title and the table
//                float tableYStart = titleY - 30;
//
//                // Set the font and position for writing the table content
//                float margin = 50;
//                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
//                float yPosition = tableYStart;
//                int rowsPerPage = 10; // Number of rows per page
//                int numRows = jt.getRowCount();
//                int numCols = jt.getColumnCount();
//
//                // Write table headers to PDF
//                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//                int columnWidth = 110; // Adjust this value to change the width of each column
//                for (int i = 0; i < numCols; i++) {
//                    String header = jt.getColumnName(i);
//                    int xOffset = (int) (margin + i * columnWidth);
//                    contentStream.beginText();
//                    contentStream.newLineAtOffset(xOffset, yPosition);
//                    contentStream.showText(header);
//                    contentStream.endText();
//                }
//                yPosition -= 20; // Move down by 20 points for the table header
//
//                // Write table content to PDF
//                for (int i = 0; i < numRows; i++) {
//                    if (i % rowsPerPage == 0) {
//                        // Add table title if desired
//                        contentStream.beginText();
//                        contentStream.newLineAtOffset(margin, yPosition);
//                        contentStream.showText("Table Data");
//                        contentStream.endText();
//                        yPosition -= 20; // Move down by 20 points for the table title
//                    }
//
//                    for (int j = 0; j < numCols; j++) {
//                        String cellValue = jt.getValueAt(i, j).toString();
//                        int xOffset = (int) (margin + j * columnWidth);
//                        contentStream.beginText();
//                        contentStream.newLineAtOffset(xOffset, yPosition);
//                        contentStream.showText(cellValue);
//                        contentStream.endText();
//                    }
//                    yPosition -= 20; // Move down by 20 points for each row
//                }
//            }
//            // Save the PDF document
//            document.save("table_data.pdf");
//            document.close();
//
//            // Automatically open the generated PDF
//            openPDF("table_data.pdf");
//
//            JOptionPane.showMessageDialog(frame, "PDF generated successfully.");
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(frame, "Error generating PDF: " + ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
//
//    // Utility method to open a file with the default application
//    private void openPDF(String filePath) {
//        try {
//            File file = new File(filePath);
//            if (file.exists()) {
//                if (Desktop.isDesktopSupported()) {
//                    Desktop.getDesktop().open(file);
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Desktop is not supported to open the PDF.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(frame, "PDF file not found.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(frame, "Error opening the PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
