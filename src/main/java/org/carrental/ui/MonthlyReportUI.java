package org.carrental.ui;

import com.toedter.calendar.JDateChooser;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.carrental.service.BookingService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class MonthlyReportUI {
    public final BookingService bookingService = new BookingService();
    JDateChooser StartDateChooser = new JDateChooser();
    JDateChooser EndDateChooser = new JDateChooser();
    private JFrame frame;
    private JPanel tblAndSearchPanel;
    private JTable jt;

    public MonthlyReportUI() {
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
        pdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePDF();
            }
        });
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

        String data[][] = bookingService.getAllMonthlyForJTabel(startDate, endDate);
        String column[] = {"V-Name", "C-Name", "BOOKING_DATE", "PRICE","Commission"};
        DefaultTableModel dtm = new DefaultTableModel(data, column);
        jt = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jt);
        tblAndSearchPanel.add(sp);
        frame.revalidate();
        frame.repaint();
    }

    private void generatePDF() {
        try {
            if (jt == null) {
                JOptionPane.showMessageDialog(frame, "Please generate the table first.", "No Table Data", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create a new PDF document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a PDF content stream
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Set the font and position for writing the table content
                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                float yPosition = yStart;
                int rowsPerPage = 10; // Number of rows per page
                int numRows = jt.getRowCount();
                int numCols = jt.getColumnCount();

                // Write the selected Start Date and End Date at the top of the PDF
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();

                // Format the Start Date and End Date to display only the date (without the time)
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = dateFormat.format(StartDateChooser.getDate());
                String endDate = dateFormat.format(EndDateChooser.getDate());

                float startX = margin;
                float startY = yStart + 30;
                contentStream.newLineAtOffset(startX, startY);
                contentStream.showText("Monthly Report"); // Title added here

                float subtitleY = startY - 20;
                contentStream.newLineAtOffset(0, subtitleY - startY);
                contentStream.showText("Start Date: " + startDate);
                contentStream.newLineAtOffset(250, 0);
                contentStream.showText("End Date: " + endDate);

                contentStream.endText();
                yPosition -= 70; // Move down by 70 points for the header section

                // Write table headers to PDF
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                int columnWidth = 110; // Adjust this value to change the width of each column
                for (int i = 0; i < numCols; i++) {
                    String header = jt.getColumnName(i);
                    int xOffset = (int) (margin + i * columnWidth);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(xOffset, yPosition);
                    contentStream.showText(header);
                    contentStream.endText();
                }
                yPosition -= 20; // Move down by 20 points for the table header

                // Write table content to PDF
                double totalSum = 0.0;
                double totalCommission = 0.0;
                for (int i = 0; i < numRows; i++) {
                    if (i % rowsPerPage == 0) {
                        // Add table title if desired
                        contentStream.beginText();
                        contentStream.newLineAtOffset(margin, yPosition);
                        contentStream.showText("Table Data");
                        contentStream.endText();
                        yPosition -= 20; // Move down by 20 points for the table title
                    }

                    for (int j = 0; j < numCols; j++) {
                        String cellValue = jt.getValueAt(i, j).toString();
                        int xOffset = (int) (margin + j * columnWidth);
                        contentStream.beginText();
                        contentStream.newLineAtOffset(xOffset, yPosition);
                        contentStream.showText(cellValue);
                        contentStream.endText();

                        // Calculate the total sum of prices (assuming the price is in the last column, adjust the index if needed)
                        if (j == numCols - 2) {
                            double price = Double.parseDouble(cellValue);
                            totalSum += price;
                        }
                        if (j == numCols - 1) {
                            double comision = Double.parseDouble(cellValue);
                            totalCommission += comision;
                        }
                    }

                    yPosition -= 20; // Move down by 20 points for each row
                }

                // Add a box at the bottom of the table to display the sum of prices and total commission
                contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
                contentStream.addRect(margin, 50, tableWidth, 30);
                contentStream.fill();

                // Set the font and color for the text in the box
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.setNonStrokingColor(Color.BLACK);

                // Write the sum of prices and total commission
                contentStream.beginText();
                contentStream.newLineAtOffset(margin + 10, 65);
                contentStream.showText("Total Sum of Prices: " + totalSum);
                contentStream.newLineAtOffset(0, -15); // Move down for the next line
                // Assuming total commission is calculated and stored in the 'totalCommission' variable
                contentStream.showText("Total Sum of Commissions: " + totalCommission);

//                double totalCommission = totalSum * 0.1; // Calculate the commission as 10% of the total sum (adjust the percentage as needed)
//                contentStream.showText("Total Commission: " + totalCommission);
                contentStream.endText();

            }
            // Save the PDF document
            document.save("monthly_report.pdf");
            document.close();

            // Automatically open the generated PDF
            openPDF("monthly_report.pdf");

            JOptionPane.showMessageDialog(frame, "PDF generated successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error generating PDF: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Utility method to open a file with the default application
    private void openPDF(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(frame, "Desktop is not supported to open the PDF.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "PDF file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error opening the PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new MonthlyReportUI();
    }
}




