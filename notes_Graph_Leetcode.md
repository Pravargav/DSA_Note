1. ArrayList<Edge> g[]
Declares an array of ArrayList<Edge>.

Each index g[i] represents the adjacency list of node i.

2. = new ArrayList[V]
Creates the array with V elements (one for each vertex).

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

3. Graphs - bfs traversal( done using <ins>Queue data structure not Priority Queue</ins>)

4. Graphs - dfs traversal is done using recurrsion

5. Absolutely! You're spot on — not all graphs are connected. Graphs can consist of multiple disconnected components, and that's where this pattern shines.

Let’s break it down.

6. Why vis[] and the loop over all nodes?
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

``994. Rotting Oranges``(implicit graph format not adjacency list)

``200. Number of Islands``(implicit graph format not adjacency list)

<------------------------------------------------------------------>

-> To check cyclicity of directed graph we use both visited array and recursion stack array because [visit this video](https://youtu.be/9twcmtQj4DU?si=9ytHYKKb5rtX_bSX)

-> For topological sort same dfs code but just add stack.push(curr) after for loop

-> To check cyclicity of undirected graph we keep track of the parent node and visited array


<------------------------------------------------------------------>

```java
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


-> Dijkstra's algorithm is BFS usning Priority Queue in place of Normal Queue(Greedy and Positive Edges Only but takes less time Complexity so useful)

-> Bellman Ford algorithm (Dynamic Programming , Negative Edges But high time complexity so useful only for negativ edges) [visit this video](https://youtu.be/obWXjtg0L64?si=ZZ9ssTn2BnulKt8D)

```java
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
int dist[] = new int[graph.length];
for(int i=0; i<dist.length; i++) {
	if(i != src)
		dist[i] = Integer.MAX_VALUE;

}
//O(V)
for(int i=0; i<graph.length-1; i++) {
//edges - O(E)
	for(int j=0; j<graph.length; j++) {
		for(int k=0; k<graph[j].size(); k++) {
			Edge e = graph[j].get(k);
			int u = e.src;
			int v = e.dest;
			int wt = e.wt;
			if(dist[u] != Integer.MAX_VALUE && dist[u]+wt < dist[v]) {
				dist[v] = dist[u] + wt;
			}
		}
	}
}
//Detecting Negative Weight Cycle
for(int j=0; j<graph.length; j++) {
	for(int k=0; k<graph[j].size(); k++) {
		Edge e = graph[j].get(k);
		int u = e.src;
		int v = e.dest;
		int wt = e.wt;
		if(dist[u] != Integer.MAX_VALUE && dist[u]+wt < dist[v]) {
			System.out.println("negative weight cycle exists");
			break;
		}
	}
}
for(int i=0; i<dist.length; i++) {
	System.out.print(dist[i]+" ");
}

```
<------------------------------------------------------------------------>

-> Prims (Mst) Algorithm is simply adding nodes from Non-MST set to MST set using Priority Queue

```java
boolean vis[] = new boolean[graph.length];
PriorityQueue<Pair> pq = new PriorityQueue<>();
pq.add(new Pair(0, 0));
int cost = 0;
while(!pq.isEmpty()) {
	Pair curr = pq.remove();
	if(!vis[curr.v]) {
		vis[curr.v] = true;
		cost += curr.wt;
		for(int i=0; i<graph[curr.v].size(); i++) {
			Edge e = graph[curr.v].get(i);
			if(!vis[e.dest]) {
				pq.add(new Pair(e.dest, e.wt));
			}
		}
	}
}
```

-> Example/Sample Leetcode problems


a)

```java
class Solution {
    static class Edge {
        int x;
        int y;

        public Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<Edge> q = new LinkedList<>();

        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Edge(i,j));
                }
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0)
            return 0;
        if (q.isEmpty())
            return -1;

        int min = -1;
        int[][] dirs = new int[4][2];

        dirs[0][0]=1;
        dirs[0][1]=0;

        dirs[1][0]=-1;
        dirs[1][1]=0;

        dirs[2][0]=0;
        dirs[2][1]=-1;

        dirs[3][0]=0;
        dirs[3][1]=1;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {

                Edge e = q.remove();
                int x = e.x;
                int y = e.y;

                for (int[] dir : dirs) {
                    int i = x + dir[0];
                    int j = y + dir[1];
                    if ((i >= 0 && i < m) && (j >= 0 && j < n) && grid[i][j] == 1) {
                        grid[i][j] = 2;
                        fresh--;
                        q.add(new Edge(i,j));
                    }
                }
            }
            min++;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return min;
    }
}

//https://leetcode.com/problems/rotting-oranges/description/
```

b)

```java
class Solution {
    static class Edge {

        int dest;
        int wt;

        public Edge(int d, int w) {

            this.dest = d;
            this.wt = w;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        List<List<Edge>> lst= new ArrayList<>();
        for (int i = 0; i < n; i++)
            lst.add(new ArrayList<>());


        for (int[] flight : flights) {
            lst.get(flight[0]).add(new Edge(flight[1], flight[2]));
        }
        Queue<Edge> q = new LinkedList<>();
        q.offer(new Edge(src, 0));
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        int stops = 0;


        while (!q.isEmpty() && stops <= k) {
            int size = q.size();
            while (size-- > 0) {
                Edge curr = q.remove();
                for (Edge neighbour : lst.get(curr.dest)) {
                    int price = neighbour.wt, neighbourNode = neighbour.dest;
                    if (price + curr.wt < minCost[neighbourNode]){ 
                    minCost[neighbourNode] = price + curr.wt;
                    q.offer(new Edge(neighbourNode, minCost[neighbourNode]));
                    }
                }
            }
            stops++;
        }
        if(minCost[dst]==Integer.MAX_VALUE){
            return -1;
        }
        return minCost[dst];
    }
}

//https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
```



