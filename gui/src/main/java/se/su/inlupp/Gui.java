package se.su.inlupp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    }
  }

  private static class NewConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

    }
  }

  private static class ChangeConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

    }
  }

}
