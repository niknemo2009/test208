package com.parking;


public enum ProgramScreenSize {
    HEIGHT(600), WIDTH(1000), BUTTON_WIDTH(130), BUTTON_HEIGHT(30);

    private int size;
    ProgramScreenSize(int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }
}
