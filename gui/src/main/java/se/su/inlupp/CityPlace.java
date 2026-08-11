package se.su.inlupp;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class CityPlace extends Pane {
    private final int radius = 8;
    private final Label label;
    //X koordinaten.
    private double xValue;
    //Y koordinaten.
    private double yValue;

    private double startX;
    private double startY;

    //En stads färg ska alltid vara blå i början
    private Color cityColor = Color.BLUE;
    private Circle circle;
    private String placeName;

    /**
     * Det ska inte vara okej att dra städer i början
     */
    private boolean dragCityCheck = false;

    List<Line> cityLines = new ArrayList<Line>();

    public CityPlace(double x, double y, String placeName) {
        this.circle = new Circle(radius, cityColor);
        this.placeName = placeName;
        this.label = new Label(placeName);

        label.setLayoutX(radius + 3);
        label.setLayoutY(radius + 3);

        getChildren().addAll(circle, label);

        xValue = x;
        yValue = y;

        relocate(x, y);

        setOnMousePressed(new StartDragHandler());
        setOnMouseDragged(new DragHandler());
        setOnMouseReleased(new ReleaseDragHandler());
    }

    public void setDragCityCheck(boolean dragCityCheck) {
        this.dragCityCheck = dragCityCheck;
    }

    public void paintCityPlaceRed() {
        cityColor = Color.RED;
        circle.setFill(cityColor);
    }

    public void paintCityPlaceBlue() {
        cityColor = Color.BLUE;
        circle.setFill(cityColor);
    }

    public String getPlaceName() {
        return placeName;
    }

    public double getXValue() {
        return xValue;
    }

    public double getYValue() {
        return yValue;
    }

    public void setCityLines(List<Line> lines) {
        this.cityLines = lines;
    }

    public Color getCityPlaceColor() {
        return cityColor;
    }

    @Override
    public String toString() {
        return placeName;
    }

    private class StartDragHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("StartDrag körs! dragCityCheck = " + dragCityCheck);
            if (dragCityCheck == true) {
                startX = event.getX();
                startY = event.getY();
            }
        }
    }

    private class DragHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            List<Line> startLines = new ArrayList<>();
            List<Line> endLines = new ArrayList<>();


            System.out.println("Drag körs! dragCityCheck = " + dragCityCheck);
            if(dragCityCheck == true) {

                for(Line line : cityLines) {
                    if(line.getStartX() == xValue && line.getStartY() == yValue) {
                        startLines.add(line);
                    } else if(line.getEndX() == xValue && line.getEndY() == yValue) {
                        endLines.add(line);
                    }
                }

                double newX = getLayoutX() + event.getX() - startX;
                double newY = getLayoutY() + event.getY() - startY;

                for(Line line : startLines) {
                    line.setStartX(newX);
                    line.setStartY(newY);
                }

                for(Line line : endLines) {
                    line.setEndX(newX);
                    line.setEndY(newY);
                }

                relocate(newX, newY);
                xValue = newX;
                yValue = newY;
            }
        }
    }

    private class ReleaseDragHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            setDragCityCheck(false);
        }
    }

}
