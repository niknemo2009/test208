package com.parking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchCar extends JFrame{
    private JPanel mainPanelSearch;

    //JCheckBox для варінтів пошуку
    private JCheckBox byLicencePlate;
    private JCheckBox byOwner;
    private JCheckBox byTime;

    //JTextField для введених критеріїв
    private JTextField licencePlate;
    private JTextField ownerName;
    private JTextField minTime;
    private JTextField maxTime;

    //Конопки
    private JButton readyButton;
    private JButton returnButton;
    private JLabel chooseHeader;
    private JLabel minLabel;
    private JLabel maxLabel;

    //Status of text fields
    private boolean minTimeIsSelected = false;
    private boolean maxTimeIsSelected = false;

    public SearchCar() {
        setContentPane(mainPanelSearch);

        //Оформлення
        chooseHeader.setFont(ProgramFonts.CAPTIONS.getFont());
        chooseHeader.setForeground(ProgramColors.CAPTIONS.getColor());

        byLicencePlate.setFont(ProgramFonts.LABELS.getFont());
        licencePlate.setFont(ProgramFonts.LABELS.getFont());

        byOwner.setFont(ProgramFonts.LABELS.getFont());
        ownerName.setFont(ProgramFonts.LABELS.getFont());

        byTime.setFont(ProgramFonts.LABELS.getFont());

        maxLabel.setFont(ProgramFonts.LABELS.getFont());
        maxTime.setFont(ProgramFonts.LABELS.getFont());

        minLabel.setFont(ProgramFonts.LABELS.getFont());
        minTime.setFont(ProgramFonts.LABELS.getFont());

        returnButton.setFont(ProgramFonts.BUTTONS.getFont());
        returnButton.setBackground(ProgramColors.BUTTONS.getColor());

        readyButton.setFont(ProgramFonts.BUTTONS.getFont());
        readyButton.setBackground(ProgramColors.BUTTONS.getColor());

        //Запрет снятия флажка
        byLicencePlate.setEnabled(false);
        byOwner.setEnabled(false);
        byTime.setEnabled(false);

        //ВАЛІДАЦІЯ ФОРМИ
        licencePlate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(licencePlate.getText().trim().length() == 8 ){
                    byLicencePlate.setSelected(true);
                    licencePlate.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    byLicencePlate.setSelected(false);
                    licencePlate.setForeground(ProgramColors.FALSE.getColor());
                }
            }
        });
        ownerName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(nameIsValid(ownerName.getText())){
                    byOwner.setSelected(true);
                    ownerName.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    byOwner.setSelected(false);
                    ownerName.setForeground(ProgramColors.FALSE.getColor());
                }
            }
        });
        minTime.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                //Стан поля minTime
                if(isValidDate(minTime.getText())){
                    minTimeIsSelected = true;
                    minTime.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    minTimeIsSelected = false;
                    minTime.setForeground(ProgramColors.FALSE.getColor());
                }

                //Стан checkbox byTime
                if(minTimeIsSelected && maxTimeIsSelected){
                    byTime.setSelected(true);
                }else{
                    byTime.setSelected(false);
                }
            }
        });
        maxTime.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                //Стан поля minTime
                if(isValidDate(maxTime.getText())){
                    maxTimeIsSelected = true;
                    maxTime.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    maxTimeIsSelected = false;
                    maxTime.setForeground(ProgramColors.FALSE.getColor());
                }

                //Стан checkbox byTime
                if (!minTimeIsSelected || !maxTimeIsSelected) {
                    byTime.setSelected(false);
                } else {
                    byTime.setSelected(true);
                }
            }
        });
    }

    //Методи для роботи з класом
    public JPanel getMainPanelSearch() {
        return mainPanelSearch;
    }
    public JTextField getOwnerName() {
        return ownerName;
    }
    public JTextField getLicencePlate() {
        return licencePlate;
    }
    public JTextField getMaxTime() {
        return maxTime;
    }
    public JTextField getMinTime() {
        return minTime;
    }
    public JCheckBox getByOwner() {
        return byOwner;
    }
    public JCheckBox getByLicencePlate() {
        return byLicencePlate;
    }
    public JCheckBox getByTime() {
        return byTime;
    }
    public JButton getReturnButton() {
        return returnButton;
    }
    public JButton getReadyButton() {
        return readyButton;
    }

    //Допоміжні методи
    private boolean nameIsValid(String str){
        if(str.trim().length() > 0){
            for(int i = 0; i < str.trim().length(); i++){
                if(str.trim().charAt(i) == ' '){
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }
    private boolean isValidDate(String date){
        int properSymbols = 0;
        if(date.trim().length() == 10){
            for(int i = 0; i < date.trim().length(); i++){
                if(Character.isDigit(date.trim().charAt(i)) || date.trim().charAt(i) == '/'){
                    properSymbols++;
                }
            }

            return properSymbols == date.trim().length();
        }else{
            return false;
        }
    }
}


