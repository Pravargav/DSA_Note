**scc1->scc2** - can happen.

**scc1->scc2 and scc2->scc1** - can never happen because if it's in both directions it will form a **scc12** as a whole.

**scc1->scc2, scc1->scc2, scc1->scc2** - possible beacause there can be any number of directed edges from one scc to another in a single direction.

[baldeung-kosaraju-scc](https://www.baeldung.com/cs/kosaraju-algorithm-scc)

-> ***In the first step, we perform a DFS traversal to define the priorities of the vertices. More specifically, for each vertex, we remember when DFS finishes processing. The later DFS is done with a vertex, the higher its priority. This step is similar to topological sorting, but it’s not the same since the latter is only possible for DAGs.***



