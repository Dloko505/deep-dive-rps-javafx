package edu.cnm.deepdive.rps.model;

import java.util.Random;

public enum Breed {
  ROCK, PAPER,SCISSORS;

  public Breed play(Breed opponent) {
    Breed victor = this;
    switch (this) {
      case ROCK:
        if (opponent == PAPER) {
          victor = opponent;
        }
        break;
      case PAPER:
        if (opponent == SCISSORS) {
          victor = opponent;
        }
        break;
      case SCISSORS:
        if (opponent == ROCK) {
          victor = opponent;
        }
        break;
    }
    return victor;
  }
  public static Breed random(Random rng) {
    Breed[] choices = values();
    return choices[rng.nextInt(choices.length)];
  }
}
