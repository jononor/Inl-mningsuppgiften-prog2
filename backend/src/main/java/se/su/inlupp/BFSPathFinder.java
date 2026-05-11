package se.su.inlupp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BFSPathFinder<T> implements PathFinder<T> {
  private static class NodeWithHistory<T> {
    public NodeWithHistory(T node, NodeWithHistory<T> prev, Edge<T> edge) {
      this.node = node;
      this.prev = prev;
      this.edge = edge;
    }

    public T node;
    public NodeWithHistory<T> prev;
    public Edge<T> edge;
  };

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
    List<T> visitedNodes = new ArrayList<T>();
    List<NodeWithHistory<T>> nodesToSearch = new ArrayList<>();
    nodesToSearch.addLast(new NodeWithHistory<T>(from, null, null));

    while (!nodesToSearch.isEmpty()) {
      NodeWithHistory<T> node = nodesToSearch.getFirst();
      nodesToSearch.removeFirst();
      visitedNodes.add(node.node);

      // If the node to search is the same as the target node
      // we are done and can construct the path between the
      // two nodes. This is a bit complicated, so we do this
      // in the separate method makePath (see below).
      if (node.node == to) {
        return makePath(node, graph);
      }

      Collection<Edge<T>> edges = graph.getEdgesFrom(node.node);
      for (Edge<T> edge : edges) {
        if (!visitedNodes.contains(edge.getDestination())) {
          nodesToSearch.addLast(new NodeWithHistory<T>(
              edge.getDestination(),
              node,
              edge));
        }
      }
    }

    // If we reach this point, we don't have any more nodes
    // to search, but still have not found the target node.
    // In this case, we return null to indicate that there
    // is no path between the two nodes in this graph.
    return null;
  }

  private Path<T> makePath(NodeWithHistory<T> last, Graph<T> graph) {
    List<T> nodes = new ArrayList<T>();
    List<Edge<T>> edges = new ArrayList<Edge<T>>();

    // We have the last node in the path, so follow the links
    // towards the first node in the path. We're done when we
    // reach a node that does not have a previous node.
    while (last != null) {
      nodes.addFirst(last.node);
      edges.addFirst(last.edge);
      last = last.prev;
    }

    // Remove the first edge, because it is always null. This
    // is because the starting node did not have an incoming
    // edge; it's just where we started the search.
    edges.removeFirst();

    return new Pathh<T>(
        nodes.getFirst(),
        nodes.getLast(),
        nodes,
        edges,
        graph);
  }
}
