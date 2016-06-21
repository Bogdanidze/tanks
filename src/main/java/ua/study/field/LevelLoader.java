package ua.study.field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ua.study.element.barrier.Barrier;
import ua.study.element.barrier.BarrierType;

class LevelLoader {

    private final static String ROW_SEPARATOR = ":";
    private final static String BARRIER_SEPARATOR = ";";
    private final static String COORDINATE_SEPARATOR = ",";

    public static List<Barrier> loadLevel(String levelFileName) throws IOException {
        List<Barrier> tempBarriers = new ArrayList<>();
        try (
                InputStream inputStream = BattlePanel.class.getResourceAsStream(levelFileName);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("line = " + line);
                if (line.contains(ROW_SEPARATOR)) {
                    tempBarriers.addAll(loadLine(line));
                } else {
                    System.out.println("Level file has on-error entry!");
                }
            }
            System.out.println("Loaded barriers = " + tempBarriers);
        }
        return tempBarriers;
    }

    private static List<Barrier> loadLine(String line) {
        String[] typeWithCoordinates = line.split(ROW_SEPARATOR);

        BarrierType barrierType = BarrierType.getBarrierType(typeWithCoordinates[0]);
        System.out.println("barrierType  = " + barrierType);
        if (barrierType == null) {
            System.out.println("Line '" + line + "' does not contain proper barrier type!");
            return Collections.emptyList();
        }

        List<Barrier> tempBarriers = new ArrayList<>();
        for (String coordinate : typeWithCoordinates[1].split(BARRIER_SEPARATOR)) {
            String[] coordinates = coordinate.split(COORDINATE_SEPARATOR);
            System.out.println("coordinates = " + Arrays.toString(coordinates));
            tempBarriers.add(
                    new Barrier(barrierType, coordinates[0], coordinates[1]));
        }
        return tempBarriers;
    }
}
