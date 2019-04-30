/**
 * Class to store a vertex in a graph and an integer associated with it
 * representing the distance to this vertex from some other vertex
 *
 * DO NOT EDIT THIS CLASS!!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public final class VertexDistance<T>
        implements Comparable<VertexDistance<? super T>> {

    private final Vertex<T> vertex;
    private final int distance;

    /**
     * Creates a pairing of vertex and distance to that vertex.
     *
     * @param vertex the Vertex to be stored.
     * @param distance the integer representing the distance to this Vertex
     *        from the previous Vertex.
     */
    public VertexDistance(Vertex<T> vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    /**
     * Gets the Vertex of this pairing.
     *
     * @return the Vertex of this pairing.
     */
    public Vertex<T> getVertex() {
        return vertex;
    }

    /**
     * Gets the distance to the vertex.
     *
     * @return the distance distance to the vertex.
     */
    public int getDistance() {
        return distance;
    }

    @Override
    public int hashCode() {
        return vertex.hashCode() ^ distance;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof VertexDistance<?>) {
            VertexDistance<?> e = (VertexDistance<?>) o;
            return distance == e.distance && vertex.equals(e.vertex);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(VertexDistance<? super T> pair) {
        return this.getDistance() - pair.getDistance();
    }

    @Override
    public String toString() {
        return "Pair with vertex " + vertex + " and distance " + distance;
    }
}