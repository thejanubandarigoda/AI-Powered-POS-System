package controller;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupController {

    public static void backupDatabase(JFrame parentFrame) {
        // ඔයාගේ Database එකේ විස්තර මෙතන දෙන්න
        String dbUser = "root";
        String dbPass = ""; // Password එකක් තියෙනවා නම් මෙතන දෙන්න (උදා: "1234")
        String dbName = "pos_system"; // ඔයාගේ Database එකේ නම

        // Backup ෆයිල් එකට අද දවසයි වෙලාවයි එක්ක නමක් හැදීම
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String defaultFileName = "POS_Backup_" + date + ".sql";

        // Save කරන තැන තෝරන්න File Chooser එකක් ගැනීම
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Database Backup");
        fileChooser.setSelectedFile(new File(defaultFileName));

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String savePath = fileToSave.getAbsolutePath();

            try {
                // MySQL වල Backup එක ගන්න කමාන්ඩ් එක (mysqldump)
                String executeCmd;

                if (dbPass.isEmpty()) {
                    executeCmd = "mysqldump -u" + dbUser + " " + dbName + " -r \"" + savePath + "\"";
                } else {
                    executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass + " " + dbName + " -r \"" + savePath + "\"";
                }

                // කමාන්ඩ් එක Run කිරීම
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();

                // සාර්ථකද කියලා බැලීම
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