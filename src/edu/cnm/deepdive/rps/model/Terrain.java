package edu.cnm.deepdive.rps.model;

import java.util.Arrays;
import java.util.Random;

public class Terrain {

  public static final int DEFAULT_SIZE = 50;

  private static final int[][] NEIGHBOR_OFFSETS = {
      {-1, 0},
      {0, -1}, {0, 1},
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
      int playerRow = rng.nextInt(cells.length);
      int playerCol = rng.nextInt(cells[playerRow].length);
      Breed player = cells[playerRow][playerCol];
      int[] opponentLocation = getRandomNeighbor(playerRow, playerCol);
      Breed opponent = cells[opponentLocation[0]][opponentLocation[1]];
      if (player.play(opponent) == player) {
        cells[opponentLocation[0]][opponentLocation[1]] = player;
      } else {
        cells[playerRow][playerCol] = opponent;
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
  //TODO add public void mixing (int pairs) method. This should select a number of random pairs
  // (not necessarily adjacent), and swap them.
}
