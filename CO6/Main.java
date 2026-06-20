import java.util.*;

public class Main {

    // ---------------- GREEDY: Activity Selection ----------------
    static void activitySelection(int[] start, int[] finish) {
        System.out.println("\nActivity Selection (Course Scheduling):");

        int n = start.length;
        int i = 0;

        System.out.print("Selected Activities: " + i);

        for (int j = 1; j < n; j++) {
            if (start[j] >= finish[i]) {
                System.out.print(" " + j);
                i = j;
            }
        }
        System.out.println();
    }

    // ---------------- GREEDY: Shortest Path (Dijkstra) ----------------
    static void dijkstra(int[][] graph, int src) {

        int V = graph.length;
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = -1;
            int min = Integer.MAX_VALUE;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && dist[v] < min) {
                    min = dist[v];
                    u = v;
                }
            }

            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("\nShortest Learning Paths:");

        for (int i = 0; i < V; i++) {
            System.out.println("Course " + i + " -> " + dist[i]);
        }
    }

    // ---------------- DP: 0/1 Knapsack ----------------
    static int knapsack(int W, int[] wt, int[] val, int n) {

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {

            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0)
                    dp[i][w] = 0;

                else if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(
                            val[i - 1] + dp[i - 1][w - wt[i - 1]],
                            dp[i - 1][w]);

                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][W];
    }

    // ---------------- DP: Longest Common Subsequence ----------------
    static int lcs(String X, String Y) {

        int m = X.length();
        int n = Y.length();

        int[][] L = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0)
                    L[i][j] = 0;

                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    L[i][j] = L[i - 1][j - 1] + 1;

                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }

        return L[m][n];
    }

    // ---------------- DP: Matrix Chain Multiplication ----------------
    static int matrixChainOrder(int[] p) {

        int n = p.length;
        int[][] m = new int[n][n];

        for (int len = 2; len < n; len++) {

            for (int i = 1; i < n - len + 1; i++) {

                int j = i + len - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {

                    int q = m[i][k]
                            + m[k + 1][j]
                            + p[i - 1] * p[k] * p[j];

                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }

        return m[1][n - 1];
    }

    public static void main(String[] args) {

        System.out.println("EduGraph – Adaptive Learning & Course Recommendation Platform");

        // Activity Scheduling
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] finish = {2, 4, 6, 7, 9, 9};
        activitySelection(start, finish);

        // Dijkstra
        int[][] graph = {
                {0, 4, 0, 0, 0, 0},
                {4, 0, 8, 0, 0, 0},
                {0, 8, 0, 7, 2, 4},
                {0, 0, 7, 0, 9, 14},
                {0, 0, 2, 9, 0, 10},
                {0, 0, 4, 14, 10, 0}
        };

        dijkstra(graph, 0);

        // Knapsack
        int[] studyHours = {2, 3, 4, 5};
        int[] benefits = {3, 4, 5, 8};
        int maxHours = 5;

        System.out.println("\nMaximum Learning Benefit (Knapsack): "
                + knapsack(maxHours, studyHours, benefits,
                studyHours.length));

        // LCS
        String studentInterest = "JAVA";
        String courseTopics = "JAVASCRIPT";

        System.out.println("\nLCS Match Score: "
                + lcs(studentInterest, courseTopics));

        // Matrix Chain Multiplication
        int[] dimensions = {10, 20, 30, 40, 30};

        System.out.println("\nMinimum Matrix Operations: "
                + matrixChainOrder(dimensions));
    }
}