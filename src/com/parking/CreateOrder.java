package com.parking;


import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateOrder extends JFrame{
    //Elements
    private JPanel mainPanelReport;
    private JButton readyButton;
    private JTextField ownerField;
    private JTextField startField;
    private JButton returnButton;
    private JLabel headerCreate;
    private JLabel labelOwner;
    private JLabel labelStart;
    private JTextField endField;
    private JLabel labelEnd;

    //Validation
    private boolean valid;
    private boolean ownerIsValid = false;
    private boolean startIsValid = false;
    private boolean endIsValid = false;

    public CreateOrder(){
        this.setContentPane(mainPanelReport);

        //Оформлення
        headerCreate.setFont(ProgramFonts.CAPTIONS.getFont());
        headerCreate.setForeground(ProgramColors.CAPTIONS.getColor());

        labelOwner.setFont(ProgramFonts.LABELS.getFont());
        ownerField.setFont(ProgramFonts.LABELS.getFont());

        labelStart.setFont(ProgramFonts.LABELS.getFont());
        startField.setFont(ProgramFonts.LABELS.getFont());

        labelEnd.setFont(ProgramFonts.LABELS.getFont());
        endField.setFont(ProgramFonts.LABELS.getFont());

        readyButton.setFont(ProgramFonts.BUTTONS.getFont());
        readyButton.setBackground(ProgramColors.BUTTONS.getColor());

        returnButton.setFont(ProgramFonts.BUTTONS.getFont());
        returnButton.setBackground(ProgramColors.BUTTONS.getColor());

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

                valid = ownerIsValid && endIsValid && startIsValid;
            }
        });
        startField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(isValidDate(startField.getText())){
                    startIsValid = true;
                    startField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    startIsValid = false;
                    startField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = ownerIsValid && endIsValid && startIsValid;
            }
        });
        endField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(isValidDate(endField.getText())){
                    endIsValid = true;
                    endField.setForeground(ProgramColors.TRUE.getColor());
                }else{
                    endIsValid = false;
                    endField.setForeground(ProgramColors.FALSE.getColor());
                }

                valid = ownerIsValid && endIsValid && startIsValid;
            }
        });
    }

    //Методи для роботи з класом
    public void clearForm(){
        ownerField.setText(null);
        endField.setText(null);
        startField.setText(null);
    }
    public JPanel getMainPanelReport() {
        return mainPanelReport;
    }
    public JTextField getOwnerField() {
        return ownerField;
    }
    public JTextField getStartField() {
        return startField;
    }
    public JTextField getEndField() {
        return endField;
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
