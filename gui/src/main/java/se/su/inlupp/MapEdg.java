package se.su.inlupp;

import java.util.List;

public class MapEdg {
    private List<Edge<CityPlace>> edges;

    public MapEdg(List<Edge<CityPlace>> edges) {
        this.edges = edges;
    }





    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( Edge<CityPlace> edge : edges) {
            if (edge instanceof Edgee<CityPlace>) {
                //Edgee<CityPlace> edgee = (Edgee<CityPlace>) edge;
                sb.append(((Edgee<CityPlace>) edge).toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
