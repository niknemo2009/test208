package lab4;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller{

    //Счёт ракет которые заходят на станцию
    static int amount = 0;
    //Счёт массы кораблей которые заходят на станцию
    static int currentWeight = 0;

    //Максимальный вес всех ракет которые находяться на станции
    private final int TOTAL_WEIGHT = 10000000;
    //Максимальное количество ракет на станции
    private final int AMOUNT_LIMIT = 11;

    //Рассы
    private Turians turians = new Turians();
    private Hodakeys hodakeys = new Hodakeys();
    private Humans humans = new Humans();

    //Коллекции
    private List<Humans> humansList;
    private List<Hodakeys> hodakeysList;
    private List<Turians> turiansList;

    @FXML
    private Button addRocket;

    @FXML
    private TextField model;

    @FXML
    private TextField massTon;

    @FXML
    private TextField power;

    @FXML
    private TextField rocketModelToDelete;

    @FXML
    private Button deleteRocket;

    @FXML
    private ListView<Turians> turiansListView;

    @FXML
    private ListView<Humans> humanListView;

    @FXML
    private ListView<Hodakeys> hodakeysListView;

    public Controller() {
        humansList = new ArrayList<>();
        hodakeysList = new ArrayList<>();
        turiansList = new ArrayList<>();
    }

    public static int getAmount() {
        return amount;
    }

    public static int getCurrentWeight() {
        return currentWeight;
    }

    public int getTotalWeight() {
        return TOTAL_WEIGHT;
    }

    public int getAmountLimit() {
        return AMOUNT_LIMIT;
    }





    @FXML
    void initialize() {
        addRocket.setOnAction(actionEvent ->  {
            createRocket();
        });

        deleteRocket.setOnAction(actionEvent -> {
            removeRocket();
        });

    }

    public void removeRocket() {
        String rocketModeltoDelete = model.getText();

        if(rocketModeltoDelete.contains("Turians") || rocketModeltoDelete.contains("turians")){
            turiansListView.getItems().remove(turians);
            leaveTheStaton(turians);
        }
        else if (rocketModeltoDelete.contains("Humans") || rocketModeltoDelete.contains("humans")){
            humanListView.getItems().remove(humans);
            leaveTheStaton(humans);
        }
        else if (rocketModeltoDelete.contains("Hodakeys") || rocketModeltoDelete.contains("hodakeys")){
            hodakeysListView.getItems().remove(hodakeys);
            leaveTheStaton(hodakeys);
        }
        else {
            System.out.println("There no model like that" + model.getText());
        }
    }
    public void createRocket() {
        String rocketModel = model.getText();
        int rocketPower = Integer.parseInt(String.valueOf(power.getText()));
        int rocketMass = Integer.parseInt(String.valueOf(massTon.getText()));
        if (rocketMass > TOTAL_WEIGHT) {
            System.out.println("ur rocket is too heavy");
        } else if (TOTAL_WEIGHT < currentWeight + rocketMass) {
            System.out.println("your rcoket is too heavy for us v2");
        } //else if (getAmount() > AMOUNT_LIMIT){
//            System.out.println("no more space for you bae");
//        }
        else {
            if (rocketModel.contains("Turians") || rocketModel.contains("turians")) {
                if (humanListView.getItems().size() > 0){
                    System.out.println("turians hate humans");
                }
                else {
                    Rocket turinasRocket = new Rocket(rocketPower, rocketModel, rocketMass);
                    turians = new Turians(turinasRocket);
                    arrive(turians);
                    turiansListView.getItems().add(turians);
                }
            } else if (rocketModel.contains("Humans") || rocketModel.contains("humans")) {
                if (hodakeysListView.getItems().size() > 0){
                    System.out.println("Humans hate hodakeys");
                }
                else {
                    Rocket humansRocket = new Rocket(rocketPower, rocketModel, rocketMass);
                    humans = new Humans(humansRocket);
                    arrive(humans);
                    humanListView.getItems().add(humans);
                }
            } else if (rocketModel.contains("Hodakeys") || rocketModel.contains("hodakeys")) {
                Rocket hodakeysRocket = new Rocket(rocketPower, rocketModel, rocketMass);
                hodakeys = new Hodakeys(hodakeysRocket);
                arrive(hodakeys);
                hodakeysListView.getItems().add(hodakeys);
            } else {
                System.out.println("wrong race");
            }
        }
        if (getAmount() >= AMOUNT_LIMIT) {
            if (rocketModel.contains("Turians") || rocketModel.contains("turians")){
                turiansListView.getItems().remove(turians);
                amount--;
            }
            else if (rocketModel.contains("Humans") || rocketModel.contains("humans")){
                humanListView.getItems().remove(humans);
                amount--;
            }
            else if (rocketModel.contains("Hodakeys") || rocketModel.contains("hodakeys")){
                hodakeysListView.getItems().remove(hodakeys);
                amount--;
            }
        } else {
            System.out.println(getAmount());
        }
        System.out.println("ROCKET is created for " + model.getText());
    }

    public void arrive(Object object){
        if (object.getClass() == hodakeys.getClass()) {
            //Если у корабля нет названия, или он неможет передвигаться, то есть у него нет мощности тогда их не пускает на станцию
            if (((Hodakeys) object).getRocket().getModel() == null || ((Hodakeys) object).getRocket().getPower() == 0) {
                System.out.println("Model : " + ((Hodakeys) object).getRocket().getModel() + " Power : " + ((Hodakeys) object).getRocket().getPower());
            }
            //Есил масса корабля больше чем вся доступная масса станции тогда их не поскает на станцию
            else if (((Hodakeys) object).getRocket().getMass() > TOTAL_WEIGHT) {
                System.out.println("Your Rocket is too heavy to our station");
            }
            //Если общая масса меньше чем масса всех кораблей + масса нового коробля, тогда их не пускает
            else if (TOTAL_WEIGHT < currentWeight + ((Hodakeys) object).getRocket().getMass()) {
                System.out.println("Your rocket is too heavy, your rocket mass is " + ((Hodakeys) object).getRocket().getMass() + " Station total mass is: " + getTotalWeight() + " Our current mass is : " + getCurrentWeight());
            }
            //Если текущая масса меньше чем общая масса тогда их запускают на станцию
            else {
                if (currentWeight < TOTAL_WEIGHT) {
                    //Добавление массы ракеты в текущую массу
                    currentWeight = ((Hodakeys) object).getRocket().getMass() + currentWeight;
                    System.out.println("Hodakeys comeIn");
                    hodakeysList.add((Hodakeys) object);
                    amount++;
                } else {
                    System.out.println(getCurrentWeight() + " Current weight; " + getTotalWeight() + " Total weight");
                }
            }
        }
        else if (object.getClass() == humans.getClass()){
            //Если у корабля нет названия, или он неможет передвигаться, то есть у него нет мощности тогда их не пускает на станцию
            if (((Humans) object).getRocket().getModel() == null || ((Humans) object).getRocket().getPower() == 0) {
                System.out.println("Model : " + ((Humans) object).getRocket().getModel() + " Power : " + ((Humans) object).getRocket().getPower());
            }
            //Если на станции есть ходакейци, тогда земляни не захотят останавливоться на этой станции
            else if (hodakeysList.size() > 0) {
                System.out.println("humans do not love hodakeys, ok?");
            }
            //Есил масса корабля больше чем вся доступная масса станции тогда их не поскает на станцию
            else if (((Humans) object).getRocket().getMass() > TOTAL_WEIGHT) {
                System.out.println("Your Rocket is too heavy to our station");
            }
            //Если общая масса меньше чем масса всех кораблей + масса нового коробля, тогда их не пускает
            else if (TOTAL_WEIGHT < currentWeight + ((Humans) object).getRocket().getMass()) {
                System.out.println("Your rocket is too heavy, your rocket mass is " + ((Humans) object).getRocket().getMass() + " Station total mass is: " + getTotalWeight() + " Our current mass is : " + getCurrentWeight());
            }
            //Если текущая масса меньше чем общая масса тогда их запускают на станцию
            else {
                if (currentWeight < TOTAL_WEIGHT) {
                    //Добавление массы ракеты в текущую массу
                    currentWeight = ((Humans) object).getRocket().getMass() + currentWeight;
                    System.out.println("Humans comeIn");
                    humansList.add((Humans) object);
                    amount++;
                } else {
                    System.out.println(getCurrentWeight() + " Current weight; " + getTotalWeight() + " Total weight");
                }
            }
        }
        else if (object.getClass() == turians.getClass()){
            //Если у корабля нет названия, или он неможет передвигаться, то есть у него нет мощности тогда их не пускает на станцию
            if (((Turians) object).getRocket().getModel() == null || ((Turians) object).getRocket().getPower() == 0) {
                System.out.println("Model : " + ((Turians) object).getRocket().getModel() + " Power : " + ((Turians) object).getRocket().getPower());
            }
            //Если на станции есть земляни, тогда турианци не захотят останавливоться на этой станции
            else if (humansList.size() > 0) {
                System.out.println("Turians do not love humans, ok?");
            }
            //Если общая масса меньше чем масса всех кораблей + масса нового коробля, тогда их не пускает
            else if (TOTAL_WEIGHT < currentWeight + ((Turians) object).getRocket().getMass()) {
                System.out.println("Your rocket is too heavy, your rocket mass is " + ((Turians) object).getRocket().getMass() + " Station total mass is: " + getTotalWeight() + " Our current mass is : " + getCurrentWeight());
            }
            //Если текущая масса меньше чем общая масса тогда их запускают на станцию
            else {
                if (currentWeight < TOTAL_WEIGHT) {
                    //Добавление массы ракеты в текущую массу
                    currentWeight = ((Turians) object).getRocket().getMass() + currentWeight;
                    System.out.println("Turians comeIn");
                    turiansList.add((Turians) object);
                    amount++;
                } else {
                    System.out.println(getCurrentWeight() + " Current weight; " + getTotalWeight() + " Total weight");
                }
            }
        }
        //Максимум на станции может находиться 10 кораблей
        if (getAmount() > AMOUNT_LIMIT) {
            System.out.println("No more space for rockets sorry, we`ve got limit, limit is : " + getAmountLimit());
            //Уменьшение счётчика
            amount--;
        } else {
            System.out.println(getAmount());
        }
    }

    //Покидают станцию
    public void leaveTheStaton(Object object) {
        if (object.getClass() == turians.getClass()) {
            //Удаление элемента из листа
            turiansList.remove(object);
            //Уменьшение счётчика
            amount--;
            System.out.println("Bye tourians");
        } else if (object.getClass() == hodakeys.getClass()) {
            hodakeysList.remove(object);
            amount--;
            System.out.println("bye hodakeys");
        } else if (object.getClass() == humans.getClass()) {
            humansList.remove(object);
            amount--;
            System.out.println("byt humans");
        }
    }

}
