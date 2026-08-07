package se.su.inlupp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DFSPathFinder<T> implements PathFinder<T> {

  private boolean found;

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {

    Set<T> set = graph.getNodes();
    ArrayList<T> allNodes = new ArrayList<>(set);
    if (allNodes.contains(from) && allNodes.contains(to)) {
      ArrayList<T> visitedNodes = new ArrayList<T>();
      ArrayList<Edge<T>> visitedEdges = new ArrayList<>();

      found = false;
      visit(from, to, visitedNodes, visitedEdges, graph);
      if (visitedNodes.contains(to)) {
        return new Pathh(from, to, visitedNodes, visitedEdges, graph);
      }

    } else {
      throw new NullPointerException("Noderna måste finnas i grafen");
    }
    return null;
  }

  private void visit(T from, T to, ArrayList<T> visitedNodes, ArrayList<Edge<T>> visitedEdges, Graph<T> graph) {
    visitedNodes.add(from);
    if (from.equals(to)) {
      found = true;
      return ;
    }
    ArrayList<Edge<T>> edges = (ArrayList<Edge<T>>) graph.getEdgesFrom(from);

    for (Edge<T> edge : edges) {
      if(found == true) {
        return;
      }
      T destination = edge.getDestination();
      if (!visitedNodes.contains(destination)) {
        visitedEdges.add(edge);
        visit(destination, to, visitedNodes, visitedEdges, graph);
        if (found == false) {
          visitedEdges.remove(edge);
        }
      }
    }
  }
}

