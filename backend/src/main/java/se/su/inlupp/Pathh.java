package se.su.inlupp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Pathh<T> implements Path<T> {
    private T startNode;
    private T endNode;
    private Collection<T> visitedNodes;
    private Collection<Edge<T>> visitedEdges;
    private Graph<T> graph;


    public Pathh(T startNode, T endNode, Collection<T> visitedNodes, Collection<Edge<T>> visitedEdges, Graph<T> graph) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.visitedNodes = visitedNodes;
        this.visitedEdges = visitedEdges;
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
        for (Edge<T> edge : visitedEdges) {
            totalWeight += edge.getWeight();
        }
        return totalWeight;
    }

    @Override
    public List<Edge<T>> getEdges() {
        List<Edge<T>> edges = new ArrayList<>();
        for (Edge<T> edge : visitedEdges) {
            edges.add(edge);
        }
        return edges;
    }

    @Override
    public List<T> getNodes() {
        List<T> nodes = new ArrayList<>();
        for (T node : visitedNodes) {
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public Iterator<Edge<T>> iterator() {
        return visitedEdges.iterator();
    }

    @Override
    public String toString() {
        String  str = "Start node:" + getStart() + " End node:" + getEnd() + " All nodes:" + getNodes() + " TotalWeight:" + getTotalWeight();
        return str;
    }

}
