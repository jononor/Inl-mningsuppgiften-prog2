package se.su.inlupp;

import java.util.ArrayList;

public class DFSPathFinder<T> implements PathFinder<T> {

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
    ArrayList<T> allNodes = (ArrayList<T>) graph.getNodes();
    if (allNodes.contains(from) && allNodes.contains(to)) {
      ArrayList<T> visitedNodes = new ArrayList<T>();
      ArrayList<Edge<T>> visitedEdges = new ArrayList<>();
      visit(from, visitedNodes, visitedEdges , graph);
      if (visitedNodes.contains(to)) {
        return new Pathh(from, to, visitedNodes, visitedEdges , graph);
      }

    } else {
      throw new NullPointerException("Noderna måste finnas i grafen");
    }
    return null;
  }

  private void visit(T from, ArrayList<T> visitedNodes, ArrayList<Edge<T>> visitedEdges, Graph<T> graph) {
    visitedNodes.add(from);
    ArrayList<Edge<T>> edges = (ArrayList<Edge<T>>) graph.getEdgesFrom(from);
    for (Edge<T> edge : edges ) {
      visitedEdges.add(edge);
      T destination = edge.getDestination();
      if(!visitedNodes.contains(destination)) {
        visit(destination, visitedNodes, visitedEdges, graph);
      }
    }
  }


}

