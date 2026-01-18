-> A condensation graph is a DAG (Directed Acyclic Graph) formed by collapsing each Strongly Connected Component (SCC) of a directed graph into a single node.

Each SCC → one node in condensation graph

Original edges between SCCs → edges between nodes

Resulting graph is always a DAG, even if the original graph had cycles


-> A strongly connected component (SCC) can contain overlapping or nested cycles, paths that are not part of simple cycles, or a combination of both.( Chatgpt and DeepSeek)
[visit this article](https://cp-algorithms.com/graph/strongly-connected-components.html)(Can be solved using Kosaraju's algorithm and Tarjan's algorithm)

![alt text](https://cp-algorithms.com/graph/strongly-connected-components-tikzpicture/graph.svg)

![alt text](https://cp-algorithms.com/graph/strongly-connected-components-tikzpicture/cond_graph.svg)



[youtube-willam-heist](https://youtu.be/wUgWX0nc4NY?si=rj0aLhlNHrLHv95C)

---

## ❌ Statement 1 (Incorrect / Misleading)

> **“The low-link value of a node is the smallest node ID reachable from that node when doing a DFS, including itself.”**

🔴 **This is NOT strictly correct.**

### Why?

* **Low-link is NOT about node ID**
* It is about **DFS discovery time**

### ✅ Correct version

> **The low-link value of a node is the minimum *DFS discovery time* reachable from that node (including itself), using tree edges and back edges to nodes still on the stack.**

📌 Node IDs are arbitrary labels.
📌 Discovery time represents DFS order (ancestor relationship).

---

### ⚠️ Why TopCoder says “node ID”

Many TopCoder problems **label nodes in DFS order (0,1,2,...)**, so:

```
node ID == discovery time
```

➡️ In **that special context**, their statement *appears* correct.
➡️ In **general graph theory**, it is **wrong wording**.

---

## ⚠️ Statement 2 (Conceptually correct, wording sloppy)

> **“There is a catch with doing a DFS on the graph, as it is highly dependent on the traversal order of the DFS, which is effectively random.”**

🟡 **Idea is correct, wording is loose**

### Clarification:

* DFS order **depends on adjacency list order**
* It is **deterministic**, not random
* But **can vary between implementations**

✔ The important idea:

> SCC detection must be **independent of DFS order**

Tarjan’s algorithm guarantees that.

---

## ✅ Statement 3 (Correct and Important)

> **“To cope with the random traversal order of the DFS, Tarjan’s algorithm maintains a stack of valid nodes from which to update low-link values.”**

✔ **Correct idea**

The stack ensures:

* We only consider **active DFS path nodes**
* Prevents cross-SCC contamination
* Ensures correctness regardless of DFS order

---

## ✅ Statement 4 (Correct)

> **“Nodes are added to the stack of valid nodes as they are explored for the first time. Nodes are removed from the stack each time a complete SCC is found.”**

✔ **100% correct**

This is the **core invariant** of Tarjan’s algorithm.

---

## 🧠 The Precise Version

Here is the **correct technical formulation** replacing TopCoder’s wording:

> The low-link value of a node `u` is the minimum DFS discovery time reachable from `u`, including itself, by following DFS tree edges and at most one back edge to a node currently on the stack. The stack is used to track active nodes in the current DFS path, and nodes are removed from the stack only when an SCC is fully identified.

---
-> Striver and Apna college assigned node-id's intelligently so to exactly match time with node-id's.But both are not same.Refer to striver's graph diagram and Apna graph diagram in youtube [striver-ex](https://takeuforward.org/graph/bridges-in-graph-using-tarjans-algorithm-of-time-in-and-low-time-g-55).


You are **100% correct** in your analysis 👍
Your **bug identification and explanation are accurate**, and your conclusion about the fix is exactly right.

Let me give a **clear final verdict**, then show the **corrected code**.

---
```java

import java.util.*;

public class TarjanSCC {

    static class Edge {
        int src, dest;
        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    public static void dfs(ArrayList<Edge> graph[], int curr,
                           int dt[], int low[],
                           boolean inStack[],
                           Stack<Integer> stack,
                           int time) {

        dt[curr] = low[curr] = ++time;
        stack.push(curr);
        inStack[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

            // If node not visited
            if (dt[e.dest] == 0) {
                dfs(graph, e.dest, dt, low, inStack, stack, time);
                low[curr] = Math.min(low[curr], low[e.dest]);
            }
            // Back edge to node in stack
            else if (inStack[e.dest]) {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            }
        }

        // Head of SCC
        if (dt[curr] == low[curr]) {
            System.out.print("SCC : ");
            while (true) {
                int node = stack.pop();
                inStack[node] = false;
                System.out.print(node + " ");
                if (node == curr)
                    break;
            }
            System.out.println();
        }
    }

    public static void getSCC(ArrayList<Edge> graph[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        boolean inStack[] = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        int time = 0;

        for (int i = 0; i < V; i++) {
            if (dt[i] == 0) {
                dfs(graph, i, dt, low, inStack, stack, time);
            }
        }
    }
}
```
## ❌ Is the given code correct?

**No — as written, it is NOT correct.**

### ❗ Reason: Java pass-by-value for `int time`

```java
public static void dfs(..., int time)
```

* Each recursive call receives **its own copy** of `time`
* `++time` only updates the local copy
* Parent calls do **not see the increment**
* Multiple nodes receive the **same discovery time (`dt`)**
* This **breaks Tarjan’s SCC logic**

✔ Your explanation of the bug is **exactly right**

---

## ✅ Correct Fixes (Two Valid Options)

---

## ✔️ FIX 1 (BEST & CLEANEST): Use `static int time`

### ✅ Corrected Working Code

```java
import java.util.*;

public class TarjanSCC {

    static class Edge {
        int src, dest;
        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static int time = 0;  // ✅ global time

    public static void dfs(ArrayList<Edge> graph[], int curr,
                           int dt[], int low[],
                           boolean inStack[],
                           Stack<Integer> stack) {

        dt[curr] = low[curr] = ++time;
        stack.push(curr);
        inStack[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

            if (dt[e.dest] == 0) {
                dfs(graph, e.dest, dt, low, inStack, stack);
                low[curr] = Math.min(low[curr], low[e.dest]);
            } 
            else if (inStack[e.dest]) {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            }
        }

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

    public static void getSCC(ArrayList<Edge> graph[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        boolean inStack[] = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        time = 0; // reset before DFS

        for (int i = 0; i < V; i++) {
            if (dt[i] == 0) {
                dfs(graph, i, dt, low, inStack, stack);
            }
        }
    }
}
```

---

## ✔️ FIX 2: Use `int[] time` (Reference Style)

```java
int time[] = {0};

dfs(..., time);

// inside dfs
dt[curr] = low[curr] = ++time[0];
```

✔ Works
❌ Slightly less clean for interviews/exams

---


### ✅ Java Code

```java
public static void dfs(ArrayList<Edge> graph[], int curr, int par,
                       boolean vis[], int dt[], int low[], int time) {

    vis[curr] = true;
    dt[curr] = low[curr] = ++time;

    for (int i = 0; i < graph[curr].size(); i++) {
        Edge e = graph[curr].get(i);

        if (e.dest == par)
            continue;

        if (vis[e.dest]) {
            low[curr] = Math.min(low[curr], dt[e.dest]);
        } else {
            dfs(graph, e.dest, curr, vis, dt, low, time);
            low[curr] = Math.min(low[curr], low[e.dest]);

            if (dt[curr] < low[e.dest]) {
                System.out.println("BRIDGE : " + curr + " --- " + e.dest);
            }
        }
    }
}

public static void getBridge(ArrayList<Edge> graph[], int V) {
    int dt[] = new int[V];
    int low[] = new int[V];
    boolean vis[] = new boolean[V];
    int time = 0;

    for (int i = 0; i < V; i++) {
        if (!vis[i]) {
            dfs(graph, i, -1, vis, dt, low, time);
        }
    }
}
```

```java
public static void dfs(ArrayList<Edge> graph[], int curr, int par,
                       boolean vis[], int dt[], int low[], int time,
                       boolean isArticulation[]) {

    vis[curr] = true;
    dt[curr] = low[curr] = ++time;
    int child = 0;

    for (int i = 0; i < graph[curr].size(); i++) {
        Edge e = graph[curr].get(i);

        if (e.dest == par)
            continue;

        if (vis[e.dest]) {
            low[curr] = Math.min(low[curr], dt[e.dest]);
        } else {
            dfs(graph, e.dest, curr, vis, dt, low, time, isArticulation);
            low[curr] = Math.min(low[curr], low[e.dest]);

            if (dt[curr] <= low[e.dest] && par != -1) {
                isArticulation[curr] = true;
            }
            child++;
        }
    }

    if (par == -1 && child > 1) {
        isArticulation[curr] = true;
    }
}

public static void getArticulation(ArrayList<Edge> graph[], int V) {
    int dt[] = new int[V];
    int low[] = new int[V];
    boolean vis[] = new boolean[V];
    boolean isArticulation[] = new boolean[V];
    int time = 0;

    for (int i = 0; i < V; i++) {
        if (!vis[i]) {
            dfs(graph, i, -1, vis, dt, low, time, isArticulation);
        }
    }

    for (int i = 0; i < V; i++) {
        if (isArticulation[i]) {
            System.out.println(i);
        }
    }
}
```


## ⚠️ Important Note (Java)

Passing `time` as an `int` is **pass-by-value**.
For correctness in real implementations, use:

* `static int time`, or
* `int[] time = {0}`

---

Below are the **corrected, fully working Java implementations** of **Bridge** and **Articulation Point** using **Tarjan’s Algorithm**, fixing the **pass-by-value `time` bug** by using a **static variable**.

These are **exam-safe, interview-safe, and production-correct**.

---

## ✅ Corrected Bridge in Graph (Tarjan’s Algorithm)

### 🔧 Fix Applied

* `time` is made **static**
* Removed `time` from DFS parameters

```java
import java.util.*;

public class BridgeTarjan {

    static class Edge {
        int src, dest;
        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static int time = 0; // ✅ FIX

    public static void dfs(ArrayList<Edge> graph[], int curr, int par,
                           boolean vis[], int dt[], int low[]) {

        vis[curr] = true;
        dt[curr] = low[curr] = ++time;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

            if (e.dest == par)
                continue;

            if (vis[e.dest]) {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            } else {
                dfs(graph, e.dest, curr, vis, dt, low);
                low[curr] = Math.min(low[curr], low[e.dest]);

                if (dt[curr] < low[e.dest]) {
                    System.out.println("BRIDGE : " + curr + " --- " + e.dest);
                }
            }
        }
    }

    public static void getBridge(ArrayList<Edge> graph[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        boolean vis[] = new boolean[V];

        time = 0; // reset

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(graph, i, -1, vis, dt, low);
            }
        }
    }
}
```

---

## ✅ Corrected Articulation Point in Graph (Tarjan’s Algorithm)

### 🔧 Fix Applied

* `time` is **static**
* DFS no longer receives `time` as parameter

```java
import java.util.*;

public class ArticulationTarjan {

    static class Edge {
        int src, dest;
        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static int time = 0; // ✅ FIX

    public static void dfs(ArrayList<Edge> graph[], int curr, int par,
                           boolean vis[], int dt[], int low[],
                           boolean isArticulation[]) {

        vis[curr] = true;
        dt[curr] = low[curr] = ++time;
        int child = 0;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

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

        if (par == -1 && child > 1) {
            isArticulation[curr] = true;
        }
    }

    public static void getArticulation(ArrayList<Edge> graph[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        boolean vis[] = new boolean[V];
        boolean isArticulation[] = new boolean[V];

        time = 0; // reset

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(graph, i, -1, vis, dt, low, isArticulation);
            }
        }

        for (int i = 0; i < V; i++) {
            if (isArticulation[i]) {
                System.out.println(i);
            }
        }
    }
}
```

---





