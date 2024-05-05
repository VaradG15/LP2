import java.util.*;

public class GraphTraversal {

    // Depth First Search traversal
    static void DFS(int[][] graph, int start) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited[current]) {
                System.out.print(current + " ");
                visited[current] = true;
                for (int i = n - 1; i >= 0; i--) {
                    if (graph[current][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
    }

    // Breadth First Search traversal
    static void BFS(int[][] graph, int start) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (int i = 0; i < n; i++) {
                if (graph[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int n = scanner.nextInt();
        int[][] graph = new int[n][n];

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter the starting node for traversal: ");
        int start = scanner.nextInt();

        System.out.println("Depth First Search traversal:");
        DFS(graph, start);

        System.out.println("\nBreadth First Search traversal:");
        BFS(graph, start);
    }
}
