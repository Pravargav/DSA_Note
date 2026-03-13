
**Arraylist of Arraylist from 2d array**
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
<--->



-> Example/Sample Leetcode problems


a) https://leetcode.com/problems/rotting-oranges/description/ 

(implicit graph format not adjacency list)

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

//
```

b) https://leetcode.com/problems/cheapest-flights-within-k-stops/description/

(implicit graph format not adjacency list)

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


```

c) 

 i) https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i


ii) https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii


```java



class Solution {

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int minT;

        public Pair(int x, int y, int minT) {
            this.x = x;
            this.y = y;
            this.minT = minT;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.minT - p2.minT;
        }
    }

    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length;
        int n = moveTime[0].length;

        int dist[][] = new int[m][n];
        boolean vis[][] = new boolean[m][n];

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dist[0][0] = 0;

        pq.add(new Pair(0, 0, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!vis[curr.x][curr.y]) {
                vis[curr.x][curr.y] = true;

                int ux1 = curr.x;
                int uy1 = curr.y;

                if (curr.x + 1 < m) {

                    int vx1 = curr.x + 1;
                    int vy1 = curr.y;
                    int nT = 0;

                    if ( dist[ux1][uy1] < moveTime[vx1][vy1]) {
                        nT = moveTime[vx1][vy1] + 1;

                    } else if ( dist[ux1][uy1] >= moveTime[vx1][vy1]) {
                        nT = dist[ux1][uy1] + 1;
                    }
                    if (!vis[vx1][vy1] && nT < dist[vx1][vy1]) {
                        dist[vx1][vy1] = nT;
                        pq.add(new Pair(vx1, vy1, dist[vx1][vy1]));
                    }

                }

                if (curr.x - 1 >= 0) {

                    int vx2 = curr.x - 1;
                    int vy2 = curr.y;
                    int nT = 0;

                    if ( dist[ux1][uy1] < moveTime[vx2][vy2]) {
                        nT = moveTime[vx2][vy2] + 1;
                    } else if (dist[ux1][uy1] >= moveTime[vx2][vy2]) {
                        nT = dist[ux1][uy1] + 1;

                    }
                    if (!vis[vx2][vy2] && nT < dist[vx2][vy2]) {
                        dist[vx2][vy2] = nT;
                        pq.add(new Pair(vx2, vy2, dist[vx2][vy2]));
                    }

                }

                if (curr.y + 1 < n) {

                    int vx3 = curr.x;
                    int vy3 = curr.y + 1;
                    int nT = 0;

                    if (dist[ux1][uy1] < moveTime[vx3][vy3]) {
                        nT = moveTime[vx3][vy3] + 1;
                    } else if (  dist[ux1][uy1] >= moveTime[vx3][vy3]) {
                        nT = dist[ux1][uy1] + 1;
                    }

                    if (!vis[vx3][vy3] && nT < dist[vx3][vy3]) {
                        dist[vx3][vy3] = nT;
                        pq.add(new Pair(vx3, vy3, dist[vx3][vy3]));
                    }

                }

                if (curr.y - 1 >= 0) {

                    int vx4 = curr.x;
                    int vy4 = curr.y - 1;
                    int nT = 0;

                    if ( dist[ux1][uy1] < moveTime[vx4][vy4]) {
                        nT = moveTime[vx4][vy4] + 1;

                    } else if ( dist[ux1][uy1] >= moveTime[vx4][vy4]) {
                        nT = dist[ux1][uy1] + 1;
                    }

                    if (!vis[vx4][vy4] && nT < dist[vx4][vy4]) {
                        dist[vx4][vy4] = nT;
                        pq.add(new Pair(vx4, vy4, dist[vx4][vy4]));
                    }

                }

            }

        }

        return dist[m - 1][n - 1];
    }
}


```
