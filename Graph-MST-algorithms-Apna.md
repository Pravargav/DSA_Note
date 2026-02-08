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
function primMST(graph) {
    const V = graph.length;              
    const key = new Array(V).fill(Number.MAX_VALUE);
    const parent = new Array(V).fill(null);
    const mstSet = new Array(V).fill(false);

    // Start from vertex 0
    key[0] = 0;
    parent[0] = -1; // first node is root

    // Build MST
    for (let i = 0; i < V - 1; i++) {
        
        // Pick the minimum key vertex not yet in MST
        let u = -1, min = Number.MAX_VALUE;
        for (let v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                u = v;
            }
        }

        mstSet[u] = true;

        // Update key and parent for neighbors
        for (let v = 0; v < V; v++) {
            if (graph[u][v] > 0 && !mstSet[v] && graph[u][v] < key[v]) {
                key[v] = graph[u][v];
                parent[v] = u;
            }
        }
    }

    // Print the constructed MST
    console.log("Edge \tWeight");
    for (let i = 1; i < V; i++) {
        console.log(parent[i] + " - " + i + " \t" +
                               graph[parent[i]][i]);
    }
}

// Driver Code
const graph = [
    [0, 2, 0, 6, 0],
    [2, 0, 3, 8, 5],
    [0, 3, 0, 0, 7],
    [6, 8, 0, 0, 9],
    [0, 5, 7, 9, 0]
];

primMST(graph);
```
-> Here in above code we do use parent array to store parent indices of each node.

-> In a tree a node can have multiple children but can have only single parent.

-> Therefore only single parent exists for each node.So that single parent id can be stored in the index.

-> If multiple parents are possible for a tree then we can't use parent array because each index can store only one value/parent.
