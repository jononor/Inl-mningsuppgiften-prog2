package se.su.inlupp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Gui extends Application {
  /**
   * De här är meny valen.
   */
  private MenuItem newMap = new MenuItem("New Map");
  private MenuItem openMap = new MenuItem("Open");
  private MenuItem saveMap = new MenuItem("Save");
  private MenuItem exitMap = new MenuItem("Exit");


  //HBoxen Ansvarar för alla knappar
  HBox topCenter = new HBox();
  private Button findPath =  new Button("Find Path");
  private Button showConnection = new Button("Show Connection");
  private Button newCity = new Button("New City");
  private Button newConnection = new Button("New Connection");
  private Button changeConnection = new Button("Change Connection");
  private Button deleteCity = new Button("Remove City");
  private Button moveCity = new Button("Move City");

  /**
   * Globala variabler.
   * Som används i start metoden och de privata klasserna.
   */
  private Image background;
  private ImageView backgroundImage;
  private Pane center;
  private Scene scene;
  private Stage stage;
  private BorderPane root;

  /**
   * De två röda städerna som är markerade.
   * Exempelvis om en stad är markerad röd så ligger staden i firstCityMarked variabeln.
   * Exempelvis om två städer är markerade så ligger den andra staden i secondCityMarked variabeln.
   */
  private CityPlace firstCityMarked, secondCityMarked;

  /**
   * Skapar en instans av FileChooser
   * Återanvänder instansen istället för att alltid skapa en ny FileChooser
   */
  private FileChooser fileChooser = new FileChooser();

  /**
   * Skapar en instans av klassen CityColorHandler som lagras i variabeln.
   * Klassen hanterar vilken färg en stad ska ha.
   * Man återanvänder instansen och skapar inte nya instanser varje gång man vill bara ändra färg.
   * Instansvariabeln används i klassen NewPlaceHandler inre klass MapKlickHandler.
   */
  private CityColorHandler cityColorHandler = new CityColorHandler();
  /**
   * Variabeln håller koll på om det är okej att ändra färg på en stad.
   * Det är en global variabeln eftersom flera funktioner behöver ha tillgång till den.
   */
  private boolean changeColorCheck = true;

  /**
   * Skapar en instans av klassen MapGraph som lagras i variabeln.
   * Ideén är att återvända instansen istället för att skapa nya ListGraph.
   * Klassen är en wrapper som hanterar backend ListGraph.
   * ListGraph är backend logik som inte får förekomma i Gui.
   * Kommunikation: Gui -> MapGraph -> ListGraph.
   * Kolla i MapGraph om osäker.
   */
  private MapGraph mapGraph = new MapGraph();

  /**
   * Skapar en instans av klassen MapEdg som lagras i variabeln.
   * Ideén är att återvända instansen istället för att skapa nya Edge.
   * Edg är backend logik som inte får förekomma i Gui.
   * Kommunikation: Gui -> MapEdg -> Edge.
   * Kolla i MapEdg om osäker.
   */
  private MapEdg edg =  new MapEdg();

  /**
   * Klassen hanterar alla lines utskrivna på gui fönstret.
   * Den har operationer för att addera, ta bort eller hämta lines på gui fönstret.
   */
  private ConnectionLines cLines = new ConnectionLines();

  /**
   * Klassen hanterar alla Nodes utskrivna på gui fönstret.
   * Den har operationer för att addera, ta bort eller hämta nodes på gui fönstret.
   */
  private NodesList nodesList = new NodesList();

  /**
   * Instanser av alla knappars handler.
   * Istället för att skapa ny instanser.
   */
  private OpenHandler openHandler = new OpenHandler();
  private FindPathHandler findPathHandler = new FindPathHandler();
  private ShowConnectionHandler showConnectionHandler = new ShowConnectionHandler();
  private NewPlaceHandler newPlaceHandler = new NewPlaceHandler();
  private NewConnectionHandler newConnectionHandler = new NewConnectionHandler();
  private ChangeConnectionHandler changeConnectionHandler = new ChangeConnectionHandler();
  private DeleteCityHandler deleteCityHandler = new DeleteCityHandler();
  private MoveCityHandler moveCityHandler = new MoveCityHandler();

  public void start(Stage stage) {
    this.stage = stage;
    stage.setTitle("Pathfinder");
    root = new BorderPane();

    //Skapar BorderPanes top. Fyll med alla knappar och label.
    //Toppen på BorderPane, knapparna och menyer
    BorderPane top = new BorderPane();

    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    menuBar.getMenus().addAll(fileMenu);
    fileMenu.getItems().addAll(newMap, openMap, saveMap, exitMap);

    HBox topTop = new HBox();
    topTop.setBackground(Background.fill(Color.LIGHTGRAY));
    topTop.setStyle("-fx-font-weight: bold;");
    topTop.setAlignment(Pos.BOTTOM_LEFT);
    topTop.setSpacing(2);
    topTop.setPadding(new Insets(10, 15, 2, 6));
    topTop.getChildren().add(menuBar);

    top.setTop(topTop);

    topCenter.setBackground(Background.fill(Color.BLACK));
    topCenter.setStyle("-fx-font-weight: bold;");
    topCenter.setAlignment(Pos.CENTER);
    topCenter.setSpacing(15);
    topCenter.setPadding(new Insets(10, 15, 10, 15));
    topCenter.getChildren().addAll(findPath, showConnection, newCity, newConnection, changeConnection, deleteCity, moveCity);

    top.setCenter(topCenter);

    root.setTop(top);
    scene = new Scene(root);

    //Stänger av alla knappar tills en bild har valts genom menyn.
    findPath.setDisable(true);
    findPath.setBackground(Background.fill(Color.BLACK));
    showConnection.setDisable(true);
    showConnection.setBackground(Background.fill(Color.BLACK));
    newCity.setDisable(true);
    newCity.setBackground(Background.fill(Color.BLACK));
    newConnection.setDisable(true);
    newConnection.setBackground(Background.fill(Color.BLACK));
    changeConnection.setDisable(true);
    changeConnection.setBackground(Background.fill(Color.BLACK));
    deleteCity.setDisable(true);
    deleteCity.setBackground(Background.fill(Color.BLACK));
    moveCity.setDisable(true);
    moveCity.setBackground(Background.fill(Color.BLACK));

    //Sätter hanterare på alla knappar och la.
    newMap.setOnAction(openHandler);
    findPath.setOnAction(findPathHandler);
    showConnection.setOnAction(showConnectionHandler);
    newCity.setOnAction(newPlaceHandler);
    newConnection.setOnAction(newConnectionHandler);
    changeConnection.setOnAction(changeConnectionHandler);
    deleteCity.setOnAction(deleteCityHandler);
    moveCity.setOnAction(moveCityHandler);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Användaren ska kunna välja en fil som ska läggas in i BorderPane center.
   * FileLabelHandler ansvarar för att användaren kan göra det.
   */
  private class OpenHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      center = new Pane();

      fileChooser.setInitialDirectory(new File("."));
      File file = fileChooser.showOpenDialog(stage);

      if (file != null) {
        background = new Image(file.toURI().toString());
        backgroundImage = new ImageView(background);

        center.getChildren().add(backgroundImage);
        root.setCenter(center);

        backgroundImage.fitHeightProperty().bind(center.heightProperty());
        backgroundImage.fitWidthProperty().bind(center.widthProperty());

        topCenter.setBackground(Background.fill(Color.STEELBLUE));
        findPath.setDisable(false);
        findPath.setBackground(Background.fill(Color.WHITE));
        showConnection.setDisable(false);
        showConnection.setBackground(Background.fill(Color.WHITE));
        newCity.setDisable(false);
        newCity.setBackground(Background.fill(Color.WHITE));
        newConnection.setDisable(false);
        newConnection.setBackground(Background.fill(Color.WHITE));
        changeConnection.setDisable(false);
        changeConnection.setBackground(Background.fill(Color.WHITE));
        deleteCity.setDisable(false);
        deleteCity.setBackground(Background.fill(Color.WHITE));
        moveCity.setDisable(false);
        moveCity.setBackground(Background.fill(Color.WHITE));
        stage.sizeToScene();
      }
    }
  }

  /**
   * Användaren ska kunna söka igenom grafen efter en väg mellan två valda platser.
   * FindPathHandler ansvarar för att användaren kan göra det.
   */
  private class FindPathHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      //Felmeddelande när användaren inte har valt två platser på kartan.
      if (firstCityMarked == null || secondCityMarked == null ) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("Two places must be selected!");
        noMarkedPlaceAlert.showAndWait();
      }

      //Felmeddelande om det inte finns någon förbindelse mellan städerna.
      MapDFS dfs = new MapDFS();
      if (dfs.findPath(mapGraph.getGraph(), firstCityMarked, secondCityMarked) == null) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("The selected places must be connected");
        noMarkedPlaceAlert.showAndWait();
        return;
      }

      //Frågar användaren vilken typ av sökning algoritm som ska användas.
      TextField number = new TextField();

      GridPane grid = new GridPane();
      grid.add(new Label("Nr 0 = dept first search. Nr 1 = breath first search"),0 , 0);

      grid.add(number, 0, 1);
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setAlignment(Pos.CENTER);

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Choose algorithm");
      alert.setHeaderText("Choose your brefered search algorithm, DFS (0) or BFS (1)");
      alert.getDialogPane().setContent(grid);
      alert.getDialogPane().setMinWidth(300);
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK) {
        String textNumber = number.getText();
        if (textNumber == null || textNumber.isEmpty()) {
          Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
          alreadyConnectedAlert.setTitle("Error!");
          alreadyConnectedAlert.setHeaderText("You need to enter a number between 0 and 1");
          alreadyConnectedAlert.showAndWait();
          return;
        }
        try {
          int num = Integer.parseInt(textNumber);
          if (num < 0 || num > 1) {
            Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
            alreadyConnectedAlert.setTitle("Error!");
            alreadyConnectedAlert.setHeaderText("you can only enter numbers between 0 and 1");
            alreadyConnectedAlert.showAndWait();
            return;
          }
          if (num == 0) {
            MapPath path = new MapPath(dfs.findPath(mapGraph.getGraph(), firstCityMarked, secondCityMarked));
            MapPathHandler(path);
          } else if (num == 1) {
            MapBFS bfs = new MapBFS();
            MapPath path = new MapPath(bfs.findPath(mapGraph.getGraph(), firstCityMarked, secondCityMarked));
            MapPathHandler(path);
          }

        } catch (Exception e) {
          Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
          alreadyConnectedAlert.setTitle("Error!");
          alreadyConnectedAlert.setHeaderText("you can only enter numbers");
          alreadyConnectedAlert.showAndWait();
        }
      }
    }

    /**
     * Lägg till rätt logik.
     * Om användaren klickar på knappen "Find Path" efter att först markerat två plastser som har förbindelse.
     * Var man börjar och slutar, vilka platser och förbindelser som passeras, hur lång tid varje delsträcka tar och hur lång tid resan tar totalt.
     */
    private void MapPathHandler(MapPath path) {
      TextArea textArea = new TextArea();
      GridPane gridTextArea = new GridPane();
      edg.setEdges(path.getEdges());

      textArea.setText(edg.toString());

      textArea.appendText("Total " + path.getTotalWeight());

      gridTextArea.add(textArea, 0, 0);
      gridTextArea.setHgap(10);
      gridTextArea.setVgap(10);
      gridTextArea.setAlignment(Pos.CENTER);

      Alert alertPath = new Alert(Alert.AlertType.CONFIRMATION);
      alertPath.setTitle("Message");
      alertPath.setHeaderText("The Path from " + firstCityMarked.getPlaceName() + " to " + secondCityMarked.getPlaceName());
      alertPath.getDialogPane().setContent(gridTextArea);
      alertPath.getDialogPane().setMinWidth(300);
      alertPath.showAndWait();
    }

  }

  /**
   * Användaren ska kunna se uppgifterna om förbindelsen mellan två valda platser.
   * ShowConnectionHandler ansvarar för att användaren kan göra det.
   */
  private class ShowConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      //Felmeddelandet om användaren inte väljer två platser på kartan.
      if (firstCityMarked == null || secondCityMarked == null ) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("Two places must be selected!");
        noMarkedPlaceAlert.showAndWait();
        return;
      }

      //Felmeddelandet om det inte finns någon förbindelse mellan städerna.
      if (mapGraph.getEdgeBetween(firstCityMarked, secondCityMarked) == null) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("The selected places must be connected");
        noMarkedPlaceAlert.showAndWait();
        return;
      }

      /**
       * Om två platser har förbindelse så visas uppgifter om förbindelsens namn och tid.
       * Fönstrets textrutor ska inte gå att redigera.
       * Om användaren klickar på "OK" eller "Avslut" i detta fönster stängs det.
       */
       edg.setEdge(mapGraph.getEdgeBetween(firstCityMarked, secondCityMarked));


      TextField name = new TextField();
      name.setText(edg.getName());
      name.setEditable(false);

      TextField time = new TextField();
      time.setText(Integer.toString(edg.getWeight()));
      time.setEditable(false);


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
      alert.setHeaderText("Connection from " + firstCityMarked.getPlaceName() + " to " + secondCityMarked.getPlaceName());
      alert.getDialogPane().setContent(grid);
      alert.getDialogPane().setMinWidth(300);
      alert.showAndWait();
    }
  }

  /**
   * Användaren ska kunna lägga till en ny plats.
   * NewPlaceHandler ansvarar för att användaren kan göra det.
   */
  private class NewPlaceHandler implements EventHandler<ActionEvent> {
    private double x;
    private double y;
    private boolean userLookingforNewPlace;
    //private Cursor cursor;
    @Override
    public void handle(ActionEvent event) {
      newCity.setDisable(true);
      userLookingforNewPlace = true;
      scene.setCursor(Cursor.CROSSHAIR);
      backgroundImage.setOnMouseClicked(new MapClickHandler());
    }

    private class MapClickHandler implements EventHandler<MouseEvent> {
      @Override
      public void handle(MouseEvent event) {
        if (userLookingforNewPlace == true) {
          //cursor =
          newCity.setDisable(true);
          x = event.getX();
          y = event.getY();

          TextField name = new TextField();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Name");
          alert.setHeaderText("Name of place:");
          alert.getDialogPane().setContent(name);
          alert.getDialogPane().setMinWidth(300);
          Optional<ButtonType> result = alert.showAndWait();

          String placeName  = name.getText();
          if ((placeName != null) && (!(placeName.trim().equals(""))) && (result.isPresent() && result.get() == ButtonType.OK)) {

            CityPlace newCityPlace = new CityPlace(x, y, placeName);
            newCityPlace.setOnMouseClicked(cityColorHandler);

            mapGraph.add(newCityPlace);
            nodesList.addNode(newCityPlace);

            center.getChildren().add(newCityPlace);
            center.setStyle("-fx-font-weight: bold;");

          } else {
            Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
            noMarkedPlaceAlert.setTitle("Error!");
            noMarkedPlaceAlert.setHeaderText("A place needs a name!");
            noMarkedPlaceAlert.showAndWait();
          }
        }
        scene.setCursor(Cursor.DEFAULT);
        userLookingforNewPlace = false;
        newCity.setDisable(false);
      }
    }
  }

  /**
   * Användaren ska kunna skapa nya förbindelser mellan två platser.
   * NewConnectionHandler ansvarar för att användaren kan göra det.
   */
  private class NewConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      //Felmeddelandet om användaren inte väljer två platser på kartan.
      if (firstCityMarked == null || secondCityMarked == null ) {
      Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
      noMarkedPlaceAlert.setTitle("Error!");
      noMarkedPlaceAlert.setHeaderText("Two places must be selected!");
      noMarkedPlaceAlert.showAndWait();
      return;
      }

      //Felmeddelandet om två städer är redan anslutna.
      if (mapGraph.getEdgeBetween(firstCityMarked, secondCityMarked) != null) {
        Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
        alreadyConnectedAlert.setTitle("Error!");
        alreadyConnectedAlert.setHeaderText("The cities is already connected!");
        alreadyConnectedAlert.showAndWait();
        return;
      }

      //Hanterar när två städer utan anslutning ska anslutas. Hanterar också fel med exception.
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
      alert.setHeaderText("Connection from " + firstCityMarked.getPlaceName() + " to " + secondCityMarked.getPlaceName());
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
          int weight = Integer.parseInt(textTime);

          Line connectionLine = new Line(firstCityMarked.getXValue(), firstCityMarked.getYValue(), secondCityMarked.getXValue(), secondCityMarked.getYValue());
          connectionLine.setStroke(Color.BLACK);
          connectionLine.setStrokeWidth(4);
          cLines.addLine(connectionLine);

          mapGraph.connect(firstCityMarked, secondCityMarked, textName, weight);
          center.getChildren().add(connectionLine);
        } catch (Exception e) {
          Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
          alreadyConnectedAlert.setTitle("Error!");
          alreadyConnectedAlert.setHeaderText("Enter only numbers");
          alreadyConnectedAlert.showAndWait();
        }
      }
    }
  }

  /**
   * Användaren ska kunna ändra tiden för en förbindelser.
   * ChangeConnectionHandler ansvarar för att användaren kan göra det.
   */
  private class ChangeConnectionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      //Felmeddelandet om användaren inte väljer två platser på kartan.
      if (firstCityMarked == null || secondCityMarked == null ) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("Two places must be selected!");
        noMarkedPlaceAlert.showAndWait();
        return;
      }

      //Felmeddelandet om det inte finns någon förbindelse mellan städerna.
      if (mapGraph.getEdgeBetween(firstCityMarked, secondCityMarked) == null) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("The selected places must be connected");
        noMarkedPlaceAlert.showAndWait();
        return;
      }

      /**
       * Om två platser har förbindelse så visas euppgifter om förbindelsens namn.
       * Det är möjligt för användaren att ändra tiden.
       * Textrutan för namnet ska inte gå att redigera.
       * Om användaren klickar på "OK" i fönstret sparas den nya tiden och fönstret stängs.
       * Om användaren klickar på "Cancel" stängs fönstret utan att några förändringar görs i förbindelsen.
       */
      edg.setEdge(mapGraph.getEdgeBetween(firstCityMarked, secondCityMarked));

      TextField name = new TextField();
      name.setText(edg.getName());
      name.setEditable(false);

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
      alert.setHeaderText("Connection from " + firstCityMarked.getPlaceName() + " to " + secondCityMarked.getPlaceName());
      alert.getDialogPane().setContent(grid);
      alert.getDialogPane().setMinWidth(300);
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK) {
        String textTime = time.getText();
        if (textTime.isEmpty()) {
          Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
          alreadyConnectedAlert.setTitle("Error!");
          alreadyConnectedAlert.setHeaderText("You muste enter a name and a time!");
          alreadyConnectedAlert.showAndWait();
          return;
        }
        try {
          int weight = Integer.parseInt(textTime);
          mapGraph.setConnectionWeight(firstCityMarked, secondCityMarked, weight);

        } catch (Exception e) {
          Alert alreadyConnectedAlert = new Alert(Alert.AlertType.ERROR);
          alreadyConnectedAlert.setTitle("Error!");
          alreadyConnectedAlert.setHeaderText("Enter only numbers");
          alreadyConnectedAlert.showAndWait();
        }
      }
    }
  }

  /**
   * Användaren ska kunna ta bort noder (städer) ur grafen.
   * Alla Kanter som berör den borttagna noden (städer) ska också försvinna från grafiska gränssnittet.
   * DeleteCityHandler ansvarar för att användaren kan göra det.
   */
  private class DeleteCityHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      //Felmeddelandet om användaren inte väljer en plats på kartan.
      if (firstCityMarked == null && secondCityMarked == null ) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("One place must be selected!");
        noMarkedPlaceAlert.showAndWait();
        return;
      }

      //Felmeddelandet om användaren inte väljer en plats på kartan.
      if (secondCityMarked != null ) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("Only One place must be selected!");
        noMarkedPlaceAlert.showAndWait();
        return;
      }

      try {
        mapGraph.remove(firstCityMarked);

        for (Line line : cLines.getLines(firstCityMarked.getXValue(), firstCityMarked.getYValue())) {
          center.getChildren().remove(line);
        }
        cLines.removeLine(firstCityMarked.getXValue(), firstCityMarked.getYValue());
        center.getChildren().remove(firstCityMarked);
        firstCityMarked = null;

      } catch (NoSuchElementException e) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("The city does not exist!");
        noMarkedPlaceAlert.showAndWait();
      }
    }
  }

  /**
   * Användaren ska kunna flytta noder (städer) genom att dra dem med musen (eller annan metod)-
   * Kanter som är kopplade till en flyttad nod (stad) skall följa med.
   */
  private class MoveCityHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      //Felmeddelandet om användaren inte väljer en plats på kartan.
      if (firstCityMarked == null && secondCityMarked == null ) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("One place must be selected!");
        noMarkedPlaceAlert.showAndWait();
        return;
      }

      //Felmeddelandet om användaren inte väljer en plats på kartan.
      if (firstCityMarked != null && secondCityMarked != null ) {
        Alert noMarkedPlaceAlert = new Alert(Alert.AlertType.ERROR);
        noMarkedPlaceAlert.setTitle("Error!");
        noMarkedPlaceAlert.setHeaderText("Only One place must be selected!");
        noMarkedPlaceAlert.showAndWait();
        return;
      }

      if (firstCityMarked == null && secondCityMarked != null ) {
        firstCityMarked = secondCityMarked;
        secondCityMarked = null;
      }

      changeColorCheck = false;

      firstCityMarked.setCityLines(cLines.getLines(firstCityMarked.getXValue(), firstCityMarked.getYValue()));

      System.out.println("MoveCityHandler körs! firstCityMarked = " + firstCityMarked);
      firstCityMarked.setDragCityCheck(true);
      System.out.println("dragCityCheck satt till true!");

      changeColorCheck = true;
    }
  }

  /**
   * Klassen ansvarar för logiken bakom hur städernas färg ändras.
   */
  private class CityColorHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      if (changeColorCheck == true) {
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
}
