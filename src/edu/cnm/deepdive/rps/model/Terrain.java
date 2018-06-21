package edu.cnm.deepdive.rps.model;

import java.util.Arrays;
import java.util.Random;

public class Terrain {

  public static final int DEFAULT_SIZE = 50;

  private static final int[][] NEIGHBOR_OFFSETS = {
      {-1, 0},
      {0, -1},
      {0, 1},
      {1, 0}
  };

  private Breed[][] cells;
  private Random rng;
  private long iterations;

  public Terrain(Random rng) {
    this.rng = rng;
    cells = new Breed[DEFAULT_SIZE][DEFAULT_SIZE];
    reset();
  }

  public void reset() {
    for (Breed[] row : cells) {
      for (int i = 0; i < row.length; i++) {
        row[i] = Breed.random(this.rng);
      }
    }
    iterations = 0;
  }

  public void iterate(int steps) {
    for (int i = 0; i < steps; i++) {
      int[] playerLocation = randomLocation();
      Breed player = cells[playerLocation[0]][playerLocation[1]];
      int[] opponentLocation = getRandomNeighbor(playerLocation[0], playerLocation[1]);
      Breed opponent = cells[opponentLocation[0]][opponentLocation[1]];
      if (player.play(opponent) == player) {
        cells[opponentLocation[0]][opponentLocation[1]] = player;
      } else {
        cells[playerLocation[0]][playerLocation[1]] = opponent;
      }
    }
    iterations += steps;
  }

  protected int[] getRandomNeighbor(int row, int col) {
    int[] offsets = NEIGHBOR_OFFSETS[rng.nextInt(NEIGHBOR_OFFSETS.length)];
    int opponentRow = (row + offsets[0] + cells.length) % cells.length;
    int opponentCol = (col + offsets[1] + cells[opponentRow].length)
        % cells[opponentRow].length;
    return new int[]{opponentRow, opponentCol};
  }

  public Breed[][] getCells() {
    return cells;
  }

  public long getIterations() {
    return iterations;
  }
  private int[] randomLocation() {
    int row = rng.nextInt(cells.length);
    return new int[] {
        row,
        rng.nextInt(cells.length)
    };
  }

  public void mix(int numPairs) {
    for (int i = 0; i < numPairs; i++) {
      int[] location1 = randomLocation();
      int[] location2 = randomLocation();
      while (Arrays.equals(location1, location2)) {
        location2 = randomLocation();
      }
      Breed temp = cells[location1[0]][location1[1]];
      cells[location1[0]][location1[1]] = cells[location2[0]][location2[1]];
      cells[location2[0]][location2[1]] = temp;
    }
  }
}
