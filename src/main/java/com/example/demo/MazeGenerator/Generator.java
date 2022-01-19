package com.example.demo.MazeGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    Random rand = new Random();
    Utils utils = new Utils();

    public ArrayList<Location> calculateMazeLocs(final Location first, final Location second) {

        ArrayList<Location> locList = new ArrayList<>();
        int lastPercent = 0;

        final Location start = first.clone();
        final Location finish;


        finish = utils.devideLocation(start.clone(), second);


        Location iAmHere = start.clone();
        ArrayList<Location> whereWasI = new ArrayList<>();
        ArrayList<Location> visitedLocs = new ArrayList<>();
        ArrayList<KoozuPair<Location, Location>> points = new ArrayList<>();
        int surfaceArea = (int) (Math.abs(start.getX() - finish.getX()) * Math.abs(start.getZ() - finish.getZ()));

        while (true) {
            lastPercent = notifyPercent(locList.size(), surfaceArea, lastPercent);

            ArrayList<Location> possibleLocs = utils.getPossibleBlocksAround(iAmHere, start, finish, visitedLocs);
            if (possibleLocs.isEmpty()) {
                if (whereWasI.size() <= 1) {
                    break;
                }
                whereWasI.remove(iAmHere);
                if (rand.nextInt(2) == 1) {
                    iAmHere = whereWasI.get(whereWasI.size() - 1);
                } else {
                    iAmHere = whereWasI.get(rand.nextInt(whereWasI.size()));
                }
                continue;
            }
            final Location selectedLoc = possibleLocs.get(rand.nextInt(possibleLocs.size()));
            points.add(new KoozuPair<>(iAmHere, selectedLoc));
            visitedLocs.add(selectedLoc);

            iAmHere = selectedLoc.clone();
            whereWasI.add(iAmHere);

            if (surfaceArea == visitedLocs.size()) {
                points.forEach(p -> {
                    locList.addAll(utils.multiplyLocations(p.getKey(), p.getValue(), start));
                });
                return locList;
            }
        }

        return locList;
    }

    public int notifyPercent(int currentArea , int surfacearea, int lastPercent){
        if (currentArea / surfacearea > lastPercent){
            System.out.println(((int) currentArea / surfacearea * 100) + "% Done!");
        }
        return currentArea / surfacearea;
    }
}
