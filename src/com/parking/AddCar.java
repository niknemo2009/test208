package com.parking;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddCar extends JFrame{
    //Поля форми
    private JTextField licencePlateField;
    private JTextField ownerField;
    private JTextField placeForParkingField;
    private JButton readyButton;
    private JPanel mainPanelAdd;
    private JButton returnButton;
    private JTextField dateField;
    private JTextField timeField;
    private JLabel headerAdd;
    private JLabel labelLicencePlate;
    private JLabel labelOwner;
    private JLabel labelParkingPlace;
    private JLabel labelDate;
    private JLabel labelTime;

    //Validation
    private boolean valid = false;
    private boolean licencePlateIsValid = false;
    private boolean ownerIsValid = false;
    private boolean placeIsValid = false;
    private boolean dateIsValid = false;
    private boolean timeIsValid = false;

    public AddCar(){
        setContentPane(mainPanelAdd);

        //Оформлення
        headerAdd.setFont(ProgramFonts.CAPTIONS.getFont());
        headerAdd.setForeground(ProgramColors.CAPTIONS.getColor());

        labelOwner.setFont(ProgramFonts.LABELS.getFont());
        ownerField.setFont(ProgramFonts.LABELS.getFont());

        labelParkingPlace.setFont(ProgramFonts.LABELS.getFont());
        placeForParkingField.setFont(ProgramFonts.LABELS.getFont());

        labelLicencePlate.setFont(ProgramFonts.LABELS.getFont());
        licencePlateField.setFont(ProgramFonts.LABELS.getFont());

        labelDate.setFont(ProgramFonts.LABELS.getFont());
        dateField.setFont(ProgramFonts.LABELS.getFont());

        labelTime.setFont(ProgramFonts.LABELS.getFont());
        timeField.setFont(ProgramFonts.LABELS.getFont());

        readyButton.setFont(ProgramFonts.BUTTONS.getFont());
        readyButton.setBackground(ProgramColors.BUTTONS.getColor());

        returnButton.setFont(ProgramFonts.BUTTONS.getFont());
        returnButton.setBackground(ProgramColors.BUTTONS.getColor());

        //VALIDATION
        licencePlateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(licencePlateField.getText().trim().length() == 8){
                    licencePlateIsValid = true;
                    licencePlateField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    licencePlateIsValid = false;
                    licencePlateField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = licencePlateIsValid && ownerIsValid && placeIsValid && dateIsValid && timeIsValid;
            }
        });
        ownerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(nameIsValid(ownerField.getText())){
                    ownerIsValid = true;
                    ownerField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    ownerIsValid = false;
                    ownerField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = licencePlateIsValid && ownerIsValid && placeIsValid && dateIsValid && timeIsValid;
            }
        });
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

                valid = licencePlateIsValid && ownerIsValid && placeIsValid && dateIsValid && timeIsValid;
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

                valid = licencePlateIsValid && ownerIsValid && placeIsValid && dateIsValid && timeIsValid;
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

                valid = licencePlateIsValid && ownerIsValid && placeIsValid && dateIsValid && timeIsValid;
            }
        });
    }

    //Методи для роботи з класом
    public void clearForm(){
        licencePlateField.setText(null);
        placeForParkingField.setText(null);
        ownerField.setText(null);
        dateField.setText(null);
        timeField.setText(null);
    }
    public JPanel getMainPanelAdd() {
        return mainPanelAdd;
    }
    public JButton getReturnButton() {
        return returnButton;
    }
    public JButton getReadyButton() {
        return readyButton;
    }
    public JTextField getLicencePlateField() {
        return licencePlateField;
    }
    public JTextField getOwnerField() {
        return ownerField;
    }
    public JTextField getDateField() {
        return dateField;
    }
    public JTextField getTimeField() {
        return timeField;
    }
    public JTextField getPlaceForParkingField() {
        return placeForParkingField;
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
