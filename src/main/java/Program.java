/*Завдання №45:	Створіть слідуючу модель - є приватна автостоянка.
Кожного місяця, водіям , які паркували авто, видається ракунок за використання послугами стоянки.
Звіт можливо сортувати або по авто, або по власникам.
Можлива ситуація коли 1 власник має декілько авто.
Повинна бути можливість отримати список усіх авто які є на стоянці зараз, та журнал обліку виїздів-заїздів за період.
Потрібна можливість формування звіту по окремому авто або власнику.
Використовуйте колекції, графічний інтерфейс не потрібен.
*/

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;

class PrivateParking {
    private int volume;//кількість місць на парковці
    private float priceOfPlace;//ціна одного місця на парковці
    private ArrayList<ParkingPlace> places;//колекція, що містить всі місця для паркування
    private Journal journal;//журнал записів про всі машини, що паркувалися або виїхали з парковки
    private User keeper;//власник парковки

    public PrivateParking(User keeper, int volume, float priceOfPlace) {
        this.keeper = keeper;
        this.volume = volume;
        this.places = new ArrayList<ParkingPlace>();
        this.priceOfPlace = priceOfPlace;
        this.journal = new Journal();
        //Композиція
        for (int i = 0; i < volume; i++) {
            this.places.add(new ParkingPlace(i + 1));
        }
    }

    public float getPrice() { return  priceOfPlace; }

    //Методи, що дозволяють змінювати кількість машин на парковці та отримувати дані про ці машини
    public void ParkCar(Car car, int placeForParking, LocalDate date, LocalTime time){
        if(placeForParking > volume) {
            System.out.println("Даного місця на парковці не існує!");
        }else{
            if(!places.get(placeForParking - 1).isVoid()) {
                System.out.println("Дане місце зайняте іншим автомобілем!");
            }
            if(places.get(placeForParking - 1).isVoid()){//placeForParking - 1, бо індексація йде с нуля,  нумерація - з 1
                places.get(placeForParking - 1).AddCar(car);
                //Робимо запис про паркування автомобіля в журналі
                this.journal.AddRecord(placeForParking, car.getLicencePlate(), car.getOwner(), Action.PARKING, date, time);
            }
        }
    }
    public void LeaveParking(int placeForParking, LocalDate date, LocalTime time){
        if(placeForParking > volume) {
            System.out.println("Даного місця на парковці не існує!");
        }else{
            if(places.get(placeForParking - 1).isVoid()) {
                System.out.println("Дане місце вже вільне!");
            }
            if(!places.get(placeForParking - 1).isVoid()) {
                Car currentCar = places.get(placeForParking - 1).getCar();//отримати дані про машину, доки її не видалено
                places.get(placeForParking - 1).RemoveCar();
                //Робимо запис про те, що автомобіль залишив парковку
                this.journal.AddRecord(placeForParking, currentCar.getLicencePlate(), currentCar.getOwner(), Action.MOVING, date, time);
            }
        }
    }
    //Всі необхідні методи пошуку, сортування та формування звітів знаходяться у журналі
    public Journal getJournal() {
        return journal;
    }
}

class User {
    private String login;
    private String password;

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public boolean LogIn(String login, String password){
        if(this.login.equals(login) && this.password.equals(password)){
            return true;
        }else{
            return false;
        }
    }
}

class ParkingPlace{
    private int number;
    private boolean isVoid = true;
    private Car car;

    public ParkingPlace(int number){
        this.number = number;
    }
    public void AddCar(Car car) {
        this.car = car;
        isVoid = false;
    }
    public void RemoveCar(){
        this.car = null;
        isVoid = true;
    }
    public boolean isVoid() {
        return isVoid;
    }
    public Car getCar() {
        return car;
    }
    public int getNumber() {
        return number;
    }
}

class Car{
    private String licencePlate;
    private String owner;

    public Car(String licencePlate, String owner){
        this.licencePlate = licencePlate;
        this.owner = owner;
    }
    public String getLicencePlate() {
        return licencePlate;
    }
    public String getOwner() {
        return owner;
    }
}

/*Для зберігання даних про паркування на парковці викор. об'єкти класів Car, ParkingPlace, PrivateParking, які містять дані про поточний
стан парковки, але не містять ІСТОРІЮ паркувань. Журнал містить дані про історію паркувань.
* */
class Journal{
    private ArrayList<Record> records;//масив записів про всі автомобілі, що паркувалися та залишали парковку

    public Journal(){
        this.records = new ArrayList<>();
    }
    public void AddRecord(int placeNumber, String carLicencePlate, String ownerName, Action action, LocalDate date, LocalTime time){
        records.add(new Record(placeNumber, carLicencePlate, ownerName, action, date, time));
    }

    //ВСІ ЗАПИСИ, що зареєстровані в журналі
    public ArrayList<Record> AllRecords(){
        return records;
    }
    public void setRecords(ArrayList<Record> newRecords){
        this.records = newRecords;
    }

    //СОРТУВАННЯ
    public void sortByOwner(){//за власником
        Collections.sort(records);
    }
    public void sortByCar(){//за авто
        records.sort(new Comparator<Record>() {//для порівняння за автомобілем
            @Override
            public int compare(Record o1, Record o2) {
                return o1.getCarLicencePlate().compareTo(o2.getCarLicencePlate());
            }
        });
    }

    //ПОШУК
    public ArrayList<Record> SearchByOwner(String owner){//за власником
        ArrayList<Record> validRecords = new ArrayList<Record>();//записи, які підходять
        for(Record record : records){
            if (isEqualTo(record.getOwnerName(), owner)){
                validRecords.add(record);
            }
        }
        return validRecords;
    }
    public ArrayList<Record> SearchByLicencePlate(String licencePlate){//за авто
        ArrayList<Record> validRecords = new ArrayList<Record>();//записи, які підходять
        for(Record record : records){
            if(isEqualTo(record.getCarLicencePlate(), licencePlate)){
                validRecords.add(record);
            }
        }
        return validRecords;
    }
    public ArrayList<Record> RecordsInRange(LocalDate min, LocalDate max){//за періодом часу
        ArrayList<Record> validRecords = new ArrayList<Record>();//записи, які підходять
        for(Record record : records){
            if((record.getDate().compareTo(min) > 0) && (record.getDate().compareTo(max) < 0)) {
                validRecords.add(record);
            }
        }
        return validRecords;
    }

    //ПАРКОВКА НА ДАНИЙ МОМЕНТ
    public ArrayList<Record> NowOnParking(){
        ArrayList<Record> validRecords = new ArrayList<Record>();//записи, які підходять

        String car;//перевіряємо паркування саме за автомобілем, бо у однієї людини може бути декілька машин
        int timesOfParking = 0;//скільки разів приїхав на парковку
        int timesOfMoving = 0;//скільки разів поїхав з парковки

        for(int i = 0; i < records.size(); i++){
            //Для якого автомобіля перевіряємо
            car = records.get(i).getCarLicencePlate();
            for (Record record : records) {
                if (record.getCarLicencePlate().equals(car) && record.getAction().equals(Action.PARKING)) {
                    timesOfParking++;
                }
                if (record.getCarLicencePlate().equals(car) && record.getAction().equals(Action.MOVING)) {
                    timesOfMoving++;
                }
            }
            //Якщо машина зараз на парковці, то вона більше разів паркувалася, ніж їхала з парковки
            if(timesOfParking > timesOfMoving){
                validRecords.add(records.get(i));
            }
            timesOfMoving = 0;
            timesOfParking = 0;
        }

        return validRecords;
    }

    //ФОРМУВАННЯ ЗВІТУ
    public void CreateOrder(String owner, float price, LocalDate start, LocalDate end){
        LocalTime parkingTime = null;//час, коли запаркувався
        LocalDate parkingDate = null;//день, коли запаркувався

        LocalTime drivingTime = null;//час, коли поїхав
        LocalDate drivingDate = null;//день, коли поїхав

        double totalTimeOnParking = 0;
        double totalSum = 0;
        String message = "";

        boolean isInRange = false;
        boolean isFound = false;

        for(Record record : records){
            //Час, коли запаркувався
            if(record.getOwnerName().equals(owner) && record.getAction().equals(Action.PARKING)){
                parkingTime = record.getTime();
                parkingDate = record.getDate();
            }

            //Час, коли поїхав с парковки
            if(record.getOwnerName().equals(owner) && record.getAction().equals(Action.MOVING)){
                drivingTime = record.getTime();
                drivingDate = record.getDate();
            }


            //Чи знайдено записи про від'їзд та паркування
            isFound = parkingTime != null && drivingTime != null;

            if(isFound){
                //Чи знаходяться ці записи в заданому діапазоні
                isInRange = (parkingDate.isAfter(start) || parkingDate.equals(start)) && (drivingDate.isBefore(end) || drivingDate.equals(end));

                //Записи в заданому діпазоні і машина виїхала та повернулася в один день
                if(isInRange && parkingDate.equals(drivingDate)){//
                    double quantityOfHours = drivingTime.getHour() - parkingTime.getHour();
                    double quantityOfMinutes = drivingTime.getMinute() / 60.00 - parkingTime.getMinute() / 60.00;

                    totalTimeOnParking +=  quantityOfHours + quantityOfMinutes;
                    totalSum += totalTimeOnParking * price;

                    String formattedTotalTimeOnParking = String.format("%.2f", totalTimeOnParking);
                    String formattedTotalSum = String.format("%.2f", totalSum);
                    message = owner + "`s car was being on parking for " + formattedTotalTimeOnParking + " hours. Total sum: " + formattedTotalSum + "$";

                    System.out.println(message);

                    //Повернення до початкових налаштувань
                    parkingTime = null;
                    parkingDate = null;

                    drivingTime = null;
                    drivingDate = null;
                }

                //Записи в заданому діпазоні і машина була на парковці кілька днів
                if(drivingDate != null){
                    if(isInRange && !parkingDate.equals(drivingDate)){
                        Period period = Period.between(parkingDate, drivingDate);
                        int quantityOfDays = period.getDays();
                        double quantityOfHours = (24 - parkingTime.getHour()) + drivingTime.getHour();
                        double quantityOfMinutes = drivingTime.getMinute() / 60.00 - parkingTime.getMinute() / 60.00;

                        totalTimeOnParking +=  quantityOfDays * 24 + quantityOfHours + quantityOfMinutes;
                        totalSum += totalTimeOnParking * price;

                        String formattedTotalTimeOnParking = String.format("%.2f", totalTimeOnParking);
                        String formattedTotalSum = String.format("%.2f", totalSum);
                        message = owner + "`s car was being on parking for " + formattedTotalTimeOnParking + " hours. Total sum: " + formattedTotalSum + "$";

                        System.out.println(message);

                        //Повернення до початкових налаштувань
                        parkingTime = null;
                        parkingDate = null;

                        drivingTime = null;
                        drivingDate = null;
                    }
                }
            }
        }
    }
    //ДОПОМІЖНІ МЕТОДИ
    private boolean isEqualTo(String str1, String str2){//пошук, який не залежить від регістра
        return str1.toLowerCase().equals(str2.toLowerCase());
    }
}

class Record implements Comparable<Record>{
    private int placeNumber;
    private String carLicencePlate;
    private String ownerName;
    private Action action;//поставить enum
    //private Calendar date;//заменить LocalDate
    private LocalDate date;
    private LocalTime time;

    public Record(int placeNumber, String carLicencePlate, String ownerName, Action action, LocalDate date, LocalTime time){
        this.placeNumber = placeNumber;
        this.carLicencePlate = carLicencePlate;
        this.ownerName = ownerName;
        this.action = action;
        this.date = date;
        this.time = time;
    }
    public Action getAction() {
        return action;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getCarLicencePlate() {
        return carLicencePlate;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() { return time; }

    @Override
    public String toString() {
        return Integer.toString(placeNumber) + ' ' + carLicencePlate + ' ' + ownerName + ' ' + ' ' + action + ' ' + time;
    }
    @Override
    public int compareTo(Record o) {//для порівняння за власником
        return this.ownerName.compareTo(o.ownerName);
    }
}

//Дія, яку здійснює автомобіль на парковці
enum Action{
    MOVING,//поїхав з парковки
    PARKING//приїхав на парковку
}

public class Program {
    public static void main(String[] args) {
        User user = new User("test", "test");
        PrivateParking parking = new PrivateParking(user,3, 5.00f);//Приватна парковка

        //Автомобілі
        Car car1 = new Car("CA1111BB", "Zinger");
        Car car2 = new Car("BB2222BB", "Aaron");
        Car car3 = new Car("AA3333BB", "Smith");

        //LocalDate
        LocalDate date1 = LocalDate.of(2020, 2, 12);
        LocalDate date2 = LocalDate.of(2020, 2, 11);
        LocalDate date3 = LocalDate.of(2020, 1,14);
        //LocalTime
        LocalTime time1 = LocalTime.of(10, 30, 15);
        LocalTime time2 = LocalTime.of(12,15,25);
        LocalTime time3 = LocalTime.of(14,30,0);

        //Зміна кількості автомобілів на парковці
        parking.ParkCar(car1, 1, date3, time3);
        parking.ParkCar(car2, 2, date1, time1);
        parking.ParkCar(car3, 3, date2, time1);
        parking.LeaveParking(2, date1, time2);

        //Тестування всіх методів у journal
        System.out.println("All records:");
        System.out.println(parking.getJournal().AllRecords());
        System.out.println("Now on parking:");
        System.out.println(parking.getJournal().NowOnParking());
        System.out.println("Sort by owner:");
        parking.getJournal().sortByOwner();
        System.out.println(parking.getJournal().AllRecords());
        System.out.println("Sort by car:");
        parking.getJournal().sortByCar();
        System.out.println(parking.getJournal().AllRecords());
        System.out.println("Search by owner:");
        System.out.println(parking.getJournal().SearchByOwner("Smith"));
        System.out.println("Search by car:");
        System.out.println(parking.getJournal().SearchByLicencePlate("CA1111BB"));
        System.out.println("Records in range:");
        System.out.println(parking.getJournal().RecordsInRange(date3, date1));
        System.out.println("Create order:");
        parking.getJournal().CreateOrder("Aaron", parking.getPrice(), date3, date1);
    }
}
