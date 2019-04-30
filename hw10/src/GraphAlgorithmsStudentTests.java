import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Basic student tests to check GraphAlgorithms. These tests are in
 * no way comprehensive nor do they guarantee any kind of grade.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class GraphAlgorithmsStudentTests {

    private Graph<Integer> directedGraph;
    private Graph<Character> undirectedGraph;
    private static final int TIMEOUT = 200;

    @Before
    public void init() {
        directedGraph = createDirectedGraph();
        undirectedGraph = createUndirectedGraph();
    }

    /**
     * Creates a directed graph.
     * The graph is depicted in the pdf.
     *
     * @return the completed graph
     */
    private Graph<Integer> createDirectedGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        for (int i = 1; i <= 7; i++) {
            vertices.add(new Vertex<>(i));
        }

        Set<Edge<Integer>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 0));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(3), 0));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(4), 0));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(5), 0));
        edges.add(new Edge<>(new Vertex<>(4), new Vertex<>(6), 0));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(4), 0));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(7), 0));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(6), 0));

        return new Graph<>(vertices, edges);
    }

    /**
     * Creates an undirected graph.
     * The graph is depicted in the pdf.
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 70; i++) {
            vertices.add(new Vertex<>((char) i));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 7));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 8));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 8));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 6));

        return new Graph<>(vertices, edges);
    }

    @Test(timeout = TIMEOUT)
    public void testBFS() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.bfs(
                new Vertex<>(1), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        bfsExpected.add(new Vertex<>(1));
        bfsExpected.add(new Vertex<>(2));
        bfsExpected.add(new Vertex<>(3));
        bfsExpected.add(new Vertex<>(4));
        bfsExpected.add(new Vertex<>(5));
        bfsExpected.add(new Vertex<>(6));
        bfsExpected.add(new Vertex<>(7));

        assertEquals(bfsExpected, bfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>(5), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>(5));
        dfsExpected.add(new Vertex<>(4));
        dfsExpected.add(new Vertex<>(6));
        dfsExpected.add(new Vertex<>(7));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('D'), undirectedGraph);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 4);
        dijkExpected.put(new Vertex<>('B'), 4);
        dijkExpected.put(new Vertex<>('C'), 2);
        dijkExpected.put(new Vertex<>('D'), 0);
        dijkExpected.put(new Vertex<>('E'), 1);
        dijkExpected.put(new Vertex<>('F'), 7);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testPrims() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
            new Vertex<>('A'), undirectedGraph);
        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 6));

        assertEquals(edges, mstActual);
    }
}