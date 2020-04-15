package com.parking;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortCars extends JFrame{
    private JRadioButton byCar;
    private JRadioButton byOwner;
    private JButton readyButton;
    private JPanel mainPanelSort;
    private JButton returnButton;
    private JLabel headerSort;

    public SortCars(){
        setContentPane(mainPanelSort);

        //Оформлення
        headerSort.setFont(ProgramFonts.CAPTIONS.getFont());
        headerSort.setForeground(ProgramColors.CAPTIONS.getColor());

        byOwner.setFont(ProgramFonts.CHECKBOX.getFont());
        byOwner.setBackground(ProgramColors.BACKGROUND.getColor());

        byCar.setFont(ProgramFonts.CHECKBOX.getFont());
        byCar.setBackground(ProgramColors.BACKGROUND.getColor());

        readyButton.setFont(ProgramFonts.BUTTONS.getFont());
        readyButton.setBackground(ProgramColors.BUTTONS.getColor());

        returnButton.setFont(ProgramFonts.BUTTONS.getFont());
        returnButton.setBackground(ProgramColors.BUTTONS.getColor());

        //Можна обрати тільки один з варіантів
        byOwner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(byOwner.isSelected()) byCar.setSelected(false);
            }
        });
        byCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(byCar.isSelected()) byOwner.setSelected(false);
            }
        });
    }

    public JPanel getMainPanelSort() {
        return mainPanelSort;
    }
    public JRadioButton getByCar() {
        return byCar;
    }
    public JRadioButton getByOwner() {
        return byOwner;
    }
    public JButton getReadyButton() {
        return readyButton;
    }
    public JButton getReturnButton() {
        return returnButton;
    }
}
