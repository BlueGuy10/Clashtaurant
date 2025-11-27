package com.clashtaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class RoundedImagePanel extends JPanel {

    private BufferedImage image;
    private int cornerRadius = 20; // Adjust the corner radius as needed

    public RoundedImagePanel(String imagePath) {
        // Load the image
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set the panel to be non-opaque so the rounded corners can show through
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable anti-aliasing for smoother corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a rounded clipping area
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, 250,250, cornerRadius, cornerRadius);
        g2d.setClip(roundedRect);

        // Draw the image within the clipped area
        if (image != null) {
            g2d.drawImage(image, 0, 0, 200, 200, null);
        } else {
            // Draw a placeholder background if the image fails to load
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fill(roundedRect);
            g2d.setColor(Color.RED);
            g2d.drawString("Image not found", 10, getHeight() / 2);
        }

        // Dispose of the graphics copy
        g2d.dispose();
    }

    // Optional: Add a border that matches the rounded shape
    @Override
    public void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getForeground()); // Use foreground color for border
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2d.dispose();
    }
}

