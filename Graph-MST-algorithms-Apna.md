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



####  Prim’s Algorithm- 👉 Link: [Prim’s Algorithm Source:](https://www.baeldung.com/cs/kruskals-vs-prims-algorithm)

Prim’s algorithm is similar in spirit to **Dijkstra’s algorithm**.  
Instead of edges, it grows the MST by always choosing the **next nearest node**.



##### **Optimization**
To improve time complexity:
- Ensure each node **appears only once** in the queue
- Use an `addOrUpdate` function:
  - If a node is already in the queue **and** the new edge weight is smaller then remove the old entry and insert the new one  
  - If it isn’t present then simply add it  



