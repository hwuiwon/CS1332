/**
 * Class representing a directed edge from u to v.
 *
 * DO NOT EDIT THIS CLASS!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */

public class Edge<T> implements Comparable<Edge<? super T>> {

    private Vertex<T> u;
    private Vertex<T> v;
    private int weight;

    /**
     * Creates a directed edge from vertex u to vertex v. Any single edge is
     * always directed, so if you're trying to create an undirected edge, you
     * must create the edges (u, v, weight) and (v, u, weight) when creating the
     * graph.
     *
     * @param u the start vertex of the edge
     * @param v the end vertex of the edge
     * @param weight the weight value of the edge
     * @throws IllegalArgumentException if any of the arguments are null
     */
    public Edge(Vertex<T> u, Vertex<T> v, int weight) {
        if (u == null || v == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        return u.hashCode() ^ v.hashCode() ^ weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Edge<?>) {
            Edge<?> e = (Edge<?>) o;
            return weight == e.weight && u.equals(e.u) && v.equals(e.v);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Edge<? super T> e) {
        return weight - e.getWeight();
    }

    /**
     * Gets the weight of this edge.
     *
     * @return the weight of this edge
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets u, the starting vertex of this edge.
     *
     * @return the u vertex of this edge
     */
    public Vertex<T> getU() {
        return u;
    }

    /**
     * Gets v, the ending vertex of this edge.
     *
     * @return the v vertex of this edge
     */
    public Vertex<T> getV() {
        return v;
    }

    @Override
    public String toString() {
        return "Edge from " + u + " to " + v + " with weight " + weight;
    }

}