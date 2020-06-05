package com.company;

public class Avto {
    private String model_avto;
    public int col_viezd;

    public String getModel_avto() {
        return model_avto;
    }

    public void setModel_avto(String model_avto) {
        this.model_avto = model_avto;
    }

    public int getCol_viezd() {
        return col_viezd;
    }

    public void setCol_viezd(int col_viezd) {
        this.col_viezd = col_viezd;
    }

    public String toString() {
        return   "Автомобиль:" + model_avto + " " + "Количество выездов/заездов: " + col_viezd;
    }
}
