package com.clashtaurant;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import static com.clashtaurant.GetFont.getClashFont;

public class GUI extends JFrame {
    private final JPanel tabs;
    private JPanel mainPanel;

    //ImageIcon eating = new ImageIcon("src/cat eating.png");
    //Image originalEating = eating.getImage();
    //int maxWidth = 300;
    //int maxHeight = 300;
    // originalW = originalEating.getWidth(null);
    //int originalH = originalEating.getHeight(null);
    //double scaleFactor = Math.min((double) maxWidth / originalW, (double) maxHeight / originalH);
    //int newH = (int) (originalH * scaleFactor);
    //Image newscaledImage1 = originalEating.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    //ImageIcon scaledEating = new ImageIcon(newscaledImage1);

    public GUI(Order order) {
        UIManager.put("Button.font", getClashFont());
        UIManager.put("Label.font", getClashFont());
        // Ensure menu items are registered before building UI
        try { MenuRegistry.init(); } catch (Throwable ignored) {}
        //Create frame
        this.setBounds(0, 0, 1015, 839);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        //Create Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBounds(0, 0, 1000, 100);
        topPanel.setBackground(Color.GREEN);
        this.add(topPanel);

        //Create Tabs panel;
        tabs = new JPanel();
        tabs.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        tabs.setBounds(0, 100, 100, 525);
        tabs.setBackground(Color.RED);
        this.add(tabs);

        //Make tab buttons


        JButton mainsTab = new JButton("Mains Tab");
        JButton dessertsTab = new JButton("Desserts Tab");
        JButton drinksTab = new JButton("Drinks Tab");
        JButton orderTab = new JButton("ORDER");
        mainsTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanels();
                System.out.println("Mains Tab");
                makeMainPanel(order, FoodCategory.MAIN);
                GUI.super.repaint();
                showPanels();
                GUI.super.repaint();
            }
        });
        dessertsTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanels();
                System.out.println("Desserts Tab");
                makeMainPanel(order, FoodCategory.DESSERT);
                GUI.super.repaint();
                showPanels();
                GUI.super.repaint();
            }
        });
        drinksTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanels();
                System.out.println("Drinks Tab");
                makeMainPanel(order, FoodCategory.DRINK);
                GUI.super.repaint();
                showPanels();
                GUI.super.repaint();
            }
        });
        orderTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Order Tab");
            }
        });
        tabs.add(mainsTab);
        tabs.add(dessertsTab);
        tabs.add(drinksTab);
        tabs.add(orderTab);

        makeMainPanel(order, FoodCategory.MAIN);

        //.add(SidePanel, BorderLayout.WEST);
        setVisible(true);
    }

    public void makeMainPanel(Order order, FoodCategory category) {
        if (mainPanel != null) {
            this.remove(mainPanel);
        }
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 4));
        mainPanel.setBounds(100, 100, 700, 700);
        mainPanel.setBackground(Color.BLUE);
        List<FoodItem> list = switch (category) {
            case MAIN -> FoodItem.getMains();
            case DESSERT -> FoodItem.getDesserts();
            case DRINK -> FoodItem.getDrinks();
        };
        for (FoodItem item : list) {
            FoodItemPanel panel = new FoodItemPanel(item, this, order);
            mainPanel.add(panel);
        }
        this.add(mainPanel);
        mainPanel.setVisible(true);
        repaint();
    }
    public void makeFoodPanel(FoodItem o, Order order) {
        hidePanels();
        FoodInfoPanel foodPanel = new FoodInfoPanel(o, order);
        foodPanel.addCloseable(new Closeable() {
            @Override
            public void close() throws IOException {
                GUI.super.remove(foodPanel);
                GUI.super.repaint();
                showPanels();
            }
        });
        this.add(foodPanel);
        foodPanel.repaint();
        repaint();
    }
    public void hidePanels() {
        mainPanel.setVisible(false);
        tabs.setVisible(false);
        
    }
    public void showPanels() {
        mainPanel.setVisible(true);
        tabs.setVisible(true);
    }
}
