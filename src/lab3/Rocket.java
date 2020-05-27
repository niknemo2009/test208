package lab3;

public class Rocket {
    //Мощность ракеты
    private int power;
    //Масса коробля
    private int massTon;
    //Модель ракеты
    private String model;

    public Rocket(int power, String model, int massTon){
        this.power=power;
        this.model=model;
        this.massTon=massTon;
    }

    public int getMass() { return massTon; }

    public void setMass(int massTon) { this.massTon = massTon; }

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


}
