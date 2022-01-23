package com.example.demo.MazeGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    Random rand = new Random();
    Utils utils = new Utils();

    public ArrayList<Location> calculateMazeLocs(final Location first, final Location second) {

        ArrayList<Location> locList = new ArrayList<>();

        final Location finish;
        finish = utils.devideLocation(first, second);


        Location iAmHere = first.clone();
        ArrayList<Location> whereWasI = new ArrayList<>();
        ArrayList<Location> visitedLocs = new ArrayList<>();
        ArrayList<KoozuPair<Location, Location>> points = new ArrayList<>();
        int surfaceArea = (Math.abs(first.getX() - finish.getX()) * Math.abs(first.getZ() - finish.getZ()));

        while (true) {
            ArrayList<Location> possibleLocs = utils.getPossibleBlocksAround(iAmHere, first, finish, visitedLocs);
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
                    locList.addAll(utils.multiplyLocations(p.getKey(), p.getValue(), first));
                });
                return locList;
            }
        }
        return locList;
    }
}
