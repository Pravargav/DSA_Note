-> ***Note: A graph is a bipartite if and only if it does not contain no odd cycles -only applicable for undirected graphs***

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
**Two-Coloring BFS- Bipartite**

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

**DFS-printing weights from parent**

```java
import java.util.*;

class Solution {

    static class Edge {
        int u, v;
        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public int[] gardenNoAdj(int n, int[][] paths) {

        boolean[][] ok = new boolean[n + 1][5];

        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= 4; c++) {
                ok[i][c] = true;
            }
        }

        List<List<Edge>> g = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        for (int[] p : paths) {
            int a = p[0], b = p[1];
            g.get(a).add(new Edge(a, b));
            g.get(b).add(new Edge(b, a));
        }

        boolean[] vis = new boolean[n + 1];
        int[] col = new int[n + 1];

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {

            if (!vis[i]) {

                q.add(i);
                vis[i] = true;
                col[i] = 1;

                for (int c = 1; c <= 4; c++) ok[i][c] = false;

                for (Edge e : g.get(i)) ok[e.v][1] = false;

                while (!q.isEmpty()) {

                    int cur = q.remove();

                    for (Edge e : g.get(cur)) {

                        int nb = e.v;

                        if (!vis[nb]) {

                            for (int c = 1; c <= 4; c++) {
                                if (ok[nb][c]) {
                                    col[nb] = c;
                                    vis[nb] = true;

                                    for (Edge x : g.get(nb)) {
                                        ok[x.v][c] = false;
                                    }
                                    break;
                                }
                            }

                            q.add(nb);
                        }
                    }
                }
            }
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = col[i + 1];
        }

        return ans;
    }
}
```

```java
import java.util.*;

public class Main {

    
    static class Edge {
        int dest;
        int weight;

        Edge(int d, int w) {
            this.dest = d;
            this.weight = w;
        }
    }

    // Recursive DFS
    public static void dfs(List<List<Edge>> graph, int curr, boolean[] visited, int x) {

        visited[curr] = true;

        // Traverse neighbors
        for (Edge e : graph.get(curr)) {
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited, x*e.weight);
            }
            System.out.println(e.dest+ " "+ x*e.weight);
        }
    }

    public static void main(String[] args) {

        int V = 10; 

        // Adjacency List
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Weighted DAG edges
        graph.get(0).add(new Edge(1, 5));
        graph.get(0).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 6));
        graph.get(1).add(new Edge(2, 2));
        graph.get(2).add(new Edge(4, 4));
        graph.get(2).add(new Edge(5, 2));
        graph.get(3).add(new Edge(6, 1));
        graph.get(4).add(new Edge(6, 2));
        graph.get(5).add(new Edge(7, 3));
        graph.get(6).add(new Edge(8, 4));
        graph.get(7).add(new Edge(9, 1));

        boolean[] visited = new boolean[V];

        // Important for BIG graphs: handle disconnected components
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, 1);
            }
        }
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

**Topological Sort**

-> https://youtu.be/NX1_etRg078?si=tyQIGE2DkyYijT7r

-> https://youtu.be/73sneFXuTEg?si=YQsv8J8yuZhXYfpf

**Indegree**

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

**Outdegree**

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


**Kahn's algorithm for topological sort(using bfs and indegree)**

Note: using bfs and indegree.

Note2: works for disconnected graph also.(confirmed by chatgpt)

-> Using BFS

```java
import java.util.*;

public class KahnsTopologicalSort {

    // Kahn's Algorithm (BFS Topological Sort)
    public static List<Integer> topoSort(int V, List<List<Integer>> graph) {

        int[] indegree = new int[V];

        // Compute indegree of each node
        for (int u = 0; u < V; u++) {
            for (int v : graph.get(u)) {
                indegree[v]++;
            }
        }

        // Use ArrayDeque (faster than LinkedList)
        Queue<Integer> q = new ArrayDeque<>();

        // Add nodes with indegree 0
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        List<Integer> topo = new ArrayList<>();

        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);

            for (int v : graph.get(u)) {
                if (--indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        // If not all nodes processed → cycle exists
        if (topo.size() != V) {
            return Collections.emptyList(); // indicates cycle
        }

        return topo;
    }

    public static void main(String[] args) {

        int V = 6;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        // Directed edges
        addEdge(graph, 5, 2);
        addEdge(graph, 5, 0);
        addEdge(graph, 4, 0);
        addEdge(graph, 4, 1);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 1);

        List<Integer> result = topoSort(V, graph);

        if (result.isEmpty())
            System.out.println("Cycle detected! Topological sort not possible.");
        else
            System.out.println("Topological Order: " + result);
    }

    static void addEdge(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
    }
}
```

-> Using DFS

```java
import java.util.*;

public class TopoSortDFS {

    public static List<Integer> topoSort(int V, List<List<Integer>> graph) {

        boolean[] visited = new boolean[V];
        boolean[] inStack = new boolean[V]; // for cycle detection
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (!dfs(i, graph, visited, inStack, stack)) {
                    return Collections.emptyList(); // cycle detected
                }
            }
        }

        List<Integer> topo = new ArrayList<>();
        while (!stack.isEmpty()) {
            topo.add(stack.pop());
        }

        return topo;
    }

    private static boolean dfs(int u,
                               List<List<Integer>> graph,
                               boolean[] visited,
                               boolean[] inStack,
                               Stack<Integer> stack) {

        visited[u] = true;
        inStack[u] = true;

        for (int v : graph.get(u)) {

            if (!visited[v]) {
                if (!dfs(v, graph, visited, inStack, stack))
                    return false;
            }
            else if (inStack[v]) {
                // Back edge → cycle
                return false;
            }
        }

        inStack[u] = false;
        stack.push(u); // push after processing children

        return true;
    }

    public static void main(String[] args) {

        int V = 6;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        addEdge(graph, 5, 2);
        addEdge(graph, 5, 0);
        addEdge(graph, 4, 0);
        addEdge(graph, 4, 1);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 1);

        List<Integer> result = topoSort(V, graph);

        if (result.isEmpty())
            System.out.println("Cycle detected! No Topological Order.");
        else
            System.out.println("Topological Order: " + result);
    }

    static void addEdge(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
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

public class DijkstraWithVisited {

    // Edge: destination + weight
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static int[] dijkstra(List<List<Edge>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        boolean[] vis = new boolean[n]; // 👈 visited array

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        dist[src] = 0;
        pq.offer(new int[]{0, src}); // {distance, node}

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];

            // 👇 Skip if already processed
            if (vis[u]) continue;

            vis[u] = true; // mark as visited

            for (Edge e : graph.get(u)) {
                int v = e.to;
                int newDist = d + e.weight;

                if (!vis[v] && newDist < dist[v]) {
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

        // Directed edges
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

**Bellman ford**

-> https://youtu.be/j0OUwduDOS0?si=K3riyaeuZE_l0u2k

-> https://youtu.be/Mn9bFIIyXIM?si=D8WU8NRn7gETDS0Z

-> Use this code for negative edges graph

```java
import java.util.*;

public class BellmanFordClean {

    // Edge representation
    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // Bellman-Ford Algorithm
    public static int[] bellmanFord(int V, List<Edge> edges, int src) {

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax edges V-1 times
        for (int i = 1; i <= V - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.u] != Integer.MAX_VALUE &&
                    dist[e.u] + e.w < dist[e.v]) {

                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }

        // Detect negative cycle
        for (Edge e : edges) {
            if (dist[e.u] != Integer.MAX_VALUE &&
                dist[e.u] + e.w < dist[e.v]) {

                System.out.println("Negative weight cycle detected!");
                return null;
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        int V = 5;
        List<Edge> edges = new ArrayList<>();

        // Directed weighted edges
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, -4));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 4, 4));
        edges.add(new Edge(4, 1, -1));

        int src = 0;

        int[] dist = bellmanFord(V, edges, src);

        if (dist != null) {
            System.out.println("Shortest distances from " + src + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("Node " + i + " -> " + dist[i]);
            }
        }
    }
}
```

-> ***Note: A graph is a bipartite if and only if it does not contain no odd cycles -only applicable for undirected graphs***
