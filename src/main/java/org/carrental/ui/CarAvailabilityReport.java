package org.carrental.ui;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.carrental.service.BookingService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CarAvailabilityReport {
    public final BookingService bookingService = new BookingService();
    public final VehicleService vehicleService = new VehicleService();
    private JFrame frame;
    private JPanel tblAndSearchPanel;
    private JTable jt;

    public CarAvailabilityReport() {

        frame = new JFrame("Car Rental APP - Car Availablity Report");
        tblAndSearchPanel = new JPanel(new GridBagLayout());
        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton generateRecord = new JButton("Check");
        JButton pdf = new JButton("Generate-PDF");
        JButton back = new JButton("BACK");



        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);


        gbc.gridx = 0;
        gbc.gridy = 1;
        tblAndSearchPanel.add(generateRecord, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        tblAndSearchPanel.add(back, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
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
        back.addActionListener(e->{
            frame.dispose();
            new ReportsUI();
        });

        frame.add(tblAndSearchPanel);

        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    private void showTableData() {
        String data[][] = vehicleService.getAllCARAVAILABILITYForJTabel();
        String column[] = {"V-Number", "V-Name", "Owner","Brand","Color"};
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

                // Set the font and position for writing the title
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                float titleX = 250; // Adjust the X coordinate for centering the title
                float titleY = page.getMediaBox().getHeight() - 50; // Adjust the Y coordinate for the title position
                contentStream.beginText();
                contentStream.newLineAtOffset(titleX, titleY);
                contentStream.showText("Car Availability Report"); // Title added here
                contentStream.endText();

                // Add a gap of 30 points between the title and the table
                float tableYStart = titleY - 30;

                // Set the font and position for writing the table content
                float margin = 50;
                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                float yPosition = tableYStart;
                int rowsPerPage = 10; // Number of rows per page
                int numRows = jt.getRowCount();
                int numCols = jt.getColumnCount();

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
                    }
                    yPosition -= 20; // Move down by 20 points for each row
                }
            }
            // Save the PDF document
            document.save("table_data.pdf");
            document.close();

            // Automatically open the generated PDF
            openPDF("table_data.pdf");

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
}
