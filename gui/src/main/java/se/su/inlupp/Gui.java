package se.su.inlupp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Optional;

public class Gui extends Application {

  private Label fileLabel = new Label("File");
  private Button findPath =  new Button("Find Path");
  private Button showConnection = new Button("Show Connection");
  private Button newPlace = new Button("New Place");
  private Button newConnection = new Button("New Connection");
  private Button changeConnection = new Button("Change Connection");

  public void start(Stage stage) {
    findPath.setOnAction(new FindPathHandler());
    showConnection.setOnAction(new ShowConnectionHandler());
    newPlace.setOnAction(new NewPlaceHandler());
    newConnection.setOnAction(new NewConnectionHandler());
    changeConnection.setOnAction(new ChangeConnectionHandler());

    stage.setTitle("Pathfinder");
    FlowPane root = new FlowPane();
    root.setOrientation(Orientation.HORIZONTAL);
    root.setAlignment(Pos.CENTER);
    root.setVgap(15);
    root.setHgap(15);

    ObservableList<Node> children = root.getChildren();
    children.add(fileLabel);
    children.add(findPath);
    children.add(showConnection);
    children.add(newPlace);
    children.add(newConnection);
    children.add(changeConnection);

    Scene scene = new Scene(root, 600, 40);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private static class FindPathHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

    }
  }

  private static class ShowConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

    }
  }

  private static class NewPlaceHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      TextField name = new TextField();

      //Markerar en ny plats
      /*
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Name");
      alert.setHeaderText("Name of place:");
      alert.getDialogPane().setContent(name);
      alert.getDialogPane().setMinWidth(300);
      Optional<ButtonType> result = alert.showAndWait();
       */
    }
  }

  private static class NewConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      TextField name = new TextField();
      TextField time = new TextField();

      //Hanterar felet när användaren inte väljer två platser i kartan
      /*
      Alert noOrToMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
      noMarkedPlaceAlert.setTitle("Error!");
      noMarkedPlaceAlert.setHeaderText("Two places must be selected!");
      noMarkedPlaceAlert.showAndWait();
       */

      //Hanterar felet när två städer är redan anslutna
      /*
      Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
      alreadyConnectedAlert.setTitle("Error!");
      alreadyConnectedAlert.setHeaderText("The cities is already connected!");
      alreadyConnectedAlert.showAndWait();
       */


      //Hanterar när två städer utan anslutning ska anslutas. Hanterar också fel.
      /*
      GridPane grid = new GridPane();
      grid.add(new Label("Name:"), 0, 0);
      grid.add(name, 1, 0);
      grid.add(new Label("Time:"), 0, 1);
      grid.add(time, 1, 1);
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setAlignment(Pos.CENTER);

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Connection");
      alert.setHeaderText("Connection from city1 to city2");
      alert.getDialogPane().setContent(grid);
      alert.getDialogPane().setMinWidth(300);
      Optional<ButtonType> result = alert.showAndWait();

      if (result.get() == ButtonType.OK) {
        String textName = name.getText();
        String textTime = time.getText();
        if (textName.isEmpty() || textTime.isEmpty()) {
          Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
          alreadyConnectedAlert.setTitle("Error!");
          alreadyConnectedAlert.setHeaderText("You muste enter a name and a time!");
          alreadyConnectedAlert.showAndWait();
          return;
        }

        try {
          Integer.parseInt(textTime);
        } catch (Exception e) {
          Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
          alreadyConnectedAlert.setTitle("Error!");
          alreadyConnectedAlert.setHeaderText("Enter only numbers");
          alreadyConnectedAlert.showAndWait();
        }
      }
      */

    }
  }

  private static class ChangeConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

    }
  }

}
