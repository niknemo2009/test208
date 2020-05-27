package lab3;

public class Humans {
    //Земляни управляют конкретной ракетой
    private Rocket rocket;

    public Humans(){}
    public Humans(Rocket rocket){
        this.rocket=rocket;
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
