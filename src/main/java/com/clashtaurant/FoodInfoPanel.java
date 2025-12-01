package com.clashtaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Closeable;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class FoodInfoPanel extends JPanel {
    Closeable closeable;
    public void addCloseable(Closeable c) {
        closeable = c;
    }
    FoodInfoPanel(FoodItem foodItem, Order order) {
        setLayout(null);
        setBounds(0, 100, 1000, 700);
        setBackground(Color.WHITE);
        Image scaled;
        java.net.URL imgUrl = getClass().getResource(foodItem.getImagePath());
        if (imgUrl != null) {
            ImageIcon imageIcon = new ImageIcon(imgUrl);
            Image resizableImage = imageIcon.getImage();
            scaled = resizableImage.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
        } else {
            // Fallback placeholder if resource not found
            BufferedImage placeholder = new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = placeholder.createGraphics();
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(0, 0, 700, 700);
            g2.setColor(Color.DARK_GRAY);
            g2.drawString("No Image", 60, 100);
            g2.dispose();
            scaled = placeholder;
        }
        JLabel image = new JLabel(new ImageIcon(scaled));
        image.setBounds(0, 0, 700, 700);
        this.add(image);

        JButton close = new ClashButton("Close", 300, 100);
        close.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    closeable.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        close.setBounds(700, 600, 300, 100);
        this.add(close);

        JLabel name = new JLabel(foodItem.getName());
        name.setBounds(700, 0, 300, 100);
        name.setHorizontalAlignment(JLabel.CENTER);
        this.add(name);

        JLabel description = new JLabel("<html>"+foodItem.getDescription()+"</html>");
        description.setBounds(700, 100, 300, 100);
        this.add(description);

        JButton small = new ClashButton("Small: $" + foodItem.getSPrice(), 300, 100);
        small.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FoodItem item = (FoodItem) foodItem.clone();
                item.setSize(Size.S);
                order.getOrderContents().add(item);
                try {
                    closeable.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        small.setBounds(700, 200, 300, 100);
        this.add(small);

        JButton medium = new ClashButton("Medium: $" + foodItem.getMPrice(), 300, 100);
        medium.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FoodItem item = (FoodItem) foodItem.clone();
                item.setSize(Size.M);
                order.getOrderContents().add(item);
                try {
                    closeable.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        medium.setBounds(700, 300, 300, 100);
        this.add(medium);

        JButton large = new ClashButton("Large: $" + foodItem.getLPrice(), 300, 100);
        large.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FoodItem item = (FoodItem) foodItem.clone();
                item.setSize(Size.L);
                order.getOrderContents().add(item);
                try {
                    closeable.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        large.setBounds(700, 400, 300, 100);
        this.add(large);

        this.setVisible(true);
    }
}
