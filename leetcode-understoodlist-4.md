1. ArrayList<Edge> g[]
Declares an array of ArrayList<Edge>.

Each index g[i] represents the adjacency list of node i.

2. = new ArrayList[V]
Creates the array with V elements (one for each vertex).

Note: This does not initialize each ArrayList inside — you need to do that in a loop.


```
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

3. Graphs - bfs traversal( done using <ins>Queue data structure not Priority Queue</ins>)

4. Graphs - dfs traversal is done using recurrsion

5. Absolutely! You're spot on — not all graphs are connected. Graphs can consist of multiple disconnected components, and that's where this pattern shines.

Let’s break it down.

6. Why vis[] and the loop over all nodes?
In disconnected graphs, running DFS or BFS from just one node (e.g., 0) won’t reach all other nodes — because some might not be connected at all.

->DFS

```
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

```
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

``797. All Paths From Source to Target``

``994. Rotting Oranges``(implicit graph format not adjacency list)

``200. Number of Islands``(implicit graph format not adjacency list)

-> To check cyclicity of directed graph we use both visited array and recursion stack array because [visit this video](https://youtu.be/9twcmtQj4DU?si=9ytHYKKb5rtX_bSX)

-> For topological sort same dfs code but just add stack.push(curr) after for loop

-> To check cyclicity of undirected graph we keep track of the parent node and visited array


```
PriorityQueue<Pair> pq = new PriorityQueue<>();
int dist[] = new int[graph.length];
boolean vis[] = new boolean[graph.length];
for(int i=0; i<dist.length; i++) {
	if(i != src) {
		dist[i] = Integer.MAX_VALUE;
	}
}
pq.add(new Pair(src, 0));
while(!pq.isEmpty()) {
	Pair curr = pq.remove();
	if(!vis[curr.n]) {
		vis[curr.n] = true;
		for(int i=0; i<graph[curr.n].size(); i++) {
			Edge e = graph[curr.n].get(i);
			int u = e.src;
			int v = e.dest;
			if(!vis[v] && dist[u]+e.wt < dist[v]) {
				dist[v] = dist[u] + e.wt;
				pq.add(new Pair(v, dist[v]));
			}
		}
	}
}

```
-> Dijkstra's algorithm is BFS usning Priority Queue in place of Normal Queue

->
