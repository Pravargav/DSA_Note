```java
List<List<Edge>> graph = new ArrayList<>(V);  // Better approach
for (int i = 0; i < V; i++) {
    graph.add(new ArrayList<>());  // Initialize each adjacency list
}
```
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

**prims algorithm**

```java
import java.util.*;

public class PrimsAlgorithm {
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
        
        graph.get(0).add(new Edge(0, 1, 10));
        graph.get(0).add(new Edge(0, 2, 15));
        graph.get(0).add(new Edge(0, 3, 30));
        graph.get(1).add(new Edge(1, 0, 10));
        graph.get(1).add(new Edge(1, 3, 40));
        graph.get(2).add(new Edge(2, 0, 15));
        graph.get(2).add(new Edge(2, 3, 50));
        graph.get(3).add(new Edge(3, 0, 30));
        graph.get(3).add(new Edge(3, 1, 40));
        graph.get(3).add(new Edge(3, 2, 50));
    }

    static class Pair implements Comparable<Pair> {
        int v;
        int wt;
        
        public Pair(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }
        
        @Override
        public int compareTo(Pair p2) {
            return this.wt - p2.wt;
        }
    }

    // O(E log E) using Priority Queue
    public static void primAlgo(List<List<Edge>> graph) {
        boolean[] vis = new boolean[graph.size()];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0)); // Start with vertex 0
        int cost = 0;
        
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            
            if (!vis[curr.v]) {
                vis[curr.v] = true;
                cost += curr.wt;
                
                for (int i = 0; i < graph.get(curr.v).size(); i++) {
                    Edge e = graph.get(curr.v).get(i);
                    if (!vis[e.dest]) {
                        pq.add(new Pair(e.dest, e.wt));
                    }
                }
            }
        }
        
        System.out.println("Minimum Spanning Tree Cost: " + cost);
    }

    public static void main(String args[]) {
        int V = 4;
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        primAlgo(graph);
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
##### Example notes for reference

````markdown
````
