## kosaraju algorithm-

**scc1->scc2** - can happen.

**scc1->scc2 and scc2->scc1** - can never happen because if it's in both directions it will form a **scc12** as a whole.

**scc1->scc2, scc1->scc2, scc1->scc2** - possible beacause there can be any number of directed edges from one scc to another in a single direction.



-> [baldeung-kosaraju-scc](https://www.baeldung.com/cs/kosaraju-algorithm-scc)

###### Note: In the first step, we perform a DFS traversal to define the priorities of the vertices. More specifically, for each vertex, we remember when DFS finishes processing. The later DFS is done with a vertex, the higher its priority. ***This step is similar to topological sorting, but it’s not the same since the latter is only possible for DAGs.*** 


-> This is a very common confusion.

You are right that topological sorting is defined only for DAGs,
but in Kosaraju’s algorithm we are NOT doing topological sort.

What we do is:

> DFS + stack based on finishing time,
which looks similar to topo sort but is NOT topo sort.



So the fix is mostly conceptual + small code cleanup.




🔴 What is wrong in your code

1. Function name topSort() is misleading


2. Some loops are broken / incomplete


3. Missing transpose graph creation


4. Stack logic is correct, but purpose is wrong






✅ Correct Concept (Important for exams & interviews)

Kosaraju does NOT do topological sort

It does: DFS

Push node to stack after DFS finishes
-> called finishing time order


This works even if the graph has cycles.




✅ Corrected & Clean Kosaraju Code (Java)

-> Proper naming + correct logic

```java
import java.util.*;

public class Kosaraju {

    static class Edge {
        int src, dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Create Graph
    public static List<List<Edge>> createGraph(int V) {
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(0, 2));
        graph.get(0).add(new Edge(0, 3));
        graph.get(1).add(new Edge(1, 0));
        graph.get(2).add(new Edge(2, 1));
        graph.get(3).add(new Edge(3, 4));

        return graph;
    }

    // Step 1: DFS for finishing times
    public static void dfsFinishTime(
            List<List<Edge>> graph,
            int curr,
            boolean[] vis,
            Stack<Integer> stack) {

        vis[curr] = true;

        for (Edge e : graph.get(curr)) {
            if (!vis[e.dest]) {
                dfsFinishTime(graph, e.dest, vis, stack);
            }
        }

        stack.push(curr);
    }

    // Step 3: DFS on transpose graph
    public static void dfsTranspose(
            List<List<Edge>> transpose,
            int curr,
            boolean[] vis) {

        vis[curr] = true;
        System.out.print(curr + " ");

        for (Edge e : transpose.get(curr)) {
            if (!vis[e.dest]) {
                dfsTranspose(transpose, e.dest, vis);
            }
        }
    }

    public static void kosaraju(List<List<Edge>> graph, int V) {

        // Step 1: Fill stack by finishing times
        Stack<Integer> stack = new Stack<>();
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfsFinishTime(graph, i, vis, stack);
            }
        }

        // Step 2: Build transpose graph
        List<List<Edge>> transpose = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            transpose.add(new ArrayList<>());
            vis[i] = false;
        }

        for (int i = 0; i < V; i++) {
            for (Edge e : graph.get(i)) {
                transpose.get(e.dest).add(new Edge(e.dest, e.src));
            }
        }

        // Step 3: Process vertices in stack order
        while (!stack.isEmpty()) {
            int curr = stack.pop();

            if (!vis[curr]) {
                dfsTranspose(transpose, curr, vis);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;

        List<List<Edge>> graph = createGraph(V);

        kosaraju(graph, V);
    }
}
```


-> Key Interview Line

> Kosaraju does NOT use topological sorting.
It uses DFS finishing time order, which is valid even for cyclic graphs.






⚠ Why calling it Top Sort is dangerous

Top sort ❌ requires DAG

Kosaraju ✅ works on cyclic graphs

Naming it topSort() causes conceptual penalty in exams


-> Correct name:

dfsFinishTime,
fillOrder,
dfsStackOrder
