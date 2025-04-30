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
