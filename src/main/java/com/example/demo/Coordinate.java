package com.example.demo;

public class Coordinate {
    private int x;
    private int y;
    private int z;

    Coordinate(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Location{" + x + "," + y + "," + z + "}";
    }
}
