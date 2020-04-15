package com.parking;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        //Підключення бази даних
        String HOST = "jdbc:mysql://localhost:3306/private_parking";
        String USERNAME = "root";
        String PASSWORD = "root";
        DBController dbController = new DBController(HOST, USERNAME, PASSWORD);

        //Запуск графічного інтерфейсу
        new Startup(dbController);
    }
}
