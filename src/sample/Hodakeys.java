package sample;

public class Hodakeys {
    //Ходакейци управляют конкретной ракетой
    private Rocket rocket;

    public Hodakeys() {
    }

    public Hodakeys(Rocket rocket) {
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
}
