package se.su.inlupp;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NodesList {

    private List<CityPlace> nodes = new ArrayList<>();

    public void addNode(CityPlace node) {
        nodes.add(node);
    }

    public void removeNode(double xValue, double yValue) {
        Iterator<CityPlace> iter = nodes.iterator();
        while (iter.hasNext()) {
            CityPlace city = iter.next();
            if(city.getXValue() == xValue && city.getYValue() == yValue) {
                iter.remove();
            }
        }
    }

    public List<CityPlace> getNodes() {
        return nodes;
    }

    public List<CityPlace> getNodes(double xValue, double yValue) {
        ArrayList<CityPlace> affectedNodes = new ArrayList<>();
        for(CityPlace city : nodes) {
            if(city.getXValue() == xValue && city.getYValue() == yValue) {
                affectedNodes.add(city);
            }
        }
        return affectedNodes;
    }

}
