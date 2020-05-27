package lab4;

public class Rocket {
    //Мощность ракеты
    private int power = 1000;
    //Масса коробля
    private int massTon = 1000;
    //Модель ракеты
    private String model;


    public Rocket(int power, String model, int massTon) {
        this.power = power;
        this.model = model;
        this.massTon = massTon;
    }


    public int getMass() {
        return massTon;
    }

    public void setMass(int massTon) {
        this.massTon = massTon;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "power=" + power +
                ", massTon=" + massTon +
                ", model='" + model + '\'' +
                '}';
    }
}
