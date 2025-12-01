package com.clashtaurant;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

class FoodItemPanel extends JPanel {
    public int ITEM_SIZE = 175;
    private BufferedImage image;

    public FoodItemPanel(FoodItem foodItem, GUI gui, Order order) {
        try {
            InputStream imageStream = getClass().getResourceAsStream(foodItem.getImagePath());
            if (imageStream != null) {
                image = ImageIO.read(imageStream);
            } else {
                // Fallback placeholder if resource path is invalid
                image = new BufferedImage(ITEM_SIZE, ITEM_SIZE, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = image.createGraphics();
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillRect(0, 0, ITEM_SIZE, ITEM_SIZE);
                g2.setColor(Color.DARK_GRAY);
                g2.drawString("No Image", 10, ITEM_SIZE / 2);
                g2.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Ensure image is non-null to avoid NPE in paint
            image = new BufferedImage(ITEM_SIZE, ITEM_SIZE, BufferedImage.TYPE_INT_ARGB);
        }
        setPreferredSize(new Dimension(ITEM_SIZE, ITEM_SIZE));
        setBounds(0,0,175,175);
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.makeFoodPanel(foodItem, order);
            }
        });
    }

    public FoodItemPanel(FoodItem foodItem) {
        ITEM_SIZE = 100;
        try {
            InputStream imageStream = getClass().getResourceAsStream(foodItem.getImagePath());
            if (imageStream != null) {
                image = ImageIO.read(imageStream);
            } else {
                // Fallback placeholder if resource path is invalid
                image = new BufferedImage(ITEM_SIZE, ITEM_SIZE, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = image.createGraphics();
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillRect(0, 0, ITEM_SIZE, ITEM_SIZE);
                g2.setColor(Color.DARK_GRAY);
                g2.drawString("No Image", 10, ITEM_SIZE / 2);
                g2.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Ensure image is non-null to avoid NPE in paint
            image = new BufferedImage(ITEM_SIZE, ITEM_SIZE, BufferedImage.TYPE_INT_ARGB);
        }
        setPreferredSize(new Dimension(ITEM_SIZE, ITEM_SIZE));
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, ITEM_SIZE, ITEM_SIZE, 10, 10);
        g2d.setClip(roundedRect);
        g2d.drawImage(image, 0, 0, ITEM_SIZE, ITEM_SIZE, null);

        g2d.dispose();
    }
}
