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
-> matrix/grid 4 directions left,right,top,bottom moves using dijikstras shortest path

```java
import java.util.*;

class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0, 0}); // time, row, col

        int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0], r = curr[1], c = curr[2];

            if (r == m - 1 && c == n - 1) return time;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                if (nr >= 0 && nc >= 0 && nr < m && nc < n) {
                    int newTime = Math.max(time, moveTime[nr][nc]) + 1;
                    if (newTime < dist[nr][nc]) {
                        dist[nr][nc] = newTime;
                        pq.offer(new int[]{newTime, nr, nc});
                    }
                }
            }
        }
        return -1;
    }
}
```
-> check if path exists between src and dest 

```java
import java.util.*;

public class PathExistenceAdjList {
    public static boolean validPath(List<List<Integer>> graph, int source, int destination) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) return true;

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Example adjacency list for n = 6
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 6; i++) graph.add(new ArrayList<>());

        // Undirected edges
        graph.get(0).add(1);
        graph.get(1).add(0);

        graph.get(1).add(2);
        graph.get(2).add(1);


        System.out.println(validPath(graph, 0, 1)); 
        System.out.println(validPath(graph, 0, 2)); 
    }
}

```
