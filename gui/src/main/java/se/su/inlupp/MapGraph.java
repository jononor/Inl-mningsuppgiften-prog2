package se.su.inlupp;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

//Wrappar Listgraph
public class MapGraph {

    private Graph<CityPlace> graph = new ListGraph();

    public void add(CityPlace city) {
        graph.add(city);
    }

    public void remove(CityPlace city) {
        graph.remove(city);
    }

    public boolean hasNode(CityPlace city) {
        return graph.hasNode(city);
    }

    public void connect(CityPlace cityOne, CityPlace cityTwo, String name, int weight) {
        graph.connect(cityOne, cityTwo, name, weight);
    }

    public void disconnect(CityPlace cityOne, CityPlace cityTwo) {
        graph.disconnect(cityOne, cityTwo);
    }

    public void setConnectionWeight(CityPlace cityOne, CityPlace cityTwo, int weight) {
        graph.setConnectionWeight(cityOne, cityTwo, weight);
    }

    public Set<CityPlace> getNodes() {
        return graph.getNodes();
    }

    public Collection<Edge<CityPlace>> getEdgesFrom(CityPlace city) {
        return graph.getEdgesFrom(city);
    }

    public Edge<CityPlace> getEdgeBetween(CityPlace cityOne, CityPlace cityTwo) {
        return graph.getEdgeBetween(cityOne, cityTwo);
    }

    public Graph<CityPlace> getGraph() {
        return graph;
    }

    public Iterator<CityPlace> iterator() {
        return graph.iterator();
    }

    public String toString() {
        return graph.toString();
    }
}
