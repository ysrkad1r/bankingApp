package org.example.app;

import org.example.ui.customer.MainFrame;
import javax.swing.*;

public class BankCoreApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame view = new MainFrame();
            view.setVisible(true);    // Görüntüle
        });
    }

}