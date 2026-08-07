package se.su.inlupp;

public class MapBFS {

    private PathFinder<CityPlace> bfs = new BFSPathFinder();

    public Path<CityPlace> findPath(Graph<CityPlace> graph, CityPlace cityFrom, CityPlace CityTo) {
        return bfs.findPath(graph, cityFrom, CityTo);
    }

}
