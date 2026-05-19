public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> weightedGraph = new WeightedGraph<>(true);
        fillWithWeights(weightedGraph);

        Vertex<String> start = weightedGraph.getVertex("Almaty");
        Vertex<String> end = weightedGraph.getVertex("Kyzylorda");

        if (start != null && end != null) {
            System.out.println("Dijkstra:");
            Search<String> djk = new DijkstraSearch<>(weightedGraph, start);
            outputPath(djk, end);

            System.out.println("--------------------");

            System.out.println("DFS:");
            Search<String> dfs = new DepthFirstSearch<>(weightedGraph, start);
            outputPath(dfs, end);

            System.out.println("--------------------");

            System.out.println("BFS:");
            Search<String> bfs = new BreadthFirstSearch<>(weightedGraph, start);
            outputPath(bfs, end);
        } else {
            System.out.println("Start or End vertex not found in graph!");
        }
    }

    public static void fillWithWeights(WeightedGraph<String> graph) {
        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
    }

    public static void outputPath(Search<String> search, Vertex<String> key) {
        Iterable<Vertex<String>> path = search.pathTo(key);
        if (path == null) {
            System.out.println("Path not found");
            return;
        }
        for (Vertex<String> v : path) {
            System.out.print(v.getData() + (v.getData().equals(key.getData()) ? "" : " -> "));
        }
        System.out.println();
    }
}

