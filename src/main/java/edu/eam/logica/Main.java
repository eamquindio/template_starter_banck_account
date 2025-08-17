package edu.eam.logica;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankAccountSwingApp app = new BankAccountSwingApp();
            app.setVisible(true);
        });
    }
}