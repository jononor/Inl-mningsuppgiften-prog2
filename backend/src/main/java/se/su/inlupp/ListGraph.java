package se.su.inlupp;

import java.util.*;

public class ListGraph<T> implements Graph<T> {

  private Map<T, List<Edge<T>>>  graph = new HashMap<>();

  @Override
  public void add(T node) {
    graph.putIfAbsent(node, new ArrayList<>());
  }

  @Override
  public void remove(T node) {
    if (!graph.containsKey(node)){
      throw new NoSuchElementException();
    }
    for (T otherNode : graph.keySet()) {
      List<Edge<T>> edges = graph.get(otherNode);
      Iterator<Edge<T>> iterator = edges.iterator();

      while (iterator.hasNext()) {
        Edge<T> edge = iterator.next();

        if (edge.getDestination().equals(node)) {
          iterator.remove();
        }
      }
    }
    graph.remove(node);
  }

  @Override
  public boolean hasNode(T node) {
    return graph.containsKey(node);
  }

  @Override
  public void connect(T node1, T node2, String name, int weight) {
    if (!graph.containsKey(node1) || !graph.containsKey(node2)) {
      throw new NoSuchElementException();
    }
    if (weight <0) {
      throw new IllegalArgumentException();
    }
    if (getEdgeBetween(node1, node2) != null) {
      throw  new IllegalStateException();
    }

    Edge<T> edge1 = new Edgee<>(node2, name, weight);
    Edge<T> edge2 = new Edgee<>(node1, name, weight);

    graph.get(node1).add(edge1);
    graph.get(node2).add(edge2);
  }

  @Override
  public void disconnect(T node1, T node2) {
    if(!graph.containsKey(node1) || !graph.containsKey(node2)) {
      throw new NoSuchElementException();
    }

    if(getEdgeBetween(node1, node2) == null) {
      throw new IllegalStateException();
    }
    graph.get(node1).removeIf(e -> e.getDestination().equals(node2));
    graph.get(node2).removeIf(e -> e.getDestination().equals(node1));
  }

  @Override
  public void setConnectionWeight(T node1, T node2, int weight) {
    if (!graph.containsKey(node1) || !graph.containsKey(node2)) {
      throw new NoSuchElementException();
    }
    if (weight < 0) {
      throw new IllegalArgumentException();
    }
    Edge<T> e1 = getEdgeBetween(node1, node2);
    Edge<T> e2 = getEdgeBetween(node2, node1);

    if (e1 == null || e2 == null) {
      throw new NoSuchElementException();
    }
    e1.setWeight(weight);
    e2.setWeight(weight);
  }

  @Override
  public Set<T> getNodes() {
    return new HashSet<>(graph.keySet());
  }

  @Override
  public Collection<Edge<T>> getEdgesFrom(T node) {
    if (!graph.containsKey(node)) {
      throw new NoSuchElementException();
    }
    return new ArrayList<>(graph.get(node));
  }

  @Override
  public Edge<T> getEdgeBetween(T node1, T node2) {
    if (!graph.containsKey(node1) || !graph.containsKey(node2)) {
      throw new NoSuchElementException();
    }

    for (Edge<T> edge : graph.get(node1)) {
      if(edge.getDestination().equals(node2)) {
        return edge;
      }
    }
    return null;
  }

  @Override
  public Iterator<T> iterator() {
    return graph.keySet().iterator();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (T node : graph.keySet()) {
      sb.append(node);
      sb.append("\n");

      for (Edge<T> edge : graph.get(node)) {
        sb.append("\t");
        sb.append(edge);
        sb.append("\n");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

}


