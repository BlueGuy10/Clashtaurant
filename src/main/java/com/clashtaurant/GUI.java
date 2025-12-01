package com.clashtaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        UIManager.put("Button.background", Color.decode("#FEB902"));
        // Ensure menu items are registered before building UI
        try {
            MenuRegistry.init();
        } catch (Throwable ignored) {
        }
        //Create frame
        this.setBounds(0, 0, 1000, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.getContentPane().setBackground(Color.WHITE);

        //Create Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBounds(0, 0, 1000, 100);
        topPanel.setBackground(Color.WHITE);
        JLabel name = new JLabel("Clashers: Clash Royale x Food");
        name.setFont(getClashFont().deriveFont(40.0f));
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setBounds(0, 0, 1000, 100);
        topPanel.add(name);
        this.add(topPanel);

        //Create Tabs panel;
        tabs = new JPanel();
        tabs.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        tabs.setBounds(0, 100, 100, 700);
        tabs.setBackground(Color.WHITE);
        this.add(tabs);

        //Make tab buttons


        JButton mainsTab = new ClashButton("Mains", 100, 20);
        JButton dessertsTab = new ClashButton("Desserts", 100, 20);
        JButton drinksTab = new ClashButton("Drinks", 100, 20);
        JButton orderTab = new ClashButton("Order", 100, 20);
        mainsTab.addActionListener(e -> {
            hidePanels();
            makeMainPanel(order, FoodCategory.MAIN);
            GUI.super.repaint();
            showPanels();
            GUI.super.repaint();
        });
        dessertsTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePanels();
                makeMainPanel(order, FoodCategory.DESSERT);
                GUI.super.repaint();
                showPanels();
                GUI.super.repaint();
            }
        });
        drinksTab.addActionListener(e -> {
            hidePanels();
            makeMainPanel(order, FoodCategory.DRINK);
            GUI.super.repaint();
            showPanels();
            GUI.super.repaint();
        });
        orderTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeOrderPanel(order);
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

    private void makeOrderPanel(Order order) {
        remove(mainPanel);
        mainPanel = new JPanel();
        mainPanel.setBounds(100, 100, 700, 700);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        hidePanels();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(100, 100, 300, 700);
        for (FoodItem item :
                order.getOrderContents()) {
            JPanel panelToAdd = new JPanel();
            panelToAdd.setBounds(0, 0, 100, 700);
            FoodItemPanel image = new FoodItemPanel(item);
            image.setBounds(0, 0, 100, 100);
            image.setPreferredSize(new Dimension(100, 100));
            panelToAdd.add(image);
            JLabel info = new JLabel("<html>$" + item.getPrice() + "<br>" + item.getSize() + "</html>");
            info.setBounds(100, 0, 100, 100);
            info.setPreferredSize(new Dimension(100, 100));
            panelToAdd.add(info);
            JButton remove = new ClashButton("Remove", 100, 100);
            remove.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    order.getOrderContents().remove(item);
                    makeOrderPanel(order);
                }
            });
            remove.setBounds(200, 0, 100, 100);
            remove.setPreferredSize(new Dimension(100, 100));
            panelToAdd.add(remove);
            panelToAdd.setPreferredSize(new Dimension(300, 100));
            panel.add(panelToAdd);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0, 0, 400, 700);
        scrollPane.setPreferredSize(new Dimension(400, 700));
        scrollPane.createVerticalScrollBar();
        mainPanel.add(scrollPane);
        JLabel totalPrice = new JLabel("$" + order.calculateFinalPrice());
        totalPrice.setHorizontalAlignment(JLabel.CENTER);
        totalPrice.setBounds(400, 0, 300, 400);
        totalPrice.setFont(getClashFont().deriveFont(40f));
        mainPanel.add(totalPrice);
        JButton checkoutButton = new ClashButton("Checkout", 300, 300);
        checkoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Checkout Successful for $" + order.calculateFinalPrice());
                System.exit(0);
            }
        });
        checkoutButton.setBounds(400, 400, 300, 300);
        mainPanel.add(checkoutButton);
        add(mainPanel);
        GUI.super.repaint();
        showPanels();
        GUI.super.repaint();
    }

    public void makeMainPanel(Order order, FoodCategory category) {
        if (mainPanel != null) {
            this.remove(mainPanel);
        }
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 4));
        mainPanel.setBounds(100, 100, 700, 700);
        mainPanel.setBackground(Color.WHITE);
        List<FoodItem> list = switch (category) {
            case MAIN -> FoodItem.getMains();
            case DESSERT -> FoodItem.getDesserts();
            case DRINK -> FoodItem.getDrinks();
        };
        for (FoodItem item : list) {
            FoodItemPanel panel = new FoodItemPanel(item, this, order);
            panel.setPreferredSize(new Dimension(175, 175));
            panel.setBounds(0,0,175, 175);
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
