public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> network = new WeightedGraph<>(true);
        populateGraph(network);

        Vertex<String> source = network.getVertex("Almaty");
        Vertex<String> destination = network.getVertex("Kyzylorda");

        if (source == null || destination == null) {
            System.out.println("Error: One of the specified cities was not found.");
            return;
        }


        System.out.println("=== Dijkstra Shortest Path ===");
        Search<String> dijkstra = new DijkstraSearch<>(network, source);
        printRoute(dijkstra, destination);

        System.out.println("\n======================\n");


        System.out.println("=== Depth-First Search (DFS) ===");
        Search<String> dfs = new DepthFirstSearch<>(network, source);
        printRoute(dfs, destination);

        System.out.println("\n======================\n");


        System.out.println("=== Breadth-First Search (BFS) ===");
        Search<String> bfs = new BreadthFirstSearch<>(network, source);
        printRoute(bfs, destination);
    }

    public static void populateGraph(WeightedGraph<String> graph) {
        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
    }

    public static void printRoute(Search<String> searchEngine, Vertex<String> target) {
        Iterable<Vertex<String>> route = searchEngine.pathTo(target);

        if (route == null) {
            System.out.println("No route available.");
            return;
        }

        StringBuilder pathVisualizer = new StringBuilder();
        for (Vertex<String> vertex : route) {
            if (pathVisualizer.length() > 0) {
                pathVisualizer.append(" => ");
            }
            pathVisualizer.append(vertex.getData());
        }
        System.out.println(pathVisualizer.toString());
    }
}

