package se.su.inlupp;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CityPlace extends Pane {
    private final int radius = 8;
    private Color cityColor = Color.BLUE;

    private Circle circle;
    private final Label label;

    public CityPlace(double x, double y, String placeName) {
        this.circle = new Circle(radius, cityColor);
        this.label = new Label(placeName);

        label.setLayoutX(radius + 3);
        label.setLayoutY(radius + 3);

        getChildren().addAll(circle, label);
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

    public Color getCityPlaceColor() {
        return cityColor;
    }

}
