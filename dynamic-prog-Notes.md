https://youtube.com/playlist?list=PLM68oyaqFM7QsfZJ8W2YHgiA2EDp3FryV&si=LTEcZVE1UBx4uWq3
--------------------------------------------------------------
**1)floydd warshall**

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

**2)Smallest Sum Contiguos Array(practice)**

**3)Stictly Increasing Array(practice)**

----------------------------------------------------------

**1)Count ways To Reach Nth Stair(practice)**
**2)Stickler Thief(practice)**

----------------------------------------------------------

**1)Maximum sum subarray(practice)**
**2)Maximum product subarray(practice)**
**3)Trapping water(practice)**

----------------------------------------------------------

**Matrix Chain Multiplication concept**

----------------------------------------------------------

**1)Longest Arthimatic Progression(practice)**
**2)Largest Square found in matrix(practice)**

----------------------------------------------------------

**1)Diameter of Tree(Trees)**
**2)Maximum path sum between two leaf nodes(Trees)**

----------------------------------------------------------

**1)Number of Paths(Grid)**
**2)Maximum path Sum(Grid)**

----------------------------------------------------------

**1)Only one transation(Stocks)**
**2)Infinite Transactions(Stocks)**
**3)At most two transactions(Stocks)**
**4)At most k transactions(Stocks)**

----------------------------------------------------------

**1)Longest Common Subsequence(String/Sequence)**
**2)Longest Common Substring(String/Sequence)**
**3)Longest Increasing Subsequence(String/Sequence)**

----------------------------------------------------------

**1)0-1 knapsack bounded(Knapsack)**
**2)is Subset Sum(Knapsack)**

----------------------------------------------------------
