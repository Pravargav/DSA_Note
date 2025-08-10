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
-> Dijikstras algorithm

```java


public class DijkstraAdjList {

    public static int[] dijkstra(List<List<int[]>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Min-heap: [distance, node]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int d = current[0];
            int u = current[1];

            if (d > dist[u]) continue; // Skip outdated entries

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int weight = edge[1];
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Undirected weighted edges
        graph.get(0).add(new int[]{1, 2});
        graph.get(1).add(new int[]{0, 2});

        graph.get(0).add(new int[]{4, 1});
        graph.get(4).add(new int[]{0, 1});

        graph.get(1).add(new int[]{2, 3});
        graph.get(2).add(new int[]{1, 3});


        int src = 0;
        int[] dist = dijkstra(graph, src);

        System.out.println("Shortest distances from node " + src + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("Node " + i + " -> " + dist[i]);
        }
    }
}
```
-> weighted dijikstra using pair (no difference between directed and undirected code just the input adjacency list is changed)

```java


public class DijkstraDirected {
    static class Pair {
        int node, weight;
        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static List<List<Pair>> graph = new ArrayList<>();

    static void dijkstra(int start) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int node = cur.node;
            int curDist = cur.weight;

            if (curDist > dist[node]) continue; // Skip outdated entries

            for (Pair edge : graph.get(node)) {
                int newDist = curDist + edge.weight;
                if (newDist < dist[edge.node]) {
                    dist[edge.node] = newDist;
                    pq.add(new Pair(edge.node, newDist));
                }
            }
        }

        // Print shortest distances
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.println("Node " + i + " is unreachable");
            else
                System.out.println("Shortest distance to node " + i + " = " + dist[i]);
        }
    }

    static void addEdge(int u, int v, int w) {
        graph.get(u).add(new Pair(v, w)); // Directed edge
    }

    public static void main(String[] args) {
        int n = 5; // Number of nodes
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Directed weighted edges
        addEdge(0, 1, 2);
        addEdge(0, 2, 4);
        addEdge(1, 2, 1);
        addEdge(1, 3, 7);
        addEdge(2, 4, 3);
        addEdge(3, 4, 1);

        dijkstra(0); // Start from node 0
    }
}
```

