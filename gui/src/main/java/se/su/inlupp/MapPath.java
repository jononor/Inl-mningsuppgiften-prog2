package se.su.inlupp;

import java.util.Iterator;
import java.util.List;

public class MapPath {

    private Path<CityPlace> path;

    public MapPath(Path path) {
        this.path = path;
    }

    public CityPlace getStart() {
        return path.getStart();
    }

    public CityPlace getEnd() {
        return path.getEnd();
    }

    public int getTotalWeight() {
        return path.getTotalWeight();
    }

    public List<Edge<CityPlace>> getEdges() {
        return path.getEdges();
    };

    public List<CityPlace> getNodes() {
        return path.getNodes();
    }

    public Iterator<Edge<CityPlace>> iterator() {
        return path.iterator();
    }

    public String toString() {
        return path.toString();
    }
}
