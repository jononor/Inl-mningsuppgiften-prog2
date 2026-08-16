package se.su.inlupp;



import javafx.scene.control.*;

import javafx.scene.layout.*;

import javafx.scene.shape.Line;



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
