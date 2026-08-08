package se.su.inlupp;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CityPlace extends Pane {
    private final int radius = 8;
    private final Label label;
    //X koordinaten.
    private double xValue;
    //Y koordinaten.
    private double yValue;

    //En stads färg ska alltid vara blå i början
    private Color cityColor = Color.BLUE;
    private Circle circle;
    private String placeName;

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

    public Color getCityPlaceColor() {
        return cityColor;
    }

    @Override
    public String toString() {
        return placeName;
    }
}
