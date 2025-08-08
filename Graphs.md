-> Building arraylist of arraylist from 2d array
```java
public class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Step 1: Build adjacency list
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u); // undirected graph
        }

        // Step 2: BFS
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) {
                return true;
            }
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return false;
    }
}
```

-> count of Connected components
```java
import java.util.*;

public class ConnectedComponentsBFS {
    public static void main(String[] args) {
        // Sample adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(Arrays.asList(1));        // 0
        adjList.add(Arrays.asList(0, 2));     // 1
        adjList.add(Arrays.asList(1));        // 2
        adjList.add(Arrays.asList(4));        // 3
        adjList.add(Arrays.asList(3));        // 4
        adjList.add(new ArrayList<>());       // 5 (isolated node)

        int connectedComponents = countConnectedComponents(adjList);
        System.out.println("Number of connected components: " + connectedComponents);
    }

    public static int countConnectedComponents(List<List<Integer>> adjList) {
        int n = adjList.size();
        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, adjList, visited);
                components++;
            }
        }

        return components;
    }

    private static void bfs(int start, List<List<Integer>> adjList, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }
}

```
