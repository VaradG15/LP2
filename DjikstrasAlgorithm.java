import java.util.Arrays;

public class DjikstrasAlgorithm {

    static final int INF = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int source) {
        int numVertices = graph.length;
        int[] distance = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        Arrays.fill(distance, INF);
        distance[source] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int minVertex = findMinVertex(distance, visited);
            visited[minVertex] = true;

            for (int j = 0; j < numVertices; j++) {
                if (!visited[j] && graph[minVertex][j] != INF && distance[minVertex] != INF
                        && distance[minVertex] + graph[minVertex][j] < distance[j]) {
                    distance[j] = distance[minVertex] + graph[minVertex][j];
                }
            }
        }

        // Print the shortest distances from the source to all other vertices
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Shortest distance from source to vertex " + i + ": " + distance[i]);
        }
    }

    public static int findMinVertex(int[] distance, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    public static void main(String[] args) {
        // Example graph represented as an adjacency matrix
        int[][] graph = {
                {0, 4, 2, INF, INF, INF},
                {INF, 0, INF, 5, INF, INF},
                {INF, 1, 0, 8, INF, INF},
                {INF, INF, INF, 0, 3, INF},
                {INF, INF, INF, INF, 0, 7},
                {INF, INF, INF, INF, INF, 0}
        };

        int source = 0; // Source vertex for the shortest path
        dijkstra(graph, source);
    }
}
