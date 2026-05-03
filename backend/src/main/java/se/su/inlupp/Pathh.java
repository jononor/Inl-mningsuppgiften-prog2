package se.su.inlupp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pathh<T> implements Path<T> {
    private T startNode;
    private T endNode;
    private ListGraph<T> graph;

    public Pathh(T startNode, T endNode, ListGraph<T> graph) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.graph = graph;
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

        return totalWeight;
    }

    @Override
    public List<Edge<T>> getEdges() {
        //return edges;
        return null;
    }

    @Override
    public List<T> getNodes() {
        return null;
    }

    @Override
    public Iterator<Edge<T>> iterator() {
        //return graph.iterator();
        return null;
    }

    @Override
    public String toString() {
        return null; //inte klar
    }

}
