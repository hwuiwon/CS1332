import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Hwuiwon Kim
 * @userid hkim944
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        Queue<Vertex<T>> queue = new LinkedList<>();
        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> result = new ArrayList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> tmp = queue.remove();
            result.add(tmp);
            for (VertexDistance<T> vd : graph.getAdjList().get(tmp)) {
                if (!visited.contains(vd.getVertex())) {
                    queue.add(vd.getVertex());
                    visited.add(vd.getVertex());
                }
            }
        }
        return result;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> result = new ArrayList<>();

        dfs(start, graph, visited, result);
        return result;
    }

    /**
     * Recursive helper method for dfs
     *
     * @param <T> the generic typing of the data
     * @param vertex the vertex to begin the dfs on
     * @param graph the graph to search through
     * @param vs the set of visited vertex
     * @param result the list that is returned
     */
    private static <T> void dfs(Vertex<T> vertex, Graph<T> graph,
                                Set<Vertex<T>> vs, List<Vertex<T>> result) {
        result.add(vertex);
        vs.add(vertex);

        for (VertexDistance<T> vd : graph.getAdjList().get(vertex)) {
            if (!vs.contains(vd.getVertex())) {
                dfs(vd.getVertex(), graph, vs, result);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from {@code start}, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check that not all vertices have been visited.
     * 2) Check that the PQ is not empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from {@code start} to every
     *          other node in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        Queue<VertexDistance<T>> queue = new PriorityQueue<>();
        Map<Vertex<T>, Integer> result = new HashMap<>();

        for (Vertex<T> v : graph.getAdjList().keySet()) {
            if (v.equals(start)) {
                result.put(v, 0);
            } else {
                result.put(v, Integer.MAX_VALUE);
            }
        }

        queue.add(new VertexDistance<>(start, 0));
        while (!queue.isEmpty()) {
            VertexDistance<T> tmp = queue.remove();
            for (VertexDistance<T> vd
                    : graph.getAdjList().get(tmp.getVertex())) {
                int distance = tmp.getDistance() + vd.getDistance();
                if (result.get(vd.getVertex()) > distance) {
                    result.put(vd.getVertex(), distance);
                    queue.add(new VertexDistance<>(vd.getVertex(), distance));
                }
            }
        }
        return result;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        PriorityQueue<Edge<T>> queue = new PriorityQueue<>(graph.getEdges());
        Set<Vertex<T>> visited = new HashSet<>();
        Set<Edge<T>> result = new HashSet<>();

        visited.add(start);
        while (!queue.isEmpty()) {
            Edge<T> tmp = queue.remove();
            if (!visited.contains(tmp.getV()) || !visited.contains(tmp.getU())) {
                result.add(tmp);
                result.add(new Edge<>(tmp.getV(), tmp.getU(), tmp.getWeight()));
                visited.add(tmp.getV());
                for (Edge<T> edge : graph.getEdges()) {
                    if (edge.getV().equals(tmp.getU())) {
                        result.add(edge);
                    } else {
                        queue.add(edge);
                    }
                }
            }
        }

        if (result.size() < graph.getVertices().size() - 1) {
            return null;
        }
        return result;
    }
}