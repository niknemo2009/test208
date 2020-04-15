package com.parking;


import java.awt.*;

public enum ProgramColors {
    BACKGROUND(Color.WHITE),
    HEADERS(new Color(184, 33, 71)),
    CAPTIONS(new Color(85, 181, 104)),
    BUTTONS(new Color(191, 230, 179)),
    TABLE_HEADER(new Color(255, 235, 179)),
    TRUE(new Color(79, 212, 38)),
    FALSE(new Color(212, 47, 38));

    private Color color;
    ProgramColors(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
