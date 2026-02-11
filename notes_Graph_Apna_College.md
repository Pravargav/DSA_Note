
**bfs**

```java
import java.util.*;

public class BFS {
  static class Edge {
    int src;
    int dest;
    int wt;

    public Edge(int s, int d, int w) {
      this.src = s;
      this.dest = d;
      this.wt = w;
    }
  }

  static void createGraph(List<List<Edge>> graph) {
    for (int i = 0; i < graph.size(); i++) {
      graph.set(i, new ArrayList<>());
    }

    graph.get(0).add(new Edge(0, 1, 1));
    graph.get(0).add(new Edge(0, 2, 1));
    graph.get(1).add(new Edge(1, 0, 1));
    graph.get(1).add(new Edge(1, 3, 1));
    graph.get(2).add(new Edge(2, 0, 1));
    graph.get(2).add(new Edge(2, 4, 1));
    graph.get(3).add(new Edge(3, 1, 1));
    graph.get(3).add(new Edge(3, 4, 1));
    graph.get(3).add(new Edge(3, 5, 1));
    graph.get(4).add(new Edge(4, 2, 1));
    graph.get(4).add(new Edge(4, 3, 1));
    graph.get(4).add(new Edge(4, 5, 1));
    graph.get(5).add(new Edge(5, 3, 1));
    graph.get(5).add(new Edge(5, 4, 1));
    graph.get(5).add(new Edge(5, 6, 1));
    graph.get(6).add(new Edge(6, 5, 1));
  }

  public static void bfs(List<List<Edge>> graph, int V) {
    boolean[] visited = new boolean[V];
    Queue<Integer> q = new LinkedList<>();
    q.add(0); // Source = 0

    while (!q.isEmpty()) {
      int curr = q.remove();
      if (!visited[curr]) {
        System.out.print(curr + " ");
        visited[curr] = true;
        for (int i = 0; i < graph.get(curr).size(); i++) {
          Edge e = graph.get(curr).get(i);
          q.add(e.dest);
        }
      }
    }
    System.out.println();
  }

  public static void main(String args[]) {
    /*
        1 --- 3
       / | \
      0  |  5 -- 6
       \ | /
        2 ---- 4
    */
    int V = 7;
    List<List<Edge>> graph = new ArrayList<>(V); // Initialize with capacity V
    for (int i = 0; i < V; i++) {
      graph.add(new ArrayList<>()); // Initialize each adjacency list
    }
    createGraph(graph);
    bfs(graph, V);
  }
}

```

**dfs**

```java
import java.util.*;

public class DFS {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void createGraph(List<List<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            graph.set(i, new ArrayList<>());
        }

        graph.get(0).add(new Edge(0, 1, 1));
        graph.get(0).add(new Edge(0, 2, 1));
        graph.get(1).add(new Edge(1, 0, 1));
        graph.get(1).add(new Edge(1, 3, 1));
        graph.get(2).add(new Edge(2, 0, 1));
        graph.get(2).add(new Edge(2, 4, 1));
        graph.get(3).add(new Edge(3, 1, 1));
        graph.get(3).add(new Edge(3, 4, 1));
        graph.get(3).add(new Edge(3, 5, 1));
        graph.get(4).add(new Edge(4, 2, 1));
        graph.get(4).add(new Edge(4, 3, 1));
        graph.get(4).add(new Edge(4, 5, 1));
        graph.get(5).add(new Edge(5, 3, 1));
        graph.get(5).add(new Edge(5, 4, 1));
        graph.get(5).add(new Edge(5, 6, 1));
        graph.get(6).add(new Edge(6, 5, 1));
    }

    public static void dfs(List<List<Edge>> graph, int curr, boolean[] visited) {
        if (visited[curr]) {
            return;
        }
        System.out.print(curr + " ");
        visited[curr] = true;
        for (int i = 0; i < graph.get(curr).size(); i++) {
            Edge e = graph.get(curr).get(i);
            dfs(graph, e.dest, visited);
        }
    }

    public static void main(String args[]) {
        /*
            1 --- 3
           / | \
          0  |  5 -- 6
           \ | /
            2 ---- 4
        */
        int V = 7;
        List<List<Edge>> graph = new ArrayList<>(V); // Initialize with capacity V
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>()); // Initialize each adjacency list
        }
        createGraph(graph);
        dfs(graph, 0, new boolean[V]);
    }
}
```
**print all paths**

```java
import java.util.*;

public class PrintAllPaths {
    static class Edge {
        int src;
        int dest;
        
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    static void createGraph(List<List<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            graph.set(i, new ArrayList<>());
        }

        graph.get(0).add(new Edge(0, 1));
        graph.get(0).add(new Edge(0, 2));
        graph.get(1).add(new Edge(1, 0));
        graph.get(1).add(new Edge(1, 3));
        graph.get(2).add(new Edge(2, 0));
        graph.get(2).add(new Edge(2, 4));
        graph.get(3).add(new Edge(3, 1));
        graph.get(3).add(new Edge(3, 4));
        graph.get(3).add(new Edge(3, 5));
        graph.get(4).add(new Edge(4, 2));
        graph.get(4).add(new Edge(4, 3));
        graph.get(4).add(new Edge(4, 5));
        graph.get(5).add(new Edge(5, 3));
        graph.get(5).add(new Edge(5, 4));
        graph.get(5).add(new Edge(5, 6));
        graph.get(6).add(new Edge(6, 5));
    }

    public static void printAllPaths(List<List<Edge>> graph, int src, int tar, String path, boolean[] vis) {
        if (src == tar) {
            System.out.println(path);
            return;
        }
        for (int i = 0; i < graph.get(src).size(); i++) {
            Edge e = graph.get(src).get(i);
            if (!vis[e.dest]) {
                vis[e.dest] = true;
                printAllPaths(graph, e.dest, tar, path + "->" + e.dest, vis);
                vis[e.dest] = false;
            }
        }
    }

    public static void main(String args[]) {
        int V = 7;
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);

        int src = 0;
        int tar = 5;
        boolean[] vis = new boolean[V];
        vis[src] = true;
        printAllPaths(graph, src, tar, "" + src, vis);
    }
}
```

**cycle undirected**

```java
import java.util.*;

public class CycleUndirected {
    static class Edge {
        int src;
        int dest;
        
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    static void createGraph(List<List<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            graph.set(i, new ArrayList<>());
        }

        graph.get(0).add(new Edge(0, 1));
        graph.get(0).add(new Edge(0, 2));
        graph.get(0).add(new Edge(0, 3));
        graph.get(1).add(new Edge(1, 0));
        graph.get(1).add(new Edge(1, 2));
        graph.get(2).add(new Edge(2, 0));
        graph.get(2).add(new Edge(2, 1));
        graph.get(3).add(new Edge(3, 0));
        graph.get(3).add(new Edge(3, 4));
        graph.get(4).add(new Edge(4, 3));
    }

    public static boolean isCyclicUtil(List<List<Edge>> graph, boolean[] vis, int curr, int par) {
        vis[curr] = true;
        for (int i = 0; i < graph.get(curr).size(); i++) {
            Edge e = graph.get(curr).get(i);
            // Case 1: Already visited and not parent
            if (vis[e.dest] && e.dest != par) {
                return true;
            }
            
            // Case 3: Not visited - recurse
            else if(!vis[e.dest]){
                boolean isCycle = isCyclicUtil(graph, vis, e.dest, curr);
                if (isCycle) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCyclic(List<List<Edge>> graph, boolean[] vis) {
        for (int i = 0; i < graph.size(); i++) {
            if (!vis[i]) {
                if (isCyclicUtil(graph, vis, i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        /*
            0 ------- 3
           / |        |
          /  |        |
         1   |        4
          \  |
           \ |
            2
        */
        int V = 5;
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        System.out.println(isCyclic(graph, new boolean[V]));
    }
}

```

**dijikstra's**

```java
import java.util.*;

public class Dijkstras {
    static class Edge {
        int src;
        int dest;
        int wt;
        
        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void createGraph(List<List<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            graph.set(i, new ArrayList<>());
        }
        
        graph.get(0).add(new Edge(0, 1, 2));
        graph.get(0).add(new Edge(0, 2, 4));
        graph.get(1).add(new Edge(1, 3, 7));
        graph.get(1).add(new Edge(1, 2, 1));
        graph.get(2).add(new Edge(2, 4, 3));
        graph.get(3).add(new Edge(3, 5, 1));
        graph.get(4).add(new Edge(4, 3, 2));
        graph.get(4).add(new Edge(4, 5, 5));
    }

    static class Pair implements Comparable<Pair> {
        int n;
        int path;
        
        public Pair(int n, int path) {
            this.n = n;
            this.path = path;
        }
        
        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
    }

    public static int[] dijkstra(List<List<Edge>> graph, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[graph.size()];
        boolean[] vis = new boolean[graph.size()];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        pq.add(new Pair(src, 0));
        
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            
            if (!vis[curr.n]) {
                vis[curr.n] = true;
                
                // Using normal for loop instead of enhanced for loop
                for (int i = 0; i < graph.get(curr.n).size(); i++) {
                    Edge e = graph.get(curr.n).get(i);
                    int u = e.src;
                    int v = e.dest;
                    if (!vis[v] && dist[u] + e.wt < dist[v]) {
                        dist[v] = dist[u] + e.wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String args[]) {
        int V = 6;
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        
        int src = 0;
        int[] dist = dijkstra(graph, src);
        
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
    }
}
```

**cycle directed**

```java
import java.util.*;

public class CycleDirected {
    static class Edge {
        int src;
        int dest;
        
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    // Graph1 - true (contains cycle)
    static void createGraph(List<List<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            graph.set(i, new ArrayList<>());
        }
        graph.get(0).add(new Edge(0, 2));
        graph.get(1).add(new Edge(1, 0));
        graph.get(2).add(new Edge(2, 3));
        graph.get(3).add(new Edge(3, 0));
    }

    public static boolean isCyclicUtil(List<List<Edge>> graph, int curr, 
                                     boolean[] vis, boolean[] stack) {
        vis[curr] = true;
        stack[curr] = true;
        
        // Using normal for loop
        for (int i = 0; i < graph.get(curr).size(); i++) {
            Edge e = graph.get(curr).get(i);
            if (stack[e.dest]) { // Cycle exists
                return true;
            } else if (!vis[e.dest] && isCyclicUtil(graph, e.dest, vis, stack)) {
                return true;
            }
        }
        
        stack[curr] = false;
        return false;
    }

    // O(V + E)
    public static boolean isCyclic(List<List<Edge>> graph) {
        boolean[] vis = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!vis[i]) {
                if (isCyclicUtil(graph, i, vis, new boolean[vis.length])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        int V = 4;
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        System.out.println(isCyclic(graph));
    }
}
```
**bellman ford**

```java
import java.util.*;

public class BellmanFord {
    static class Edge {
        int src;
        int dest;
        int wt;
        
        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void createGraph(List<List<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            graph.set(i, new ArrayList<>());
        }
        
        graph.get(0).add(new Edge(0, 1, 2));
        graph.get(0).add(new Edge(0, 2, 4));
        graph.get(1).add(new Edge(1, 2, -4));
        graph.get(2).add(new Edge(2, 3, 2));
        graph.get(3).add(new Edge(3, 4, 4));
        graph.get(4).add(new Edge(4, 1, -1));
    }

    public static void bellmanFord(List<List<Edge>> graph, int src) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        // Relax all edges V-1 times
        for (int i = 0; i < graph.size() - 1; i++) {
            for (int j = 0; j < graph.size(); j++) {
                for (int k = 0; k < graph.get(j).size(); k++) {
                    Edge e = graph.get(j).get(k);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }
        
        // Check for negative weight cycles
        for (int j = 0; j < graph.size(); j++) {
            for (int k = 0; k < graph.get(j).size(); k++) {
                Edge e = graph.get(j).get(k);
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;
                
                if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                    System.out.println("Negative weight cycle exists");
                    return;
                }
            }
        }
        
        // Print distances
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int V = 5;
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        
        int src = 0;
        bellmanFord(graph, src);
    }
}
```



**indegree**

```java
public class InDegreeExample {
    public static void main(String[] args) {
        int vertices = 5; // Number of vertices (0 to 4)

        // Example directed edges
        int[][] edges = {
            {0, 1},
            {0, 2},
            {1, 2},
            {2, 3},
            {3, 4}
        };

        // Adjacency list representation
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
        }

        // Array to store indegrees
        int[] indegree = new int[vertices];

        // Calculate indegrees
        for (int u = 0; u < vertices; u++) {
            for (int v : graph.get(u)) {
                indegree[v]++;
            }
        }

        // Print indegree of each vertex
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + " -> In-degree: " + indegree[i]);
        }
    }
}
```

**khan's algorithm for topological sort(using bfs and indegree)**

Note: works for disconnected graph also.

```java

public class KahnsAlgorithm {
    public static void main(String[] args) {
        int vertices = 6; // Number of vertices (0 to 5)

        // Directed edges
        int[][] edges = {
            {5, 2},
            {5, 0},
            {4, 0},
            {4, 1},
            {2, 3},
            {3, 1}
        };

        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[vertices];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            indegree[v]++; // count incoming edges
        }

        // Queue for vertices with indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Kahn's BFS topological sort
        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);

            // Reduce indegree of neighbors
            for (int neighbor : graph.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Check for cycle
        if (topoOrder.size() != vertices) {
            System.out.println("Cycle detected! Topological sort not possible.");
        } else {
            System.out.println("Topological Order: " + topoOrder);
        }
    }
}
```




**Building arraylist of arraylist from 2d array**
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

**count of Connected components**
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
**matrix/grid 4 directions left,right,top,bottom moves using dijikstras shortest path**

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
**check if path exists between src and dest**

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
**Dijikstras algorithm**

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
**weighted dijikstra using pair** 

Note: no difference between directed and undirected code just the input adjacency list is changed.

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
**khan's algorithm for topological sort**

Note: using bfs and indegree.

```java

public class KahnsAlgorithm {
    public static void main(String[] args) {
        int vertices = 6; // Number of vertices (0 to 5)

        // Directed edges
        int[][] edges = {
            {5, 2},
            {5, 0},
            {4, 0},
            {4, 1},
            {2, 3},
            {3, 1}
        };

        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[vertices];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            indegree[v]++; // count incoming edges
        }

        // Queue for vertices with indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Kahn's BFS topological sort
        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);

            // Reduce indegree of neighbors
            for (int neighbor : graph.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Check for cycle
        if (topoOrder.size() != vertices) {
            System.out.println("Cycle detected! Topological sort not possible.");
        } else {
            System.out.println("Topological Order: " + topoOrder);
        }
    }
}
```


