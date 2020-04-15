package com.parking;


import java.awt.*;

public enum ProgramFonts {
    HEADERS(new Font("Arial", Font.BOLD, 22)),
    CAPTIONS(new Font("Arial", Font.BOLD, 20)),
    LABELS(new Font("Arial", Font.PLAIN, 18)),
    BUTTONS(new Font("Arial", Font.PLAIN, 16)),
    CHECKBOX(new Font("Arial", Font.ITALIC, 16)),
    TABLE_HEADER(new Font("Arial", Font.BOLD, 16)),
    TABLE_CELL(new Font("Arial", Font.PLAIN, 16));

    private Font font;

    ProgramFonts(Font font){
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}
