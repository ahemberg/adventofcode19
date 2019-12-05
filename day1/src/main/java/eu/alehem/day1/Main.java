package eu.alehem.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

  private static final String PATH =
      "/Users/ahemberg/repo/personal/eu.alehem.adventofcode19/day1/src/main/resources/";

  public static void main(String... args) throws IOException {
    int fuel =
        Files.lines(Paths.get(PATH + "modules.csv"))
            .map(Double::valueOf)
            .map(Main::fuelCalc)
            .reduce(Integer::sum)
            .orElse(0);
    System.out.println(fuel);
  }

  private static int fuelCalc(Double mass) {
    int fuel = (int) (mass/3) - 2;
    fuel += fuel > 0 ? fuelCalc((double) fuel) : 0;
    return fuel;
  }
}

//5180690