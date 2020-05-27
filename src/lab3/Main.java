package lab3;

public class Main {

    public static void main(String[] args) {
        //Создание станции
        Station station = new Station();

        //Создание ракет, Агрегация
        Rocket humanRocket = new Rocket(1600, "humanRocket", 1000000);
        Rocket hodakeysRocket = new Rocket(2000, "hodakeysRocket", 1000000);
        Rocket turiansRocket = new Rocket(1200, "turiansRocket", 1);

        //Создание экипажа для ракет
        Humans humans = new Humans(humanRocket);
        Hodakeys hodakeys = new Hodakeys(hodakeysRocket);
        Turians turians = new Turians(turiansRocket);

        station.arrive(hodakeys);
        station.arrive(humans);
        station.arrive(turians);
        station.arrive(turians);
        station.arrive(turians);
        station.arrive(turians);
        station.arrive(turians);
        station.arrive(turians);
        station.arrive(turians);
        station.arrive(turians);
        station.arrive(turians);

        station.arrive(turians);

        station.leaveTheStaton(turians);
    }
}
