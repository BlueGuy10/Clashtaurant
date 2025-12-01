package com.clashtaurant;

import java.awt.*;
import java.io.InputStream;

public class GetFont {
    static Font getClashFont() {
        try {
            InputStream stream = GetFont.class.getResourceAsStream("/font/YouBlockhead.ttf");
            if (stream != null) {
                Font yourFont = Font.createFont(Font.TRUETYPE_FONT, stream);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(yourFont);
                return yourFont.deriveFont(10f);
            }
        } catch (Exception _) {}
        return Font.getFont("Arial").deriveFont(10f);
    }
}
