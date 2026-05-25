
->DFS

```java
import java.util.*;

public class DisconnectedGraphDFS_Edge {

    static class Edge {
        int src;
        int dest;
        int wt;

        Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    // Create Disconnected Graph
    static void createGraph(List<List<Edge>> graph) {

        // Component 1: 0 - 1 - 2
        graph.get(0).add(new Edge(0, 1, 1));
        graph.get(1).add(new Edge(1, 0, 1));

        graph.get(1).add(new Edge(1, 2, 1));
        graph.get(2).add(new Edge(2, 1, 1));

        // Component 2: 3 - 4
        graph.get(3).add(new Edge(3, 4, 1));
        graph.get(4).add(new Edge(4, 3, 1));

        // Component 3: Node 5 is isolated (no edges)
    }

    // DFS function
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

        int V = 6;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        boolean[] visited = new boolean[V];

        // Handle disconnected components
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                System.out.print("Component: ");
                dfs(graph, i, visited);
                System.out.println();
            }
        }
    }
}
```
-> BFS

```java
import java.util.*;

public class BFS_Components {

    static class Edge {
        int src;
        int dest;
        int wt;

        Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    // Create Graph
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

    // BFS for one component
    static void bfs(List<List<Edge>> graph, int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

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
    }

    public static void main(String[] args) {
        int V = 7;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        boolean[] visited = new boolean[V];

        // Traverse all components
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                System.out.print("Component: ");
                bfs(graph, i, visited);
                System.out.println();
            }
        }
    }
}
```



<--->

-> To check cyclicity of directed graph we use both visited array and recursion stack array because [visit this video](https://youtu.be/9twcmtQj4DU?si=9ytHYKKb5rtX_bSX)

-> For topological sort same dfs code but just add stack.push(curr) after for loop

-> To check cyclicity of undirected graph we keep track of the parent node and visited array


<--->


-> Dijkstra's algorithm is BFS usning Priority Queue in place of Normal Queue(Greedy and Positive Edges Only but takes less time Complexity so useful)

-> Bellman Ford algorithm (Dynamic Programming , Negative Edges But high time complexity so useful only for negativ edges) [visit this video](https://youtu.be/obWXjtg0L64?si=ZZ9ssTn2BnulKt8D)



