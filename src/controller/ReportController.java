package controller;

import db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.sql.Connection;

public class ReportController {

    // Method to generate the Daily Sales Report
    public static void generateDailySalesReport() {
        try {
            // 1. Get the Database Connection
            Connection conn = DBConnection.getInstance().getConnection();

            // 2. Path to the report template file (.jrxml)
            // Note: We will create a 'reports' folder inside 'src' later
            String reportPath = "src/reports/DailySalesReport.jrxml";

            // 3. Compile the JRXML file into a JasperReport object
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            // 4. Fill the report with data directly from the Database connection
            // 'null' is passed because we are not sending any external parameters right now
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

            // 5. Display the generated report in a Viewer Window
            // 'false' ensures that closing the report doesn't close the entire POS system
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Generating Report: " + e.getMessage());
        }
    }
}