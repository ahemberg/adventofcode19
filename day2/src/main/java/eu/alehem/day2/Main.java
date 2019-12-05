package eu.alehem.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

  private static final String PATH =
      "/Users/ahemberg/repo/personal/eu.alehem.adventofcode19/day2/src/main/resources/";
  private static final int DESIRED_STATE = 19690720;
  private List<Integer> instructions;

  private Main(final List<Integer> instructions, final int noun, final int verb) {
    this.instructions = new ArrayList<>(instructions);
    this.instructions.set(1, noun);
    this.instructions.set(2, verb);
  }

  public static void main(String... args) throws IOException {
    List<Integer> instructions =
        Stream.of(Files.readString(Paths.get(PATH + "instructions.csv")).split(","))
            .map(Integer::valueOf)
            .collect(Collectors.toList());

    IntStream.range(0, 99)
        .forEach(
            noun ->
                IntStream.range(0, 99)
                    .forEach(
                        verb -> {
                          Main program = new Main(instructions, noun, verb);
                          program.compute(0);
                          if (program.getResult() == DESIRED_STATE) {
                            System.out.println("noun:" + noun + " verb: " + verb);
                            System.exit(0);
                          }
                        }));
  }

  private int getResult() {
    return instructions.get(0);
  }

  private void compute(final int address) {
    final int instruction = instructions.get(address);
    final int noun = instructions.get(address + 1);
    final int verb = instructions.get(address + 2);
    final int storeLocation = instructions.get(address + 3);
    switch (instruction) {
      case 1:
        instructions.set(storeLocation, instructions.get(noun) + instructions.get(verb));
        break;
      case 2:
        instructions.set(storeLocation, instructions.get(noun) * instructions.get(verb));
        break;
      case 99:
        return;
    }
    compute(address + 4);
  }
}
