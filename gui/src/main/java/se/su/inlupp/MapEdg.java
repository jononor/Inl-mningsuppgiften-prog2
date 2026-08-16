package se.su.inlupp;

import java.util.List;

public class MapEdg {
    private List<Edge<CityPlace>> edges;

    private Edge<CityPlace> edgen;

    public List<Edge<CityPlace>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<CityPlace>> edges) {
        this.edges = edges;
    }

    public int getSizeofEdges() {
        return edges.size();
    }

    public void setEdge(Edge<CityPlace> edge) {
        this.edgen = edge;
    }

    public int getWeight() {
        return edgen.getWeight();
    }

    public void setWeight(int weight) {
        edgen.setWeight(weight);
    }

    public CityPlace getDestination() {
        return edgen.getDestination();
    }

    public String getName() {
        return edgen.getName();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( Edge<CityPlace> edge : edges) {
            if (edge instanceof Edgee<CityPlace>) {
                sb.append(((Edgee<CityPlace>) edge).toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
