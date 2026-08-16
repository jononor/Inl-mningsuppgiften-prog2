package se.su.inlupp;

import java.util.List;

public class MapEdg {
    private List<Edge<CityPlace>> edges;

    private Edge<CityPlace> edge;

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
        this.edge = edge;
    }

    public int getWeight() {
        return edge.getWeight();
    }

    public void setWeight(int weight) {
        edge.setWeight(weight);
    }

    public CityPlace getDestination() {
        return edge.getDestination();
    }

    public String getName() {
        return edge.getName();
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
