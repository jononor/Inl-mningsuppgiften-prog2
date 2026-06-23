package se.su.inlupp;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.Optional;

public class Gui extends Application {

  private Label fileLabel = new Label("File");
  private Button findPath =  new Button("Find Path");
  private Button showConnection = new Button("Show Connection");
  private Button newPlace = new Button("New Place");
  private Button newConnection = new Button("New Connection");
  private Button changeConnection = new Button("Change Connection");

  private Image background;
  private ImageView backgroundImage;
  private Pane center;

  private Scene scene;
  private Stage stage;
  private BorderPane root;

  private CityPlace firstCityMarked, secondCityMarked;
  private CityColorHandler cityColorHandler = new CityColorHandler();


  public void start(Stage stage) {

    this.stage = stage;
    stage.setTitle("Pathfinder");
    root = new BorderPane();


    HBox top = new HBox();
    top.setAlignment(Pos.CENTER);
    top.setSpacing(15);
    top.setPadding(new Insets(10, 15, 10, 15));
    ObservableList<Node> children = top.getChildren();
    children.add(fileLabel);
    children.add(findPath);
    children.add(showConnection);
    children.add(newPlace);
    children.add(newConnection);
    children.add(changeConnection);
    root.setTop(top);

    scene = new Scene(root);
    findPath.setDisable(true);
    showConnection.setDisable(true);
    newPlace.setDisable(true);
    newConnection.setDisable(true);
    changeConnection.setDisable(true);


    //logik för att vad som händer om man trycker på en blå eller röd cirkel
    //det ska inte gå att markera två platser


    fileLabel.setOnMousePressed(new FileLabelHandler());
    findPath.setOnAction(new FindPathHandler());
    showConnection.setOnAction(new ShowConnectionHandler());
    newPlace.setOnAction(new NewPlaceHandler());
    newConnection.setOnAction(new NewConnectionHandler());
    changeConnection.setOnAction(new ChangeConnectionHandler());

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private class FileLabelHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      center = new Pane();

      FileChooser fileChooser = new FileChooser();
      File file = fileChooser.showOpenDialog(stage);

      if (file != null) {
        background = new Image(file.toURI().toString());
        backgroundImage = new ImageView(background);

        center.getChildren().add(backgroundImage);
        root.setCenter(center);
      }
      findPath.setDisable(false);
      showConnection.setDisable(false);
      newPlace.setDisable(false);
      newConnection.setDisable(false);
      changeConnection.setDisable(false);

      stage.sizeToScene();
    }
  }

  private class FindPathHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

      if (firstCityMarked == null || secondCityMarked == null ) {
        //Hanterar felet när användaren inte väljer två platser på kartan
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("Two places must be selected!");
        noMarkedPlaceAlert.showAndWait();
      }

      //hanterar om det inte finns någon förbindelse mellan städerna
      /*
      Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
      noMarkedPlaceAlert.setTitle("Error!");
      noMarkedPlaceAlert.setHeaderText("The selected places must be connected");
      noMarkedPlaceAlert.showAndWait();
      */

      //Om användaren klickar på knappen "Find Path" efter att först markerat två plastser som har förbindelse
      //Dialogrutan path skall innehålla all relevant information om resan
      //Alltså var man börjar och slutar, vilka platser och förbindelser som passeras, hur lång tid varje delsträcka tar och hur lång tid resan tar totalt
      /*
      TextArea path = new TextArea();
      TextField name = new TextField();
      TextField time = new TextField();

      GridPane grid = new GridPane();
      grid.add(path, 0, 0);
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setAlignment(Pos.CENTER);

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Message");
      alert.setHeaderText("The Path from city1 to city2");
      alert.getDialogPane().setContent(grid);
      alert.getDialogPane().setMinWidth(300);
      alert.showAndWait();
       */


    }
  }

  private class ShowConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

      if (firstCityMarked == null || secondCityMarked == null ) {
        //Hanterar felet när användaren inte väljer två platser på kartan
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("Two places must be selected!");
        noMarkedPlaceAlert.showAndWait();
      }


      //hanterar om det inte finns någon förbindelse mellan städerna
      /*
      Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
      noMarkedPlaceAlert.setTitle("Error!");
      noMarkedPlaceAlert.setHeaderText("The selected places must be connected");
      noMarkedPlaceAlert.showAndWait();
      */

      //Om två platser har förbindelse så visas ett fönster med uppgifter om förbindelsens namn och tid.
      //Fönstrets textrutor ska inte gå att redigera.
      //Om användaren klickar på "OK" eller "Avslut" i detta fönster stängs det.
      /*
      TextField name = new TextField();
      TextField time = new TextField();

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
      alert.showAndWait();
       */

    }
  }

  private class NewPlaceHandler implements EventHandler<ActionEvent> {
    private double x;
    private double y;
    private boolean userLookingforNewPlace;

    @Override
    public void handle(ActionEvent event) {
      newPlace.setDisable(true);
      userLookingforNewPlace = true;
      backgroundImage.setOnMouseClicked(new MapClickHandler());
    }

    private class MapClickHandler implements EventHandler<MouseEvent> {
      @Override
      public void handle(MouseEvent event) {
        if (userLookingforNewPlace == true) {
          newPlace.setDisable(true);
          x = event.getX();
          y = event.getY();

          TextField name = new TextField();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Name");
          alert.setHeaderText("Name of place:");
          alert.getDialogPane().setContent(name);
          alert.getDialogPane().setMinWidth(300);
          Optional<ButtonType> result = alert.showAndWait();

          if (result.isPresent() && result.get() == ButtonType.OK) {
            String placeName  = name.getText();
            CityPlace newCityPlace = new CityPlace(x, y, placeName);
            newCityPlace.setOnMouseClicked(cityColorHandler);
            center.getChildren().add(newCityPlace);
            center.setStyle("-fx-font-weight: bold;");
          }
        }

        userLookingforNewPlace = false;
        newPlace.setDisable(false);
      }
    }
  }

  private class NewConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

      if (firstCityMarked == null || secondCityMarked == null ) {
        //Hanterar felet när användaren inte väljer två platser på kartan
      Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
      noMarkedPlaceAlert.setTitle("Error!");
      noMarkedPlaceAlert.setHeaderText("Two places must be selected!");
      noMarkedPlaceAlert.showAndWait();
      }


      //Hanterar felet när två städer är redan anslutna
      /*
      Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
      alreadyConnectedAlert.setTitle("Error!");
      alreadyConnectedAlert.setHeaderText("The cities is already connected!");
      alreadyConnectedAlert.showAndWait();
       */


      //Hanterar när två städer utan anslutning ska anslutas. Hanterar också fel.
      /*
      TextField name = new TextField();
      TextField time = new TextField();

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

  private class ChangeConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

      if (firstCityMarked == null || secondCityMarked == null ) {
        //Hanterar felet när användaren inte väljer två platser på kartan
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("Two places must be selected!");
        noMarkedPlaceAlert.showAndWait();
      }


      //hanterar om det inte finns någon förbindelse mellan städerna
      /*
      Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
      noMarkedPlaceAlert.setTitle("Error!");
      noMarkedPlaceAlert.setHeaderText("The selected places must be connected");
      noMarkedPlaceAlert.showAndWait();
      */

      //Om två platser har förbindelse så visas ett fönster med uppgifter om förbindelsens namn och det är möjligt för användaren att ändra tiden.
      //Textrutan för namnet ska inte gå att redigera.
      //Om användaren klickar på "OK" i fönstret sparas den nya tiden och fönstret stängs.
      //Kom ihåg att grafen är oriktad, så ändringar åt ett håll måste speglas åt det andra hållet
      //Om användaren klickar på "Cancel" stängs fönstret utan att några förändringar görs i förbindelsen.
      /*
      TextField name = new TextField();
      TextField time = new TextField();

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
       */

    }
  }

  private class CityColorHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      CityPlace current = (CityPlace) event.getSource();
      Color color = current.getCityPlaceColor();
      if (color == Color.RED) {
        if (firstCityMarked == current) {
          firstCityMarked = null;
          current.paintCityPlaceBlue();
        } else if (secondCityMarked == current && current != firstCityMarked) {
          secondCityMarked = null;
          current.paintCityPlaceBlue();
        }
      } else if (color == Color.BLUE) {
        if (firstCityMarked == null) {
          firstCityMarked = current;
          current.paintCityPlaceRed();
        } else if (secondCityMarked == null && current != firstCityMarked) {
          secondCityMarked = current;
          current.paintCityPlaceRed();
        }
      }
    }
  }
}
