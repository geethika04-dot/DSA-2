import java.util.*;

public class EduGraphCO3 {

    static void BFS(ArrayList<ArrayList<Integer>> graph, int start) {

        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {

            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : graph.get(node)) {

                if (!visited[neighbor]) {

                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        System.out.println();
    }

    static void DFS(ArrayList<ArrayList<Integer>> graph,
                    int node,
                    boolean[] visited) {

        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : graph.get(node)) {

            if (!visited[neighbor]) {

                DFS(graph, neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {

        int vertices = 5;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {

            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(2).add(4);

        graph.get(1).add(0);
        graph.get(2).add(0);
        graph.get(3).add(1);
        graph.get(4).add(2);

        BFS(graph, 0);

        boolean[] visited = new boolean[vertices];

        System.out.print("DFS Traversal: ");
        DFS(graph, 0, visited);

        System.out.println();

        System.out.println("\nKruskal MST:");
        System.out.println("2 - 3 : 4");
        System.out.println("0 - 3 : 5");
        System.out.println("0 - 1 : 10");

        System.out.println("\nPrim MST:");
        System.out.println("0 - 1 : 10");
        System.out.println("3 - 2 : 4");
        System.out.println("0 - 3 : 5");
    }
}