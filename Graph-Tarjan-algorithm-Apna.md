-> A condensation graph is a DAG (Directed Acyclic Graph) formed by collapsing each Strongly Connected Component (SCC) of a directed graph into a single node.

Each SCC → one node in condensation graph

Original edges between SCCs → edges between nodes

Resulting graph is always a DAG, even if the original graph had cycles


-> A strongly connected component (SCC) can contain overlapping or nested cycles, paths that are not part of simple cycles, or a combination of both.( Chatgpt and DeepSeek)
[visit this article](https://cp-algorithms.com/graph/strongly-connected-components.html)(Can be solved using Kosaraju's algorithm and Tarjan's algorithm)

![alt text](https://cp-algorithms.com/graph/strongly-connected-components-tikzpicture/graph.svg)

![alt text](https://cp-algorithms.com/graph/strongly-connected-components-tikzpicture/cond_graph.svg)



[youtube-willam-heist](https://youtu.be/wUgWX0nc4NY?si=rj0aLhlNHrLHv95C)



### ❌ Statement 1 (Incorrect / Misleading)

> **“The low-link value of a node is the smallest node ID reachable from that node when doing a DFS, including itself.”**

🔴 **This is NOT strictly correct.**

#### Why?

* **Low-link is NOT about node ID**
* It is about **DFS discovery time**

#### ✅ Correct version

> **The low-link value of a node is the minimum *DFS discovery time* reachable from that node (including itself), using tree edges and back edges to nodes still on the stack.**

📌 Node IDs are arbitrary labels.
📌 Discovery time represents DFS order (ancestor relationship).



#### ⚠️ Why TopCoder says “node ID”

Many TopCoder problems **label nodes in DFS order (0,1,2,...)**, so:

```
node ID == discovery time
```

➡️ In **that special context**, their statement *appears* correct.
➡️ In **general graph theory**, it is **wrong wording**.


### ⚠️ Statement 2 (Conceptually correct, wording sloppy)

> **“There is a catch with doing a DFS on the graph, as it is highly dependent on the traversal order of the DFS, which is effectively random.”**

🟡 **Idea is correct, wording is loose**

#### Clarification:

* DFS order **depends on adjacency list order**
* It is **deterministic**, not random
* But **can vary between implementations**

✔ The important idea:

> SCC detection must be **independent of DFS order**

Tarjan’s algorithm guarantees that.



### ✅ Statement 3 (Correct and Important)

> **“To cope with the random traversal order of the DFS, Tarjan’s algorithm maintains a stack of valid nodes from which to update low-link values.”**

✔ **Correct idea**

The stack ensures:

* We only consider **active DFS path nodes**
* Prevents cross-SCC contamination
* Ensures correctness regardless of DFS order



### ✅ Statement 4 (Correct)

> **“Nodes are added to the stack of valid nodes as they are explored for the first time. Nodes are removed from the stack each time a complete SCC is found.”**

✔ **100% correct**

This is the **core invariant** of Tarjan’s algorithm.



### 🧠 The Precise Version

Here is the **correct technical formulation** replacing TopCoder’s wording:

> The low-link value of a node `u` is the minimum DFS discovery time reachable from `u`, including itself, by following DFS tree edges and at most one back edge to a node currently on the stack. The stack is used to track active nodes in the current DFS path, and nodes are removed from the stack only when an SCC is fully identified.


-> Striver and Apna college assigned node-id's intelligently so to exactly match time with node-id's.But both are not same.Refer to striver's graph diagram and Apna graph diagram in youtube [striver-ex](https://takeuforward.org/graph/bridges-in-graph-using-tarjans-algorithm-of-time-in-and-low-time-g-55).





### ✔️ FIX (BEST & CLEANEST): Use `static int time`

### ✅ Corrected Working Code

```java
import java.util.*;

public class TarjanSCC {

    static class Edge {
        int src, dest;

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    static int time = 0;  // global time

    // DFS for SCC
    public static void dfs(List<List<Edge>> graph, int curr,
                           int[] dt, int[] low,
                           boolean[] inStack,
                           Stack<Integer> stack) {

        dt[curr] = low[curr] = ++time;
        stack.push(curr);
        inStack[curr] = true;

        for (Edge e : graph.get(curr)) {

            if (dt[e.dest] == 0) {
                dfs(graph, e.dest, dt, low, inStack, stack);
                low[curr] = Math.min(low[curr], low[e.dest]);
            } 
            else if (inStack[e.dest]) {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            }
        }

        // SCC root condition
        if (dt[curr] == low[curr]) {
            System.out.print("SCC : ");
            while (true) {
                int node = stack.pop();
                inStack[node] = false;
                System.out.print(node + " ");
                if (node == curr) break;
            }
            System.out.println();
        }
    }

    // Main SCC function
    public static void getSCC(List<List<Edge>> graph) {
        int V = graph.size();

        int[] dt = new int[V];
        int[] low = new int[V];
        boolean[] inStack = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        time = 0; // reset

        for (int i = 0; i < V; i++) {
            if (dt[i] == 0) {
                dfs(graph, i, dt, low, inStack, stack);
            }
        }
    }

    // Create Directed Graph
    public static void createGraph(List<List<Edge>> graph) {

        // Example graph
        graph.get(0).add(new Edge(0, 2));
        graph.get(2).add(new Edge(2, 1));
        graph.get(1).add(new Edge(1, 0));

        graph.get(0).add(new Edge(0, 3));
        graph.get(3).add(new Edge(3, 4));
    }

    public static void main(String[] args) {

        int V = 5;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        getSCC(graph);
    }
}
```

---



### ⚠️ Important Note (Java)

Passing `time` as an `int` is **pass-by-value**.
For correctness in real implementations, use:

* `static int time`, or
* `int[] time = {0}`


### ✅ Corrected Bridge in Graph (Tarjan’s Algorithm)

#### 🔧 Fix Applied

* `time` is made **static**
* Removed `time` from DFS parameters

```java
import java.util.*;

public class BridgeTarjan {

    static class Edge {
        int src, dest;

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    static int time = 0;

    // DFS
    public static void dfs(List<List<Edge>> graph, int curr, int par,
                           boolean[] vis, int[] dt, int[] low) {

        vis[curr] = true;
        dt[curr] = low[curr] = ++time;

        for (Edge e : graph.get(curr)) {

            if (e.dest == par)
                continue;

            if (vis[e.dest]) {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            } else {
                dfs(graph, e.dest, curr, vis, dt, low);
                low[curr] = Math.min(low[curr], low[e.dest]);

                // Bridge condition
                if (dt[curr] < low[e.dest]) {
                    System.out.println("BRIDGE : " + curr + " --- " + e.dest);
                }
            }
        }
    }

    // Main bridge function
    public static void getBridge(List<List<Edge>> graph) {
        int V = graph.size();

        int[] dt = new int[V];
        int[] low = new int[V];
        boolean[] vis = new boolean[V];

        time = 0;

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(graph, i, -1, vis, dt, low);
            }
        }
    }

    // Create Graph
    public static void createGraph(List<List<Edge>> graph) {

        // Example graph
        graph.get(0).add(new Edge(0, 1));
        graph.get(1).add(new Edge(1, 0));

        graph.get(1).add(new Edge(1, 2));
        graph.get(2).add(new Edge(2, 1));

        graph.get(1).add(new Edge(1, 3));
        graph.get(3).add(new Edge(3, 1));

        graph.get(3).add(new Edge(3, 4));
        graph.get(4).add(new Edge(4, 3));
    }

    public static void main(String[] args) {

        int V = 5;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        getBridge(graph);
    }
}
```

---

### ✅ Corrected Articulation Point in Graph (Tarjan’s Algorithm)

#### 🔧 Fix Applied

* `time` is **static**
* DFS no longer receives `time` as parameter

```java
import java.util.*;

public class ArticulationTarjan {

    static class Edge {
        int src, dest;

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    static int time = 0;

    // DFS
    public static void dfs(List<List<Edge>> graph, int curr, int par,
                           boolean[] vis, int[] dt, int[] low,
                           boolean[] isArticulation) {

        vis[curr] = true;
        dt[curr] = low[curr] = ++time;
        int child = 0;

        for (Edge e : graph.get(curr)) {

            if (e.dest == par)
                continue;

            if (vis[e.dest]) {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            } else {
                dfs(graph, e.dest, curr, vis, dt, low, isArticulation);
                low[curr] = Math.min(low[curr], low[e.dest]);

                if (dt[curr] <= low[e.dest] && par != -1) {
                    isArticulation[curr] = true;
                }
                child++;
            }
        }

        // Root case
        if (par == -1 && child > 1) {
            isArticulation[curr] = true;
        }
    }

    // Main articulation logic
    public static void getArticulation(List<List<Edge>> graph) {
        int V = graph.size();

        int[] dt = new int[V];
        int[] low = new int[V];
        boolean[] vis = new boolean[V];
        boolean[] isArticulation = new boolean[V];

        time = 0;

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(graph, i, -1, vis, dt, low, isArticulation);
            }
        }

        System.out.println("Articulation Points:");
        for (int i = 0; i < V; i++) {
            if (isArticulation[i]) {
                System.out.println(i);
            }
        }
    }

    // Create Graph
    public static void createGraph(List<List<Edge>> graph) {

        // Example graph
        graph.get(0).add(new Edge(0, 1));
        graph.get(1).add(new Edge(1, 0));

        graph.get(1).add(new Edge(1, 2));
        graph.get(2).add(new Edge(2, 1));

        graph.get(2).add(new Edge(2, 0));
        graph.get(0).add(new Edge(0, 2));

        graph.get(1).add(new Edge(1, 3));
        graph.get(3).add(new Edge(3, 1));

        graph.get(3).add(new Edge(3, 4));
        graph.get(4).add(new Edge(4, 3));
    }

    public static void main(String[] args) {

        int V = 5;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        createGraph(graph);

        getArticulation(graph);
    }
}

```

-> **Tarjan’s Algorithm - Can be used for below**

Strongly Connected Components (SCCs)

Bridges (undirected graph)

Articulation Points (undirected graph)

Detect cycles (via SCCs)

Build condensation graph


