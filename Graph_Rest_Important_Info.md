

Note: This does not initialize each ArrayList inside — you need to do that in a loop.


```java
import java.util.*;

class Edge {
    int to, weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Graph {
    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge> g[] = new ArrayList[V];

        // Initialize each list
        for (int i = 0; i < V; i++) {
            g[i] = new ArrayList<>();
        }

        // Add some edges (u -> v with weight w)
        g[0].add(new Edge(1, 10));
        g[0].add(new Edge(4, 20));
        g[1].add(new Edge(2, 30));
        g[2].add(new Edge(3, 40));

        // Print the graph
        for (int i = 0; i < V; i++) {
            System.out.print("Node " + i + ": ");
            for (Edge e : g[i]) {
                System.out.print("-> (to: " + e.to + ", wt: " + e.weight + ") ");
            }
            System.out.println();
        }
    }
}
```



-> Why vis[] and the loop over all nodes?
In disconnected graphs, running DFS or BFS from just one node (e.g., 0) won’t reach all other nodes — because some might not be connected at all.

->DFS

```java
import java.util.*;

public class DisconnectedGraphDFS {
    static void dfs(List<List<Integer>> graph, int node, boolean[] vis) {
        vis[node] = true;
        System.out.print(node + " ");

        for (int neighbor : graph.get(node)) {
            if (!vis[neighbor]) {
                dfs(graph, neighbor, vis);
            }
        }
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        // Component 1: 0-1-2
        graph.get(0).add(1);
        graph.get(1).add(0);
        graph.get(1).add(2);
        graph.get(2).add(1);

        // Component 2: 3-4
        graph.get(3).add(4);
        graph.get(4).add(3);

        // Component 3: Node 5 is isolated

        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                System.out.print("Component: ");
                dfs(graph, i, vis);
                System.out.println();
            }
        }
    }
}
```
-> BFS

```java
static void bfs(List<List<Integer>> graph, int start, boolean[] vis) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    vis[start] = true;

    while (!q.isEmpty()) {
        int node = q.poll();
        System.out.print(node + " ");
        for (int neighbor : graph.get(node)) {
            if (!vis[neighbor]) {
                vis[neighbor] = true;
                q.offer(neighbor);
            }
        }
    }
}

for (int i = 0; i < V; i++) {
    if (!vis[i]) {
        System.out.print("Component: ");
        bfs(graph, i, vis);
        System.out.println();
    }
}

```

-> Write a code finds the shortest distance from the starting node to every node in an unweighted graph using BFS/dijikstras algorithm.






<--->

-> To check cyclicity of directed graph we use both visited array and recursion stack array because [visit this video](https://youtu.be/9twcmtQj4DU?si=9ytHYKKb5rtX_bSX)

-> For topological sort same dfs code but just add stack.push(curr) after for loop

-> To check cyclicity of undirected graph we keep track of the parent node and visited array


<--->

```java
import java.util.*;

public class DijkstraClean {

    static class Edge {
        int dest, wt;

        Edge(int dest, int wt) {
            this.dest = dest;
            this.wt = wt;
        }
    }

    static class Pair implements Comparable<Pair> {
        int node, dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        public int compareTo(Pair p) {
            return this.dist - p.dist;
        }
    }

    public static int[] dijkstra(List<List<Edge>> graph, int src) {

        int V = graph.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        dist[src] = 0;
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();
            int u = curr.node;

            // Skip outdated entry
            if (curr.dist > dist[u]) continue;

            for (Edge e : graph.get(u)) {
                int v = e.dest;
                int newDist = dist[u] + e.wt;

                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.add(new Pair(v, newDist));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        int V = 5;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        // Undirected edges
        graph.get(0).add(new Edge(1, 2));
        graph.get(1).add(new Edge(0, 2));

        graph.get(0).add(new Edge(2, 4));
        graph.get(2).add(new Edge(0, 4));

        graph.get(1).add(new Edge(2, 1));
        graph.get(2).add(new Edge(1, 1));

        graph.get(1).add(new Edge(3, 7));
        graph.get(3).add(new Edge(1, 7));

        graph.get(2).add(new Edge(4, 3));
        graph.get(4).add(new Edge(2, 3));

        int[] dist = dijkstra(graph, 0);

        System.out.println(Arrays.toString(dist));
    }
}
```


-> Dijkstra's algorithm is BFS usning Priority Queue in place of Normal Queue(Greedy and Positive Edges Only but takes less time Complexity so useful)

-> Bellman Ford algorithm (Dynamic Programming , Negative Edges But high time complexity so useful only for negativ edges) [visit this video](https://youtu.be/obWXjtg0L64?si=ZZ9ssTn2BnulKt8D)



