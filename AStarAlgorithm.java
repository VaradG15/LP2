import java.util.*;

class Node {
    int x, y;
    int f, g, h;
    Node parent;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Calculate the heuristic value (H) using Manhattan distance
    void calculateH(Node end) {
        h = Math.abs(end.x - x) + Math.abs(end.y - y);
    }
}

public class AStar {
    private int[][] grid;
    private Node startNode, endNode;
    private PriorityQueue<Node> openList;
    private Set<Node> closedSet;
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 4-directional movement

    public AStar(int[][] grid, int startX, int startY, int endX, int endY) {
        this.grid = grid;
        startNode = new Node(startX, startY);
        endNode = new Node(endX, endY);
        openList = new PriorityQueue<>((a, b) -> a.f - b.f);
        closedSet = new HashSet<>();
    }

    public List<Node> findPath() {
        startNode.calculateH(endNode);
        startNode.g = 0;
        startNode.f = startNode.h;
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();

            if (currentNode.x == endNode.x && currentNode.y == endNode.y)
                return reconstructPath(currentNode);

            closedSet.add(currentNode);

            for (int[] dir : directions) {
                int newX = currentNode.x + dir[0];
                int newY = currentNode.y + dir[1];

                if (isValid(newX, newY)) {
                    Node neighbor = new Node(newX, newY);
                    if (closedSet.contains(neighbor))
                        continue;

                    int tentativeGScore = currentNode.g + 1; // Assuming uniform cost for movement

                    if (!openList.contains(neighbor) || tentativeGScore < neighbor.g) {
                        neighbor.parent = currentNode;
                        neighbor.g = tentativeGScore;
                        neighbor.calculateH(endNode);
                        neighbor.f = neighbor.g + neighbor.h;

                        if (!openList.contains(neighbor))
                            openList.add(neighbor);
                    }
                }
            }
        }

        return null; // No path found
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0;
    }

    private List<Node> reconstructPath(Node currentNode) {
        List<Node> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }
        Collections.reverse(path);
        return path;
    }

    // Example usage
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        int startX = 0, startY = 0;
        int endX = 2, endY = 4;

        AStar aStar = new AStar(grid, startX, startY, endX, endY);
        List<Node> path = aStar.findPath();

        if (path != null) {
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("No path found!");
        }
    }
}
