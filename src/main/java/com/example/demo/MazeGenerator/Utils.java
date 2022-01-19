package com.example.demo.MazeGenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {

    public KoozuPair<Location, Location> getInvertedLocations(final Location loc1, final Location loc2) {
        int startx = (int) Math.min(loc1.getX(), loc2.getX());
        int endx = (int) Math.max(loc1.getX(), loc2.getX());

        int starty =(int) Math.min(loc1.getY(), loc2.getY());
        int endy = (int)Math.max(loc1.getY(), loc2.getY());

        int startz =(int) Math.min(loc1.getZ(), loc2.getZ());
        int endz = (int)Math.max(loc1.getZ(), loc2.getZ());

        Location start = new Location(startx, starty, startz);
        Location finish = new Location(endx, endy, endz);
        return new KoozuPair<>(start, finish);
    }

    //Check if loc is in the 2d square of corner 1 & 2
    public boolean isInRegion(Location location, Location corner1, Location corner2) {
        int doubleCheck = 0;

        if (location.getX() >= corner1.getX() && location.getX() <= corner2.getX()) {
            doubleCheck += 1;
        }
        if (location.getZ() >= corner1.getZ() && location.getZ() <= corner2.getZ()) {
            doubleCheck += 1;
        }

        if (doubleCheck == 2) {
            return true;
        }
        return false;
    }

    //Devides pos2 to 1/4 of original surface area
    public Location devideLocation(final Location pos1, final Location pos2) {
        return pos1.clone().add((pos2.getX() - pos1.getX()) / 2, 0, (pos2.getZ() - pos1.getZ()) / 2);
    }

    //Checks if location is visited
    public boolean isVisited(Location loc, ArrayList<Location> list) {
        return list.contains(loc);
    }

    //Get 4 locations around a location
    public ArrayList<Location> getBlocksAround(Location loc) {
        Location aroundLoc1 = loc.clone().add(1, 0, 0);
        Location aroundLoc2 = loc.clone().subtract(1, 0, 0);

        Location aroundLoc3 = loc.clone().add(0, 0, 1);
        Location aroundLoc4 = loc.clone().subtract(0, 0, 1);

        return new ArrayList<>(Arrays.asList(aroundLoc1, aroundLoc2, aroundLoc3, aroundLoc4));
    }

    //Get 4 locations around & return each if not visited and in region
    public ArrayList<Location> getPossibleBlocksAround(Location loc, Location pos1, Location pos2, ArrayList<Location> visitedLocs) {
        ArrayList<Location> locs = getBlocksAround(loc);
        ArrayList<Location> pLocs = new ArrayList<>();
        locs.forEach(location -> {
            if (isInRegion(location, pos1, pos2) && !isVisited(location, visitedLocs)) {
                pLocs.add(location);
            }
        });
        return pLocs;
    }

    //When stretching, multiply pairs to corects size and add wall inbetween
    public ArrayList<Location> multiplyLocations(final Location pos1, final Location pos2, final Location mazeStartLocation) {
        Location mPos1 = pos1.clone();
        Location mPos2 = pos2.clone();

        mPos1.add(pos1.getX() - mazeStartLocation.getX(), 0, pos1.getZ() - mazeStartLocation.getZ());
        mPos2.add(pos2.getX() - mazeStartLocation.getX(), 0, pos2.getZ() - mazeStartLocation.getZ());
        Location between = mPos1.clone();
        if (mPos1.getX() != mPos2.getX()) {
            between.add(mPos1.getX() < mPos2.getX() ? 1 : -1, 0, 0);
        }
        if (mPos1.getZ() != mPos2.getZ()) {
            between.add(0, 0, mPos1.getZ() < mPos2.getZ() ? 1 : -1);
        }
        return new ArrayList<>(Arrays.asList(mPos1, mPos2, between));


    }

    public ArrayList<Location> stretchTo3Times(final ArrayList<Location> locList, final Location startLoc){
        ArrayList<Location> stretchedLocs = new ArrayList<>();
        locList.forEach(loc -> {
            Location locMid = startLoc.clone().add((loc.clone().getX() - startLoc.clone().getX()) * 3, 0, (loc.clone().getZ() - startLoc.clone().getZ()) * 3);
            final Location arLoc1 = locMid.clone().add(1, 0, 1);
            final Location arLoc2 = locMid.clone().add(1, 0, 0);
            final Location arLoc3 = locMid.clone().add(1, 0, -1);
            final Location arLoc4 = locMid.clone().add(0, 0, 1);
            final Location arLoc5 = locMid.clone().add(0, 0, -1);
            final Location arLoc6 = locMid.clone().add(-1, 0, 1);
            final Location arLoc7 = locMid.clone().add(-1, 0, 0);
            final Location arLoc8 = locMid.clone().add(-1, 0, -1);
            stretchedLocs.addAll(Arrays.asList(arLoc1, arLoc2, arLoc3, arLoc4, arLoc5, arLoc6, arLoc7, arLoc8, locMid));
        });
        return stretchedLocs;
    }
    public Location splitToThird(Location pos1, Location pos2){
        return pos1.clone().add((pos2.getX() - pos1.getX()) / 3, 0, (pos2.getZ() - pos1.getZ()) / 3);
    }
}
