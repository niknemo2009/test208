package com.parking;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RemoveCar extends JFrame{
    //Elements
    private JButton readyButton;
    private JTextField placeForParkingField;
    private JPanel mainPanelRemove;
    private JButton returnButton;
    private JTextField dateField;
    private JTextField timeField;
    private JLabel headerRemove;
    private JLabel labelPlace;
    private JLabel labelDate;
    private JLabel labelTime;

    //Validation
    private boolean valid = false;
    private boolean placeIsValid = false;
    private boolean dateIsValid = false;
    private boolean timeIsValid = false;

    public RemoveCar(ParkingTableModel parkingTableModel){
        setContentPane(mainPanelRemove);

        //Оформлення
        headerRemove.setFont(ProgramFonts.CAPTIONS.getFont());
        headerRemove.setForeground(ProgramColors.CAPTIONS.getColor());

        labelPlace.setFont(ProgramFonts.LABELS.getFont());
        placeForParkingField.setFont(ProgramFonts.LABELS.getFont());

        labelTime.setFont(ProgramFonts.LABELS.getFont());
        timeField.setFont(ProgramFonts.LABELS.getFont());

        labelDate.setFont(ProgramFonts.LABELS.getFont());
        dateField.setFont(ProgramFonts.LABELS.getFont());

        readyButton.setFont(ProgramFonts.BUTTONS.getFont());
        readyButton.setBackground(ProgramColors.BUTTONS.getColor());

        returnButton.setFont(ProgramFonts.BUTTONS.getFont());
        returnButton.setBackground(ProgramColors.BUTTONS.getColor());

        //VALIDATION
        placeForParkingField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(placeForParkingField.getText().trim().length() > 0 && isIntNumber(placeForParkingField.getText())){
                    placeIsValid = true;
                    placeForParkingField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    placeIsValid = false;
                    placeForParkingField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = placeIsValid && dateIsValid && timeIsValid;
            }
        });
        dateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(isValidDate(dateField.getText())){
                    dateIsValid = true;
                    dateField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    dateIsValid = false;
                    dateField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = placeIsValid && dateIsValid && timeIsValid;
            }
        });
        timeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(isValidTime(timeField.getText())){
                    timeIsValid = true;
                    timeField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    timeIsValid = false;
                    timeField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = placeIsValid && dateIsValid && timeIsValid;
            }
        });
    }

    //Методи для роботи з класом
    public void clearForm(){
        placeForParkingField.setText(null);
        dateField.setText(null);
        timeField.setText(null);
    }
    public JPanel getMainPanelRemove() {
        return mainPanelRemove;
    }
    public JTextField getPlaceForParkingField() {
        return placeForParkingField;
    }
    public JTextField getTimeField() {
        return timeField;
    }
    public JTextField getDateField() {
        return dateField;
    }
    public JButton getReadyButton() {
        return readyButton;
    }
    public JButton getReturnButton() {
        return returnButton;
    }
    public boolean isValid(){
        return valid;
    }

    //Допоміжні методи
    private  boolean isIntNumber(String str){
        int digits=0;

        for(int i = 0 ; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                digits++;
            }
        }

        return digits == str.length();
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
    private boolean isValidTime(String time){
        int properSymbols = 0;
        if(time.trim().length() == 8){
            for(int i = 0; i < time.trim().length(); i++){
                if(Character.isDigit(time.trim().charAt(i)) || time.trim().charAt(i) == ':'){
                    properSymbols++;
                }
            }

            return properSymbols == time.trim().length();
        }else{
            return false;
        }
    }
}
