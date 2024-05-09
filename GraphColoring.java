import java.util.*;

public class GraphColoring {
    public static void greedyColoring(int[][] graph, int numOfColors) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);

        colors[0] = 0;

        boolean[] availableColors = new boolean[numOfColors];
        Arrays.fill(availableColors, true);

        // Assign colors to remaining V-1 vertices
        for (int v = 1; v < graph.length; v++) {
            for (int i = 0; i < graph[v].length; i++) {
                if (graph[v][i] == 1 && colors[i] != -1) {
                    availableColors[colors[i]] = false;
                }
            }

            int chosenColor;
            for (chosenColor = 0; chosenColor < numOfColors; chosenColor++) {
                if (availableColors[chosenColor]) {
                    break;
                }
            }

            colors[v] = chosenColor;

            Arrays.fill(availableColors, true);
        }

        System.out.println("Vertex   Color");
        for (int v = 0; v < graph.length; v++) {
            System.out.println("  " + v + "       " + colors[v]);
        }
    }

    public static void main(String[] args) throws Exception{
        int[][] graph = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}
        };
        int numOfColors = 3;

        System.out.println("Graph Coloring using Greedy Algorithm:");
        greedyColoring(graph, numOfColors);
    }
}
