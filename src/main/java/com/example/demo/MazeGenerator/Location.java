package com.example.demo.MazeGenerator;

public class Location implements Cloneable{
    private double x;
    private double y;
    private double z;

    public Location(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public Location clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }
    public Location add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }
    public Location subtract(int x, int y, int z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }
    public int getBlockX() {
        return locToBlock(this.x);
    }
    public int getBlockY() {
        return locToBlock(this.y);
    }
    public int getBlockZ() {
        return locToBlock(this.z);
    }

    public static int locToBlock(double loc) {
        final int floor = (int) loc;
        return floor == loc ? floor : floor - (int) (Double.doubleToRawLongBits(loc) >>> 63);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;

        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        return true;
    }

}
