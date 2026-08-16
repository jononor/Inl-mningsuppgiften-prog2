package se.su.inlupp;


import java.util.ArrayList;

import java.util.List;

public class NodesList {

    private List<CityPlace> nodes = new ArrayList<>();

    public void addNode(CityPlace node) {
        nodes.add(node);
    }

    public CityPlace getNodeByName(String name) {
        for (CityPlace node : nodes) {
            if (node.getPlaceName().equals(name)) {
                return node;
            }
        }
        return null;
    }

}
