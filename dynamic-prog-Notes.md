**floydd warshall**

```java
class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class FloydWarshall {
    static final int INF = (int) 1e9;

    public static int[][] floydWarshall(List<List<Edge>> graph, int V) {
        int[][] dist = new int[V][V];

        // Step 1: Initialize the distance matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        // Step 2: Fill distances from graph edges
        for (int u = 0; u < V; u++) {
            for (Edge edge : graph.get(u)) {
                dist[u][edge.to] = edge.weight;
            }
        }

        // Step 3: Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        // Example edges
        graph.get(0).add(new Edge(1, 5));
        graph.get(0).add(new Edge(3, 10));
        graph.get(1).add(new Edge(2, 3));
        graph.get(2).add(new Edge(3, 1));

        int[][] result = floydWarshall(graph, V);

        // Print the result
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (result[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
```
