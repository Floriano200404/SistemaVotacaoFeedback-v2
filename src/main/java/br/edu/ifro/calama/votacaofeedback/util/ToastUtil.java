/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class ToastUtil extends JDialog {

    public enum ToastType {
        SUCCESS, ERROR
    }

    public static final int DURATION = 2500; 
   
    public ToastUtil(JFrame owner, String message, ToastType type) {
        super(owner);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setFocusable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(350, 50));
        if (type == ToastType.SUCCESS) {
            panel.setBackground(new Color(34, 177, 76));
        } else {
            panel.setBackground(new Color(237, 28, 36));
        }

        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, gbc);

        add(panel);
        pack();

        if (owner != null) {
            Point ownerLocation = owner.getLocationOnScreen();
            int x = ownerLocation.x + owner.getWidth() - getWidth() - 15;
            int y = ownerLocation.y + owner.getHeight() - getHeight() - 40;
            setLocation(x, y);
        } else {
            Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            int x = screenBounds.width - getWidth() - 15;
            int y = screenBounds.height - getHeight() - 15;
            setLocation(x, y);
        }
    }

    public ToastUtil(JFrame owner, JComponent anchor, String message, ToastType type) {
    this(owner, message, type);

    if (anchor != null) {
        Point anchorLocation = anchor.getLocationOnScreen();
        int anchorX = anchorLocation.x;
        int anchorY = anchorLocation.y;
        int anchorWidth = anchor.getWidth();
        int anchorHeight = anchor.getHeight();
        
        int toastWidth = getWidth();

        int x = anchorX + (anchorWidth / 2) - (toastWidth / 2);
        
        int y = anchorY + anchorHeight + 10;
        
        this.setLocation(x, y);
    }
    }

    public void display() {
        Timer timer = new Timer(DURATION, e -> {
            float opacity = 1.0f;
            while (opacity > 0.0f) {
                setOpacity(opacity);
                opacity -= 0.1f;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                }
            }
            dispose();
        });
        timer.setRepeats(false);
        timer.start();

        setOpacity(1.0f);
        setVisible(true);
    }
}