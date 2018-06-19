package edu.cnm.deepdive.rps.view;

import edu.cnm.deepdive.rps.model.Breed;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TerrainView extends Canvas {

  private static final Color[] BREED_COLORS = {Color.RED, Color.GREEN, Color.BLUE};

  private Breed[][] source;

  public void draw() {
    if (source != null) {
      GraphicsContext context = getGraphicsContext2D();
      double cellSize = Math.min(getHeight() / source.length, getWidth() / source[0].length);
      context.clearRect(0, 0, getWidth(), getHeight());
      for (int i = 0; i < source.length; i++) {
        for (int j = 0; j < source[i].length; j++) {
          context.setFill(BREED_COLORS[source[i][j].ordinal()]);
          context.fillRoundRect(j * cellSize, i * cellSize, cellSize, cellSize, 13.3, 13.3);
        }
      }
    }
  }

  public void setSource(Breed[][] source) {
    this.source = source;
  }
}
