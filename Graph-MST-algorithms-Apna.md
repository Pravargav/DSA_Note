#### Minimum Spanning Tree (MST) - Wikipedia
👉 Link: [wikipedia - definition](https://en.wikipedia.org/wiki/Minimum_spanning_tree)

A **minimum spanning tree (MST)** or **minimum weight spanning tree** is a subset of the edges of a connected, edge‑weighted undirected graph that connects all the vertices together, without any cycles and with the minimum possible total edge weight.[1]

That is, it is a **spanning tree whose sum of edge weights is as small as possible**.[2]

More generally, any edge-weighted undirected graph (not necessarily connected) has a **minimum spanning forest**, which is a union of the minimum spanning trees for its connected components.

#### MST Visualizations — Princeton University

Visit the Princeton University MST page to watch **two visualization videos** that demonstrate:

1. **Kruskal’s Algorithm Visualization**  
2. **Prim’s Algorithm Visualization**

👉 Link: [princeton university — MST Visualizations](https://algs4.cs.princeton.edu/43mst/)

These videos appear under the **“Visualizations”** section of the page, showing how both algorithms operate on a Euclidean graph with 500 vertices.  

#### Union-Find Algorithm — Video Resource

Watch the Union-Find (Disjoint Set Union) algorithm explanation here:

👉  Link: [Union-find-Explanation](https://youtu.be/ayW5B2W9hfo?si=IdoGvXzGN8j4NshB)

👉  Link: [Union-find-Explanation2](https://youtu.be/92UpvDXc8fs?si=fgcueqAXGoSwh3fc)

#### MST Visualizations — Wikipedia

1. **Kruskal’s Algorithm Visualization**  
2. **Prim’s Algorithm Visualization**

👉 Link: [kruskal's](https://en.wikipedia.org/wiki/Kruskal%27s_algorithm)

👉 Link: [prim's](https://en.wikipedia.org/wiki/Prim%27s_algorithm)

####  Prim’s Algorithm- 👉 Link: [Prim’s Algorithm Source](https://www.baeldung.com/cs/kruskals-vs-prims-algorithm)

Prim’s algorithm is similar in spirit to **Dijkstra’s algorithm**.  
Instead of edges, it grows the MST by always choosing the **next nearest node**.

##### **Optimization**
To improve time complexity:
- Ensure each node **appears only once** in the queue
- Use an `addOrUpdate` function:
  - If a node is already in the queue **and** the new edge weight is smaller then remove the old entry and insert the new one  
  - If it isn’t present then simply add it


👉 Link: [Kruskal’s Algorithm Source](https://youtu.be/OxfTT8slSLs?si=ucQFVqdZjzw5EBEN)

👉 Link: [Kruskal’s Algorithm Source2](https://youtu.be/JZBQLXgSGfs?si=Oa79wrzmBssDaa-9)

👉 Link: [prims’s Algorithm Source2](https://youtu.be/20QfaLQPLqQ?si=32I0uXw1EzTp7zhg)

-> Note: Williamfeast lazy prims and eager prims videos are wrong.Don't trust and don't watch.

-> Note2: Apna college only shows calculating the minimum total cost, not the printing the edges of mst.

-> Note3: Techdose code is same as gfg code for prims algorithm.They are using min key function in place of priority queue.


-> Here in above code we do use parent array to store parent indices of each node.

-> In a tree a node can have multiple children but can have only single parent.

-> Therefore only single parent exists for each node.So that single parent id can be stored in the index.

-> If multiple parents are possible for a tree then we can't use parent array because each index can store only one value/parent.




-> Prims algo using Priority Queue - Apna, striver and Gfg(second version of gfg code).

```java
//Striver code
import java.util.*;

// User function Template for Java

class Pair {
    int node;
    int distance;
    public Pair(int distance, int node) {
        this.node = node;
        this.distance = distance;
    }
}
class Solution {
    //Function to find sum of weights of edges of the Minimum Spanning Tree.
    static int spanningTree(int V,
                            ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        PriorityQueue<Pair> pq =
            new PriorityQueue<Pair>((x, y) -> x.distance - y.distance);

        int[] vis = new int[V];
        // {wt, node}
        pq.add(new Pair(0, 0));
        int sum = 0;
        while (pq.size() > 0) {
            int wt = pq.peek().distance;
            int node = pq.peek().node;
            pq.remove();

            if (vis[node] == 1) continue;
            // add it to the mst
            vis[node] = 1;
            sum += wt;

            for (int i = 0; i < adj.get(node).size(); i++) {
                int edW = adj.get(node).get(i).get(1);
                int adjNode = adj.get(node).get(i).get(0);
                if (vis[adjNode] == 0) {
                    pq.add(new Pair(edW, adjNode));
                }
            }
        }
        return sum;
    }
}

public class tUf {
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
        int[][] edges =  {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<ArrayList<Integer>>());
        }

        for (int i = 0; i < 6; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            ArrayList<Integer> tmp1 = new ArrayList<Integer>();
            ArrayList<Integer> tmp2 = new ArrayList<Integer>();
            tmp1.add(v);
            tmp1.add(w);

            tmp2.add(u);
            tmp2.add(w);

            adj.get(u).add(tmp1);
            adj.get(v).add(tmp2);
        }

        Solution obj = new Solution();
        int sum = obj.spanningTree(V, adj);
        System.out.println("The sum of all the edge weights: " + sum);
    }
}
```

```java
import java.util.*;

public class PrimsClean {

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

        @Override
        public int compareTo(Pair p) {
            return this.dist - p.dist;
        }
    }

    public static int prims(List<List<Edge>> graph) {

        int V = graph.size();

        boolean[] vis = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        int mstWeight = 0;

        pq.offer(new Pair(0, 0)); // (node, edgeWeight)

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();

            int u = curr.node;
            int wt = curr.dist;

            if (vis[u]) {
                continue;
            }

            vis[u] = true;
            mstWeight += wt;

            for (Edge e : graph.get(u)) {

                int v = e.dest;

                if (!vis[v]) {
                    pq.offer(new Pair(v, e.wt));
                }
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {

        int V = 5;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Undirected graph
        graph.get(0).add(new Edge(1, 2));
        graph.get(1).add(new Edge(0, 2));

        graph.get(0).add(new Edge(2, 1));
        graph.get(2).add(new Edge(0, 1));

        graph.get(1).add(new Edge(2, 1));
        graph.get(2).add(new Edge(1, 1));

        graph.get(2).add(new Edge(3, 2));
        graph.get(3).add(new Edge(2, 2));

        graph.get(3).add(new Edge(4, 1));
        graph.get(4).add(new Edge(3, 1));

        graph.get(4).add(new Edge(2, 2));
        graph.get(2).add(new Edge(4, 2));

        int mstWeight = prims(graph);

        System.out.println("MST Weight = " + mstWeight);
    }
}
```

-> Kruskal's algorithm 

```java
import java.util.*;

class Edge {
    int dest, wt;

    Edge(int dest, int wt) {
        this.dest = dest;
        this.wt = wt;
    }
}

class DisjointSet {
    List<Integer> parent, size;

    public DisjointSet(int n) {
        parent = new ArrayList<>();
        size = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node))
            return node;

        parent.set(node, findUPar(parent.get(node)));
        return parent.get(node);
    }

    public void unionBySize(int u, int v) {
        int pu = findUPar(u);
        int pv = findUPar(v);

        if (pu == pv) return;

        if (size.get(pu) < size.get(pv)) {
            parent.set(pu, pv);
            size.set(pv, size.get(pu) + size.get(pv));
        } else {
            parent.set(pv, pu);
            size.set(pu, size.get(pu) + size.get(pv));
        }
    }
}

class Solution {

    public int spanningTree(int V, List<List<Edge>> adj) {

        List<int[]> edges = new ArrayList<>();

        // Collect all edges
        for (int u = 0; u < V; u++) {
            for (Edge e : adj.get(u)) {
                edges.add(new int[]{e.wt, u, e.dest});
            }
        }

        edges.sort(Comparator.comparingInt(a -> a[0]));

        DisjointSet ds = new DisjointSet(V);

        int mstWeight = 0;

        for (int[] edge : edges) {
            int wt = edge[0];
            int u = edge[1];
            int v = edge[2];

            if (ds.findUPar(u) != ds.findUPar(v)) {
                mstWeight += wt;
                ds.unionBySize(u, v);
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {

        int V = 4;

        List<List<Edge>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Undirected graph
        adj.get(0).add(new Edge(1, 1));
        adj.get(1).add(new Edge(0, 1));

        adj.get(1).add(new Edge(2, 2));
        adj.get(2).add(new Edge(1, 2));

        adj.get(2).add(new Edge(3, 3));
        adj.get(3).add(new Edge(2, 3));

        adj.get(0).add(new Edge(3, 4));
        adj.get(3).add(new Edge(0, 4));

        Solution sol = new Solution();

        int ans = sol.spanningTree(V, adj);

        System.out.println("The sum of weights of edges in MST is: " + ans);
    }
}
```
