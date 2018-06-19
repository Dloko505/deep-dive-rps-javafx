package edu.cnm.deepdive.rps;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    ClassLoader classloader = getClass().getClassLoader();
    ResourceBundle bundle = ResourceBundle.getBundle("resources/ui-strings");
    FXMLLoader fxmlLoader = new FXMLLoader(classloader.getResource
        ("resources/main.fxml"), bundle);
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root, 620, 760);
    stage.setTitle(bundle.getString("window_title"));
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
