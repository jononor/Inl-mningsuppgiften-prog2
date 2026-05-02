package se.su.inlupp;

import java.util.List;

public class Pathh<T> implements Path<T> {
    private T startNode;
    private T endNode;

    public Pathh(T startNode, T endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @Override
    public T getStart() {
        return startNode;
    }

    @Override
    public T getEnd() {
        return endNode;
    }

    @Override
    public int getTotalWeight() {
        int totalWeight = 0;

        //... kod som itererar tills slutnoden

        return totalWeight;
    }

    @Override
    public List<Edge<T>> getEdges() {
        return null; // inte klar
    }

    @Override
    public List<T> getNodes() {
        return null; //inte klar
    }

    @Override
    public String toString() {
        return null; //inte klar
    }

}
