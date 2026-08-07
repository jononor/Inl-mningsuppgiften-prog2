package se.su.inlupp;

public class MapDFS {

    private PathFinder<CityPlace> dfs = new DFSPathFinder();

    public Path<CityPlace> findPath(Graph<CityPlace> graph, CityPlace cityFrom, CityPlace CityTo) {
        return dfs.findPath(graph, cityFrom, CityTo);
    }

}
