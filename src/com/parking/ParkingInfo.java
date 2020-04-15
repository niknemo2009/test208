package com.parking;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ParkingInfo extends JFrame {
    Container container;//container for all content
    JPanel topPanel;//menu
    JPanel infoPanel;//table and data about parking
    JPanel controlPanel;//return to authorization and reset data in table
    ParkingTableModel parkingTableModel;//data model for table
    JPanel optionalPanel;//panel from menu
    JButton returnButton;//return to authorization

    public ParkingInfo(DBController dbController, Container outside, JPanel panel){
        //Для додавання елементів
        container = this.getContentPane();
        container.setLayout(new BorderLayout());

        //HEADER PANEL
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        //Header
        JPanel headerText = new JPanel();
        headerText.setLayout(new FlowLayout());
        JLabel headerLabel = new JLabel("Parking info");
        headerText.add(headerLabel);
        topPanel.add(headerText, BorderLayout.NORTH);

        //Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        //Кнопка Park car
        JButton addCar = new JButton("Add");
        addCar.setPreferredSize(new Dimension(ProgramScreenSize.BUTTON_WIDTH.getSize(), ProgramScreenSize.BUTTON_HEIGHT.getSize()));
        addCar.setBackground(ProgramColors.BUTTONS.getColor());
        buttonPanel.add(addCar);
        //Кнопка Drive car
        JButton removeCar = new JButton("Remove");
        removeCar.setPreferredSize(new Dimension(ProgramScreenSize.BUTTON_WIDTH.getSize(), ProgramScreenSize.BUTTON_HEIGHT.getSize()));
        removeCar.setBackground(ProgramColors.BUTTONS.getColor());
        buttonPanel.add(removeCar);
        //Кнопка sort
        JButton sort = new JButton("Sort");
        sort.setPreferredSize(new Dimension(ProgramScreenSize.BUTTON_WIDTH.getSize(), ProgramScreenSize.BUTTON_HEIGHT.getSize()));
        sort.setBackground(ProgramColors.BUTTONS.getColor());
        buttonPanel.add(sort);
        //Кнопка search
        JButton search = new JButton("Search");
        search.setPreferredSize(new Dimension(ProgramScreenSize.BUTTON_WIDTH.getSize(), ProgramScreenSize.BUTTON_HEIGHT.getSize()));
        search.setBackground(ProgramColors.BUTTONS.getColor());
        buttonPanel.add(search);
        //Кнопка now
        JButton now = new JButton("Now");
        now.setPreferredSize(new Dimension(ProgramScreenSize.BUTTON_WIDTH.getSize(), ProgramScreenSize.BUTTON_HEIGHT.getSize()));
        now.setBackground(ProgramColors.BUTTONS.getColor());
        buttonPanel.add(now);
        //Кнопка report
        JButton report = new JButton("Report");
        report.setPreferredSize(new Dimension(ProgramScreenSize.BUTTON_WIDTH.getSize(), ProgramScreenSize.BUTTON_HEIGHT.getSize()));
        report.setBackground(ProgramColors.BUTTONS.getColor());
        buttonPanel.add(report);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        //INFO PANEL
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        //Заголовок до таблиці
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        String header = "Total quantity of places: " + dbController.getVolume() +
                ". Price for one place per hour: " + dbController.getPrice() + "$";

        JLabel label = new JLabel(header);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(label);
        infoPanel.add(titlePanel, BorderLayout.NORTH);

        //Таблиця
        JPanel tablePanel = new JPanel();
        parkingTableModel = new ParkingTableModel(dbController);//структура даних таблиці
        JTable table = new JTable(parkingTableModel);//таблиця
        JScrollPane tableScrollPane = new JScrollPane(table);//контейнер, в якому таблицю можна буде прокрутити
        tableScrollPane.setPreferredSize(new Dimension(ProgramScreenSize.WIDTH.getSize() - 100, ProgramScreenSize.HEIGHT.getSize() - 200));//розміри видимої частини таблиці
        tablePanel.add(tableScrollPane);
        infoPanel.add(tablePanel, BorderLayout.SOUTH);

        //СТИЛІЗАЦІЯ ТАБЛИЦІ
        //Стилі заголовку таблиці
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(ProgramColors.TABLE_HEADER.getColor());
        tableHeader.setFont(ProgramFonts.TABLE_HEADER.getFont());

        //Стилі комірок
        table.setFont(ProgramFonts.TABLE_CELL.getFont());
        table.setRowHeight(28);
        table.setCellSelectionEnabled(false);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(String.class);
        renderer.setHorizontalAlignment(JLabel.CENTER);//Вирівнювання в комірках по горизонталі

        //CONTROL PANEL
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        //Кнопка reset
        JButton reset = new JButton("Reset");
        controlPanel.add(reset);
        //Кнопка return
        returnButton = new JButton("Return");
        controlPanel.add(returnButton);

        //Оформлення
        //Колір фону
        headerText.setBackground(ProgramColors.BACKGROUND.getColor());
        buttonPanel.setBackground(ProgramColors.BACKGROUND.getColor());
        titlePanel.setBackground(ProgramColors.BACKGROUND.getColor());
        tablePanel.setBackground(ProgramColors.BACKGROUND.getColor());
        infoPanel.setBackground(ProgramColors.BACKGROUND.getColor());
        controlPanel.setBackground(ProgramColors.BACKGROUND.getColor());
        table.setBackground(ProgramColors.BACKGROUND.getColor());

        //Шрифти
        headerLabel.setFont(ProgramFonts.HEADERS.getFont());
        headerLabel.setForeground(ProgramColors.HEADERS.getColor());

        label.setFont(ProgramFonts.LABELS.getFont());

        addCar.setFont(ProgramFonts.BUTTONS.getFont());
        removeCar.setFont(ProgramFonts.BUTTONS.getFont());
        sort.setFont(ProgramFonts.BUTTONS.getFont());
        report.setFont(ProgramFonts.BUTTONS.getFont());
        now.setFont(ProgramFonts.BUTTONS.getFont());
        search.setFont(ProgramFonts.BUTTONS.getFont());

        reset.setFont(ProgramFonts.BUTTONS.getFont());
        reset.setBackground(ProgramColors.BUTTONS.getColor());

        returnButton.setFont(ProgramFonts.BUTTONS.getFont());
        returnButton.setBackground(ProgramColors.BUTTONS.getColor());

        //ДОДАВАННЯ ВСІХ ЕЛЕМЕНТІВ НА ФОРМУ
        container.add(topPanel, BorderLayout.NORTH);
        container.add(infoPanel, BorderLayout.CENTER);
        container.add(controlPanel, BorderLayout.SOUTH);

        //MENU
        addCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Form for parking
                AddCar addCar = new AddCar();

                //Panel
                JPanel addPanel = addCar.getMainPanelAdd();

                //Text fields
                JTextField licencePlateField = addCar.getLicencePlateField();
                JTextField ownerField = addCar.getOwnerField();
                JTextField dateField = addCar.getDateField();
                JTextField timeField = addCar.getTimeField();
                JTextField placeForParkingField = addCar.getPlaceForParkingField();

                //Buttons
                JButton returnButton = addCar.getReturnButton();
                JButton readyButton = addCar.getReadyButton();

                //Settings for displaying
                showPanel(addPanel);

                //Event listeners
                returnButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        disposePanel(addPanel);
                    }
                });

                readyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(addCar.isValid()){
                            //Update data
                            Car car = new Car(licencePlateField.getText(), ownerField.getText());
                            String date = dateField.getText().trim();
                            String time = timeField.getText().trim();
                            int placeForParking = Integer.parseInt(placeForParkingField.getText());
                            parkingTableModel.addData(car, placeForParking, date, time);

                            //Update view
                            disposePanel(addPanel);
                        }else{
                            JOptionPane.showMessageDialog(null, "Invalid value of field!");
                            addCar.clearForm();
                        }
                    }
                });
            }
        });
        removeCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Form for driving
                RemoveCar removeCar = new RemoveCar(parkingTableModel);

                //Panel
                JPanel removePanel = removeCar.getMainPanelRemove();

                //Text fields
                JTextField dateField = removeCar.getDateField();
                JTextField timeField = removeCar.getTimeField();
                JTextField placeForParkingField = removeCar.getPlaceForParkingField();

                //Buttons
                JButton readyButton = removeCar.getReadyButton();
                JButton returnButton = removeCar.getReturnButton();

                //SettingsForDisplaying
                showPanel(removePanel);

                returnButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       disposePanel(removePanel);
                    }
                });
                readyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(removeCar.isValid()){
                            //Update data
                            String date = dateField.getText().trim();
                            String time = timeField.getText().trim();
                            int placeForParking = Integer.parseInt(placeForParkingField.getText());
                            parkingTableModel.removeData(placeForParking, date, time);

                            //Update view
                            disposePanel(removePanel);
                        }else{
                            JOptionPane.showMessageDialog(null, "Invalid value of field!");
                            removeCar.clearForm();
                        }
                    }
                });
            }
        });
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Form for sorting
                SortCars sortCars = new SortCars();

                //Panel
                JPanel sortPanel = sortCars.getMainPanelSort();

                //Radio buttons
                JRadioButton byOwner = sortCars.getByOwner();
                JRadioButton byCar = sortCars.getByCar();

                //Buttons
                JButton returnButton = sortCars.getReturnButton();
                JButton readyButton = sortCars.getReadyButton();

                //SettingsForDisplaying
                showPanel(sortPanel);

                //Event listeners
                returnButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        disposePanel(sortPanel);
                    }
                });
                readyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Update data
                        if(byOwner.isSelected()) parkingTableModel.sortByOwner();
                        if(byCar.isSelected()) parkingTableModel.sortByCar();

                        //Update view
                        disposePanel(sortPanel);
                    }
                });
            }
        });
        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Form for creation order
                CreateOrder createOrder = new CreateOrder();

                //Panel
                JPanel creationPanel = createOrder.getMainPanelReport();

                //Text fields
                JTextField ownerField = createOrder.getOwnerField();
                JTextField startField = createOrder.getStartField();
                JTextField endField = createOrder.getEndField();

                //Buttons
                JButton returnButton = createOrder.getReturnButton();
                JButton readyButton = createOrder.getReadyButton();

                //SettingsForDisplaying
                showPanel(creationPanel);

                returnButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        disposePanel(creationPanel);
                    }
                });
                readyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(createOrder.isValid()){
                            //Update data
                            String owner = ownerField.getText();
                            String startDate = startField.getText();
                            String endDate = endField.getText();

                            parkingTableModel.createOrder(owner, startDate, endDate);

                            //Update view
                            disposePanel(creationPanel);
                        }else{
                            JOptionPane.showMessageDialog(null, "Invalid value of field!");
                            createOrder.clearForm();
                        }
                    }
                });
            }
        });
        now.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(optionalPanel != null) disposePanel(optionalPanel);
                parkingTableModel.nowOnParking();
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Form for searching
                SearchCar searchCar = new SearchCar();

                //Panel
                JPanel searchPanel = searchCar.getMainPanelSearch();

                //Text fields
                JTextField ownerName = searchCar.getOwnerName();
                JTextField licencePlate = searchCar.getLicencePlate();
                JTextField minTime = searchCar.getMinTime();
                JTextField maxTime = searchCar.getMaxTime();

                //Radio buttons
                JCheckBox byOwner = searchCar.getByOwner();
                JCheckBox byLicencePlate = searchCar.getByLicencePlate();
                JCheckBox byTime = searchCar.getByTime();

                //Buttons
                JButton readyButton = searchCar.getReadyButton();
                JButton returnButton = searchCar.getReturnButton();

                //SettingsForDisplaying
                showPanel(searchPanel);

                returnButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        disposePanel(searchPanel);
                    }
                });
                readyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Дані, за якими буде здійснено пошукі
                        ArrayList<String> dataForSearch = new ArrayList<String>();

                        //Owner name
                        if(byOwner.isSelected()){
                            dataForSearch.add(ownerName.getText().trim());
                        }else{
                            dataForSearch.add(null);
                        }

                        //Licence plate
                        if(byLicencePlate.isSelected()){
                            dataForSearch.add(licencePlate.getText().trim());
                        }else{
                            dataForSearch.add(null);
                        }

                        //Time
                        if(byTime.isSelected()){
                            dataForSearch.add(minTime.getText().trim());
                            dataForSearch.add(maxTime.getText().trim());
                        }else{
                            dataForSearch.add(null);
                            dataForSearch.add(null);
                        }

                        //Пошук
                        parkingTableModel.search(dataForSearch);
                        disposePanel(searchPanel);
                    }
                });
            }
        });

        //CONTROLS
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parkingTableModel.reset();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outside.remove(container);
                outside.repaint();
                outside.add(panel);
                outside.revalidate();
            }
        });
    }

    public Container getContainer() {
        return container;
    }

    //Службові методи
    private  void showPanel(JPanel panel){
        if(optionalPanel != null) disposePanel(optionalPanel);//close all panels from menu

        container.remove(infoPanel);
        container.remove(controlPanel);
        container.repaint();

        container.add(panel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();

        optionalPanel = panel;
    }
    private void disposePanel(JPanel panel){
        container.remove(panel);
        container.repaint();

        container.add(infoPanel, BorderLayout.CENTER);
        container.add(controlPanel, BorderLayout.SOUTH);
        container.revalidate();
    }
}
