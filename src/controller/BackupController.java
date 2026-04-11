package controller;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupController {

    public static void backupDatabase(JFrame parentFrame) {
        //  Database Details
        String dbUser = "root";
        String dbPass = ""; // Password 
        String dbName = "pos_system"; // Database Name

        // Backup 
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String defaultFileName = "POS_Backup_" + date + ".sql";

        // Save File Chooser 
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Database Backup");
        fileChooser.setSelectedFile(new File(defaultFileName));

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String savePath = fileToSave.getAbsolutePath();

            try {
                // MySQL Backup 
                String executeCmd;

                if (dbPass.isEmpty()) {
                    executeCmd = "mysqldump -u" + dbUser + " " + dbName + " -r \"" + savePath + "\"";
                } else {
                    executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass + " " + dbName + " -r \"" + savePath + "\"";
                }

              
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();

               
                if (processComplete == 0) {
                    JOptionPane.showMessageDialog(parentFrame,
                            "Database Backup Successful!\nSaved at: " + savePath,
                            "Backup Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parentFrame,
                            "Could not create the backup.\nMake sure MySQL/bin folder is in your System Environment PATH.",
                            "Backup Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentFrame,
                        "Error while taking backup: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
