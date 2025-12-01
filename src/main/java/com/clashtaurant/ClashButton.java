package com.clashtaurant;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClashButton extends JButton {

    private static final long serialVersionUID = 1L;
    private final int wX;
    private final int wY;
    private Color defaultBackgroundColor = Color.decode("#FEB902");
    private Color pressedBackgroundColor = Color.decode("#BB5E12");
    private Color currentBackgroundColor;
    private Color borderColor = Color.BLACK;
    private int arcRadius = 10;

    public ClashButton(String text, int wX, int wY) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        currentBackgroundColor = defaultBackgroundColor;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentBackgroundColor = pressedBackgroundColor;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentBackgroundColor = defaultBackgroundColor;
                repaint();
            }
        });
        this.wX = wX;
        this.wY = wY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(currentBackgroundColor);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcRadius, arcRadius);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcRadius, arcRadius);

        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(getText(), x, y);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(wX, wY);
    }
}
