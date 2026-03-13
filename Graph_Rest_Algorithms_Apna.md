
**BFS**

-> https://youtu.be/scQITTLgFJo?si=X26mKGbWEf4YyyVS

-> https://youtu.be/m1_0UlIFnLs?si=muCMU3A832TAuvxA

```java
import java.util.*;

public class BFS {

    static class Edge {
        int src;
        int dest;
        int wt;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }

    static void createGraph(List<List<Edge>> graph) {

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

    static void bfs(List<List<Edge>> graph) {

        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        visited[0] = true;

        while (!q.isEmpty()) {

            int curr = q.remove();
            System.out.print(curr + " ");

            for (Edge e : graph.get(curr)) {
                if (!visited[e.dest]) {
                    visited[e.dest] = true;
                    q.add(e.dest);
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int V = 7;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        bfs(graph);
    }
}

```

**Print all paths(BFS)**

```java
import java.util.*;

public class BFSPaths {

    static class Edge {
        int src;
        int dest;

        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static void createGraph(List<List<Edge>> graph) {

        graph.get(0).add(new Edge(0, 1));
        graph.get(0).add(new Edge(0, 2));

        graph.get(1).add(new Edge(1, 3));
        graph.get(1).add(new Edge(1, 4));

        graph.get(2).add(new Edge(2, 4));

        graph.get(3).add(new Edge(3, 5));

        graph.get(4).add(new Edge(4, 5));
        graph.get(4).add(new Edge(4, 6));

        graph.get(5).add(new Edge(5, 6));
    }

    static void printAllPathsBFS(List<List<Edge>> graph, int src, int dest) {

        Queue<List<Integer>> q = new LinkedList<>();

        List<Integer> start = new ArrayList<>();
        start.add(src);

        q.offer(start);

        while (!q.isEmpty()) {

            List<Integer> path = q.poll();
            int last = path.get(path.size() - 1);

            if (last == dest) {
                System.out.println(path);
                continue;
            }

            for (Edge e : graph.get(last)) {

                if (!path.contains(e.dest)) {

                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(e.dest);

                    q.offer(newPath);
                }
            }
        }
    }

    public static void main(String[] args) {

        int V = 7;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        int src = 0;
        int dest = 6;

        printAllPathsBFS(graph, src, dest);
    }
}
```

**DFS**

-> https://youtu.be/84jNzUOY78c?si=b7GQGIuUOVLRh5EM

-> https://youtu.be/3czYbhac160?si=CHZWJSO5-y0YPOLZ

```java
import java.util.*;

public class DFS {

    static class Edge {
        int src;
        int dest;
        int wt;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }

    static void createGraph(List<List<Edge>> graph) {

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

    static void dfs(List<List<Edge>> graph, int curr, boolean[] visited) {

        visited[curr] = true;
        System.out.print(curr + " ");

        for (Edge e : graph.get(curr)) {
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    public static void main(String[] args) {

        int V = 7;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        dfs(graph, 0, new boolean[V]);
    }
}
```
**print all paths(DFS)**

```java
import java.util.*;

public class PrintAllPaths {

    static class Edge {
        int src;
        int dest;

        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static void createGraph(List<List<Edge>> graph) {

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

    static void printAllPaths(List<List<Edge>> graph, int src, int dest, boolean[] vis, String path) {

        if (src == dest) {
            System.out.println(path);
            return;
        }

        for (Edge e : graph.get(src)) {

            if (!vis[e.dest]) {

                vis[e.dest] = true;

                printAllPaths(
                        graph,
                        e.dest,
                        dest,
                        vis,
                        path + " -> " + e.dest
                );

                vis[e.dest] = false;
            }
        }
    }

    public static void main(String[] args) {

        int V = 7;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        int src = 0;
        int dest = 5;

        boolean[] vis = new boolean[V];
        vis[src] = true;

        printAllPaths(graph, src, dest, vis, String.valueOf(src));
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

        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static void createGraph(List<List<Edge>> graph) {

        graph.get(0).add(new Edge(0, 1));
        graph.get(0).add(new Edge(0, 2));

        graph.get(1).add(new Edge(1, 0));
        graph.get(1).add(new Edge(1, 3));
        graph.get(1).add(new Edge(1, 4));

        graph.get(2).add(new Edge(2, 0));
        graph.get(2).add(new Edge(2, 5));

        graph.get(3).add(new Edge(3, 1));
        graph.get(3).add(new Edge(3, 4));

        graph.get(4).add(new Edge(4, 1));
        graph.get(4).add(new Edge(4, 3));
        graph.get(4).add(new Edge(4, 6));

        graph.get(5).add(new Edge(5, 2));
        graph.get(5).add(new Edge(5, 7));

        graph.get(6).add(new Edge(6, 4));
        graph.get(6).add(new Edge(6, 7));

        graph.get(7).add(new Edge(7, 5));
        graph.get(7).add(new Edge(7, 6));
    }

    static boolean dfs(List<List<Edge>> graph, boolean[] vis, int curr, int parent) {

        vis[curr] = true;

        for (Edge e : graph.get(curr)) {

            int neighbor = e.dest;

            if (vis[neighbor] && neighbor != parent) {
                return true;
            }

            if (!vis[neighbor]) {
                if (dfs(graph, vis, neighbor, curr)) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean hasCycle(List<List<Edge>> graph) {

        boolean[] vis = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            if (!vis[i]) {
                if (dfs(graph, vis, i, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int V = 8;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        System.out.println(hasCycle(graph));
    }
}

```



**cycle directed**

```java
import java.util.*;

public class Main {

    static class Edge {
        int src;
        int dest;

        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static void createGraph(List<List<Edge>> graph) {

        graph.get(0).add(new Edge(0, 1));
        graph.get(1).add(new Edge(1, 2));
        graph.get(2).add(new Edge(2, 3));
        graph.get(3).add(new Edge(3, 4));
        graph.get(4).add(new Edge(4, 5));
        graph.get(5).add(new Edge(5, 6));
        graph.get(6).add(new Edge(6, 0));

        graph.get(2).add(new Edge(2, 7));
        graph.get(7).add(new Edge(7, 8));
        graph.get(8).add(new Edge(8, 3));
        graph.get(3).add(new Edge(3, 6));
        graph.get(5).add(new Edge(5, 2));

        graph.get(1).add(new Edge(1, 5));
        graph.get(6).add(new Edge(6, 1));

        graph.get(4).add(new Edge(4, 2));
        graph.get(8).add(new Edge(8, 6));
    }

    static boolean dfs(List<List<Edge>> graph, int curr,
                       boolean[] visited, boolean[] stack) {

        visited[curr] = true;
        stack[curr] = true;

        for (Edge e : graph.get(curr)) {

            if (stack[e.dest]) {
                return true;
            }

            if (!visited[e.dest]) {
                if (dfs(graph, e.dest, visited, stack)) {
                    return true;
                }
            }
        }

        stack[curr] = false;
        return false;
    }

    static boolean isCyclic(List<List<Edge>> graph, int V) {

        boolean[] visited = new boolean[V];
        boolean[] stack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(graph, i, visited, stack)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int V = 9;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        System.out.println(isCyclic(graph, V));
    }
}
```



**print all cycles(not exactly correct-experimental)**

*Note: Same as below code just replace visited with stack in if condition*

```java
import java.util.*;

public class Main {

    static class Edge {
        int src;
        int dest;

        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static void createGraph(List<List<Edge>> graph) {

        graph.get(0).add(new Edge(0, 1));
        graph.get(1).add(new Edge(1, 2));
        graph.get(2).add(new Edge(2, 3));
        graph.get(3).add(new Edge(3, 4));
        graph.get(4).add(new Edge(4, 5));
        graph.get(5).add(new Edge(5, 6));
        graph.get(6).add(new Edge(6, 0));

        graph.get(2).add(new Edge(2, 7));
        graph.get(7).add(new Edge(7, 8));
        graph.get(8).add(new Edge(8, 3));
        graph.get(3).add(new Edge(3, 6));
        graph.get(5).add(new Edge(5, 2));

        graph.get(1).add(new Edge(1, 5));
        graph.get(6).add(new Edge(6, 1));

        graph.get(4).add(new Edge(4, 2));
        graph.get(8).add(new Edge(8, 6));
    }

    static void dfs(List<List<Edge>> graph, int curr, boolean[] visited,
                    boolean[] stack, List<Integer> stack2) {

        visited[curr] = true;
        stack[curr] = true;
        stack2.add(curr);

        for (Edge e : graph.get(curr)) {

            if (!stack[e.dest]) {
                dfs(graph, e.dest, visited, stack, stack2);
            }

            else if (stack[e.dest]) {

                int index = stack2.indexOf(e.dest);

                System.out.print("Cycle:");
                for (int i = index; i < stack2.size(); i++) {
                    System.out.print(stack2.get(i) + " ");
                }
                System.out.println(e.dest);
            }
        }

        stack[curr] = false;
        stack2.remove(stack2.size() - 1);
    }

    public static void main(String[] args) {

        int V = 9;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        boolean[] visited = new boolean[V];
        boolean[] stack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, stack, new ArrayList<>());
            }
        }
    }
}
```

-> **print all cycles formed due to back-edges(not exactly correct-experimental)**- *Same as above code just replace stack with visited in if condition*


---


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

**outdegree**

```java
import java.util.*;

public class OutDegreeExample {
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

        // Array to store outdegrees
        int[] outdegree = new int[vertices];

        // Calculate outdegrees
        for (int u = 0; u < vertices; u++) {
            outdegree[u] = graph.get(u).size();
        }

        // Print outdegree of each vertex
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + " -> Out-degree: " + outdegree[i]);
        }
    }
}
```


**khan's algorithm for topological sort(using bfs and indegree)**

Note: using bfs and indegree.

Note2: works for disconnected graph also.(confirmed by chatgpt)

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


---

#### Dijikstra's algorithm ####

-> Dijikstras Algorithm works for

a) graph has cycles - yes

b) graph is directed - yes

c) graph is undirected - yes

d) graph has negative weights - no

-> https://youtu.be/j0OUwduDOS0?si=csRukXqR1gGkMJq8

-> https://youtu.be/EFg3u_E6eHU?si=yJOH7fIPYzKyw6HP

-> https://youtu.be/bZkzH5x0SKU?si=tl2VSPupOlq1RpvP

**Dijikstras algorithm**

```java
import java.util.*;

public class DijkstraClean {

    // Edge: destination + weight
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Dijkstra Algorithm
    public static int[] dijkstra(List<List<Edge>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        dist[src] = 0;
        pq.offer(new int[]{0, src}); // {distance, node}

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];

            // Skip outdated entry
            if (d != dist[u]) continue;

            for (Edge e : graph.get(u)) {
                int v = e.to;
                int newDist = d + e.weight;

                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(new int[]{newDist, v});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int n = 6;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        // Add edges (DIRECTED)
        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(2, 4));
        graph.get(1).add(new Edge(2, 1));
        graph.get(1).add(new Edge(3, 7));
        graph.get(2).add(new Edge(4, 3));
        graph.get(4).add(new Edge(3, 2));
        graph.get(3).add(new Edge(5, 1));

        int src = 0;
        int[] dist = dijkstra(graph, src);

        System.out.println("Shortest distances from " + src + ":");
        for (int i = 0; i < n; i++)
            System.out.println("Node " + i + " -> " + dist[i]);
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


