package se.su.inlupp;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import java.util.*;

public class ConnectionLines {

    private List<Line> lines = new ArrayList<>();

    public void addLine(Line newLine) {
        lines.add(newLine);
    }

    public void removeLine(double xValue, double yValue) {
        Iterator<Line> iter = lines.iterator();
        while (iter.hasNext()) {
            Line line = iter.next();
            if((line.getStartX() == xValue && line.getStartY() == yValue) || (line.getEndX() == xValue && line.getEndY() == yValue)) {
                iter.remove();
            }
        }
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Line> getLines(double xValue, double yValue) {
        ArrayList<Line> affectedLines = new ArrayList<>();
        for(Line line : lines) {
            if((line.getStartX() == xValue && line.getStartY() == yValue) || (line.getEndX() == xValue && line.getEndY() == yValue)) {
                affectedLines.add(line);
            }
        }
        return affectedLines;
    }
}
