package sample;

public class Turians {
    //Турианци управляют конкретной ракетой
    private Rocket rocket;

    public Turians() {
    }

    public Turians(Rocket rocket) {
        this.rocket = rocket;
    }

    //Можно узнать какой ракетой он управляет
    public Rocket getRocket() {
        return rocket;
    }

    //Можно поручить управлять ракетой
    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public String toString() {
        return "Turians{" +
                "rocket=" + rocket +
                '}';
    }
}

