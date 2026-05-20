
#### Example/Sample Leetcode problems

**HARD LEVEL**


a)

This problem is solved using Multi-Source BFS (Breadth First Search).

The idea:

Every rotten orange (2) spreads rot to adjacent fresh oranges (1) every minute.

Instead of starting BFS from one node, we start from all rotten oranges together.

Each BFS level = 1 minute.



---

Step-by-Step Algorithm

1. Add all rotten oranges into queue

if (grid[i][j] == 2) {
    q.add(new Edge(i,j));
}

Why?

Because all rotten oranges start spreading simultaneously.

So BFS starts from multiple sources.


---

2. Count fresh oranges

if (grid[i][j] == 1) {
    fresh++;
}

We need this because:

If no fresh oranges exist → answer is 0

If fresh oranges exist but no rotten orange exists → impossible → -1



---

Base Cases

No fresh oranges

if (fresh == 0)
    return 0;

Already all rotten.


---

Fresh oranges exist but no rotten orange

if (q.isEmpty())
    return -1;

No way to spread rot.


---

Direction Array

int[][] dirs = new int[4][2];

Represents:

Down  -> (1,0)
Up    -> (-1,0)
Left  -> (0,-1)
Right -> (0,1)

Used to visit 4 neighboring cells.


---

BFS Traversal

while (!q.isEmpty())

Each iteration of outer loop = 1 minute


---

Level Order BFS

int size = q.size();

All oranges currently in queue rot neighbors simultaneously.

So we process entire level together.


---

Process Current Rotten Orange

Edge e = q.remove();

Take one rotten orange.


---

Visit Neighbors

int i = x + dir[0];
int j = y + dir[1];

Generate adjacent cell coordinates.


---

Rot Fresh Orange

if ((i >= 0 && i < m) &&
    (j >= 0 && j < n) &&
    grid[i][j] == 1)

Checks:

inside grid

is fresh orange


Then:

grid[i][j] = 2;
fresh--;
q.add(new Edge(i,j));

Meaning:

orange becomes rotten

fresh count decreases

newly rotten orange added for next minute spreading



---

Minute Counter

min++;

After one BFS level completes, 1 minute has passed.


---

Why min = -1 initially?

Because:

Queue initially already contains rotten oranges at minute 0

First BFS level should represent minute 0


So after first level:

-1 + 1 = 0

This avoids extra counting.


---

Final Check

for (...)
    if (grid[i][j] == 1)
        return -1;

If any fresh orange still exists:

it was unreachable

answer is -1


Otherwise:

return min;


---

Time Complexity

Grid visited once.

If grid size is m × n:

Time  : O(m*n)
Space : O(m*n)

(queue can contain all cells)


---

Why BFS Works Here

Because BFS naturally processes nodes level-by-level.

Here:

1 BFS level = 1 minute

So BFS perfectly models spreading processes:

infection

fire spread

rotting

shortest distance in unweighted graph



---

Visualization Example

Grid:

2 1 1
1 1 0
0 1 1

Initially rotten:

(0,0)

Minute 0:

2 2 1
2 1 0
0 1 1

Minute 1:

2 2 2
2 2 0
0 1 1

Minute 2:

2 2 2
2 2 0
0 2 1

Minute 3:

2 2 2
2 2 0
0 2 2

Answer = 4 minutes total according to BFS level counting convention in this implementation.
 https://leetcode.com/problems/rotting-oranges/description/ 

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

This problem is:

Cheapest Flights Within K Stops

Goal:

Find the minimum flight cost from:

src → dst

with at most:

k stops


---

Core Idea

This solution uses:

BFS + Cost Relaxation

instead of normal Dijkstra.

Why?

Because:

We must limit number of stops

BFS naturally processes level-by-level

Each BFS level = one extra stop



---

Graph Construction

Edge Class

static class Edge {
    int dest;
    int wt;
}

Represents:

destination node
flight cost


---

Adjacency List

List<List<Edge>> lst= new ArrayList<>();

Stores graph.


---

Build Graph

for (int[] flight : flights) {
    lst.get(flight[0]).add(
        new Edge(flight[1], flight[2])
    );
}

If:

0 -> 1 cost 100

store:

0 : [(1,100)]


---

Queue for BFS

Queue<Edge> q = new LinkedList<>();

Queue stores:

(currentNode, currentCost)

Initially:

q.offer(new Edge(src, 0));

Meaning:

start from src with cost 0


---

Min Cost Array

int[] minCost = new int[n];
Arrays.fill(minCost, Integer.MAX_VALUE);

Purpose:

minCost[i] =
minimum price found so far to reach node i

Used to avoid unnecessary expensive paths.


---

Stops Variable

int stops = 0;

Tracks BFS level.

Important:

1 BFS level = 1 stop


---

Main BFS Loop

while (!q.isEmpty() && stops <= k)

Meaning:

process paths only up to k stops



---

Level Order Traversal

int size = q.size();

Processes one BFS level at a time.

All nodes currently in queue belong to same stop count.


---

Remove Current Node

Edge curr = q.remove();

Current:

curr.dest = current node
curr.wt   = current path cost


---

Explore Neighbors

for (Edge neighbour : lst.get(curr.dest))

Traverse all outgoing flights.


---

Relaxation Step

if (price + curr.wt < minCost[neighbourNode])

This is similar to Dijkstra/Bellman-Ford relaxation.

Meaning:

Did we find cheaper cost to reach neighbour?

If yes:

minCost[neighbourNode] = price + curr.wt;

Update cheapest cost.


---

Push into Queue

q.offer(new Edge(neighbourNode, minCost[neighbourNode]));

Now this node can further explore flights in next stop level.


---

Increment Stops

stops++;

After processing one BFS level:

one extra stop used


---

Final Answer

If destination unreachable:

if(minCost[dst]==Integer.MAX_VALUE)
    return -1;

Else:

return minCost[dst];


---

Why BFS Works Here

Normal BFS works for:

minimum edges

But here weights exist.

So solution combines:

BFS levels + relaxation

This behaves somewhat like:

BFS for stop control

Bellman-Ford for cost updates



---

Important Observation

We do NOT simply mark visited.

Because:

A node may be reached again with cheaper cost

Example:

0 -> 1 cost 500
0 -> 2 cost 100
2 -> 1 cost 100

Node 1 reached twice.

Second path cheaper.

So we use:

minCost[]

instead of visited array.


---

Example Walkthrough

Flights:

0 -> 1 : 100
1 -> 2 : 100
0 -> 2 : 500

Find:

src=0 dst=2 k=1


---

Initial

Queue:

[(0,0)]

minCost:

[INF, INF, INF]


---

Stop 0

Process node 0.

Neighbors:

1 cost 100
2 cost 500

Update:

minCost[1]=100
minCost[2]=500

Queue:

[(1,100), (2,500)]


---

Stop 1

Process node 1.

Neighbor:

2 cost 100

New cost:

100+100=200

Better than 500.

Update:

minCost[2]=200

Answer:

200


---

Time Complexity

Let:

V = nodes
E = flights

Worst case:

O(k * E)

because each stop level may process all edges.


---

Space Complexity

O(V + E)

for graph + queue + minCost.


---

Relation to Algorithms

This solution is closest to:

Bellman-Ford using BFS levels

because:

limited relaxations (k+1)

repeated cost improvements

weighted graph handling


But implemented efficiently using queue/BFS style traversal.


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
----

**MEDIUM LEVEL**

a) 

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
b) https://leetcode.com/problems/find-closest-node-to-given-two-nodes/

```java
class Solution {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int[] dijikstra(List<List<Edge>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<int[]> pq = new LinkedList<>();

        dist[src] = 0;
        pq.offer(new int[] { 0, src });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];

            if (vis[u])
                continue;
            vis[u] = true;

            for (Edge e : graph.get(u)) {
                int v = e.to;
                int newDist = d + 1;
                if (!vis[v]) {
                    dist[v] = newDist;
                    pq.offer(new int[] { newDist, v });
                }
            }
        }
        return dist;
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < edges.length; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != -1) {
                graph.get(i).add(new Edge(edges[i], 1));
            }
        }

        int[] dist1 = dijikstra(graph, node1);
        int[] dist2 = dijikstra(graph, node2);

        for (int i = 0; i < dist1.length; i++) {
            System.out.print(dist1[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < dist2.length; i++) {
            System.out.print(dist2[i] + " ");
        }

        int min = Integer.MAX_VALUE;
        int minidx = -1;
        for (int i = 0; i < dist1.length; i++) {
            if (dist1[i] != -1 && dist2[i] != -1) {
                int k = Math.max(dist1[i], dist2[i]);
                if (k < min) {
                    min = k;
                    minidx = i;
                }
            }
        }
        return minidx;
    }

}
```
