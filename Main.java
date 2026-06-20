  import java.util.*;

public class Main {

    static final int INF = 999999;

    // Dijkstra Algorithm
    static void dijkstra(int[][] graph, int src) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = -1;
            int min = INF;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }

            if (u == -1)
                break;

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] &&
                    graph[u][v] != INF &&
                    dist[u] != INF &&
                    dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("\nDijkstra - Minimum Learning Path:");
        for (int i = 0; i < n; i++) {
            System.out.println("Course " + i + " : " + dist[i]);
        }
    }

    // Bellman Ford Algorithm
    static void bellmanFord(int[][] graph, int src) {
        int n = graph.length;
        int[] dist = new int[n];

        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int k = 1; k < n; k++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] != INF &&
                        dist[u] != INF &&
                        dist[u] + graph[u][v] < dist[v]) {

                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        boolean negativeCycle = false;

        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != INF &&
                    dist[u] != INF &&
                    dist[u] + graph[u][v] < dist[v]) {

                    negativeCycle = true;
                }
            }
        }

        System.out.println("\nBellman-Ford - Course Recommendations:");

        if (negativeCycle) {
            System.out.println("Negative Cycle Detected");
        } else {
            for (int i = 0; i < n; i++) {
                System.out.println("Course " + i + " : " + dist[i]);
            }
        }
    }

    // Floyd Warshall Algorithm
    static void floydWarshall(int[][] graph) {
        int n = graph.length;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++)
            dist[i] = graph[i].clone();

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF &&
                        dist[k][j] != INF &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {

                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        System.out.println("\nFloyd-Warshall - All Course Paths:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Course Graph
        // 0 = Programming Basics
        // 1 = Data Structures
        // 2 = Algorithms
        // 3 = Machine Learning

        int[][] courseGraph = {
            {0, 3, INF, INF},
            {INF, 0, 2, 5},
            {INF, INF, 0, 2},
            {INF, INF, INF, 0}
        };

        int sourceCourse = 0;

        System.out.println("EduGraph - Adaptive Learning & Course Recommendation Platform");

        dijkstra(courseGraph, sourceCourse);
        bellmanFord(courseGraph, sourceCourse);
        floydWarshall(courseGraph);
    }
}                                                                                                                  