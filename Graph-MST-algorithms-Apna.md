#### Minimum Spanning Tree (MST) - Wikipedia
👉 Link: [wikipedia - definition](https://en.wikipedia.org/wiki/Minimum_spanning_tree#:~:text=A%20minimum%20spanning%20tree%20(MST)%20or%20minimum,minimum%20spanning%20trees%20for%20its%20connected%20components.)

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

```java
//gfg and Techdose code
import java.io.*;
import java.lang.*;
import java.util.*;

class MST {

 
    int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < mstSet.length; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }


    void printMST(int parent[], int graph[][])
    {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < graph.length; i++)
            System.out.println(parent[i] + " - " + i + "\t"
                               + graph[parent[i]][i]);
    }


    void primMST(int graph[][])
    {
        int V = graph.length;
        
        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in
        // cut
        int key[] = new int[V];

        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }


        key[0] = 0;
      
        // First node is always root of MST
        parent[0] = -1;

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            


            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;


            for (int v = 0; v < V; v++)


                if (graph[u][v] != 0 && mstSet[v] == false
                    && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // Print the constructed MST
        printMST(parent, graph);
    }

    public static void main(String[] args)
    {
        MST t = new MST();
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                                      { 2, 0, 3, 8, 5 },
                                      { 0, 3, 0, 0, 7 },
                                      { 6, 8, 0, 0, 9 },
                                      { 0, 5, 7, 9, 0 } };

        // Print the solution
        t.primMST(graph);
    }
}
```
-> Here in above code we do use parent array to store parent indices of each node.

-> In a tree a node can have multiple children but can have only single parent.

-> Therefore only single parent exists for each node.So that single parent id can be stored in the index.

-> If multiple parents are possible for a tree then we can't use parent array because each index can store only one value/parent.

***Note:*** Below code is same code as above but using arraylist to store edges rather than parent array.
```java
//same code as above but using arraylist to store edges rather than parent array
import java.util.*;

class MST {

    // Find vertex with minimum key value
    int minKey(int key[], Boolean mstSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < mstSet.length; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // Print MST edges stored in ArrayList
    void printMST(ArrayList<int[]> edges)
    {
        System.out.println("Edge \tWeight");
        for (int[] e : edges)
            System.out.println(e[0] + " - " + e[1] + "\t" + e[2]);
    }

    // Prim's MST
    void primMST(int graph[][])
    {
        int V = graph.length;

        int key[] = new int[V];
        Boolean mstSet[] = new Boolean[V];
        int parent[] = new int[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
            parent[i] = -1;
        }

        key[0] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minKey(key, mstSet);

            // Safety check for disconnected graph
            if (u == -1)
                break;

            mstSet[u] = true;

            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && !mstSet[v]
                        && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // Store MST edges in ArrayList
        ArrayList<int[]> mstEdges = new ArrayList<>();

        for (int i = 1; i < V; i++) {
            if (parent[i] != -1)
                mstEdges.add(new int[]{parent[i], i, graph[parent[i]][i]});
        }

        printMST(mstEdges);
    }

    public static void main(String[] args)
    {
        MST t = new MST();

        int graph[][] = new int[][] {
            { 0, 2, 0, 6, 0 },
            { 2, 0, 3, 8, 5 },
            { 0, 3, 0, 0, 7 },
            { 6, 8, 0, 0, 9 },
            { 0, 5, 7, 9, 0 }
        };

        t.primMST(graph);
    }
} 
```

-> Prims algo using Priority Queue - Apna, striver and Gfg(second version of gfg code).

```java
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
-> Kruskal's algorithm - Gfg and striver code

```java
import java.util.*;

class DisjointSet {
    /* To store the ranks, parents and 
    sizes of different set of vertices */
    List<Integer> rank, parent, size;
    
    // Constructor
    public DisjointSet(int n) {
        rank = new ArrayList<>(n + 1);
        Collections.fill(rank, 0);
        parent = new ArrayList<>(n + 1);
        size = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            size.add(1);
        }
    }
    
    // Function to find ultimate parent
    public int findUPar(int node) {
        if (node == parent.get(node))
            return node;
        parent.set(node, findUPar(parent.get(node)));
        return parent.get(node);
    }
    
    // Function to implement union by rank
    public void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        }
        else if (rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
        }
        else {
            parent.set(ulp_v, ulp_u);
            rank.set(ulp_u, rank.get(ulp_u) + 1);
        }
    }
    
    // Function to implement union by size
    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        }
        else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

// Solution class
class Solution {
    // Function to get the sum of weights of edges in MST
    public int spanningTree(int V, List<List<List<Integer>>> adj) {
        
        // To store the edges
        List<int[]> edges = new ArrayList<>();
        
        // Getting all edges from adjacency list
        for (int i = 0; i < V; i++) {
            for (List<Integer> it : adj.get(i)) {
                int v = it.get(0); // Node v
                int wt = it.get(1); // edge weight
                int u = i; // Node u
                edges.add(new int[]{wt, u, v});
            }
        }
        
        // Creating a disjoint set of V vertices
        DisjointSet ds = new DisjointSet(V);
        
        // Sorting the edges based on their weights
        edges.sort(Comparator.comparingInt(o -> o[0]));
        
        // To store the sum of edges in MST
        int sum = 0;
        
        // Iterate on the edges
        for (int[] it : edges) {
            int wt = it[0]; // edge weight
            int u = it[1]; // First node
            int v = it[2]; // Second node
            
            // Join the nodes if not in the same set 
            if (ds.findUPar(u) != ds.findUPar(v)) {
                
                // Update the sum of edges in MST
                sum += wt;
                
                // Unite the nodes 
                ds.unionBySize(u, v);
            }
        }
        
        // Return the computed sum
        return sum;
    }
    
    public static void main(String[] args) {
        int V = 4;
        List<int[]> edges = Arrays.asList(
            new int[]{0, 1, 1},
            new int[]{1, 2, 2},
            new int[]{2, 3, 3},
            new int[]{0, 3, 4}
        );
        
        // Forming the adjacency list from edges
        List<List<List<Integer>>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] it : edges) {
            int u = it[0];
            int v = it[1];
            int wt = it[2];
            
            adj.get(u).add(Arrays.asList(v, wt));
            adj.get(v).add(Arrays.asList(u, wt));
        }
        
        // Creating instance of Solution class
        Solution sol = new Solution();
        
        /* Function call to get the sum 
        of weights of edges in MST */
        int ans = sol.spanningTree(V, adj);
        
        System.out.println("The sum of weights of edges in MST is: " + ans);
    }
}
```
