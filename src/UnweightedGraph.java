import java.util.*;

public class UnweightedGraph<V> {
    private final boolean undirected;


    private final Map<V, Vertex<V>> map = new HashMap<>();

    public UnweightedGraph() {
        this(true);
    }

    public UnweightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V v) {
        if (hasVertex(v))
            return;

        map.put(v, new Vertex<>(v));
    }

    public void addEdge(V source, V dest) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest) || source.equals(dest))
            return;

        Vertex<V> sourceVertex = map.get(source);
        Vertex<V> destVertex = map.get(dest);


        sourceVertex.addAdjacentVertex(destVertex, 1.0);

        if (undirected) {
            destVertex.addAdjacentVertex(sourceVertex, 1.0);
        }
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<V> vertex : map.values()) {
            count += vertex.getAdjacentVertices().size();
        }

        if (undirected)
            count /= 2;

        return count;
    }

    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source) || !hasVertex(dest)) return false;

        Vertex<V> sourceVertex = map.get(source);
        Vertex<V> destVertex = map.get(dest);

        return sourceVertex.getAdjacentVertices().containsKey(destVertex);
    }


    public List<V> adjacencyList(V v) {
        if (!hasVertex(v)) return null;

        List<V> neighbors = new ArrayList<>();
        Vertex<V> vertex = map.get(v);

        for (Vertex<V> neighbor : vertex.getAdjacentVertices().keySet()) {
            neighbors.add(neighbor.getData());
        }

        return neighbors;
    }
}

