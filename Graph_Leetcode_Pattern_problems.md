  
#### Example/Sample Leetcode problems

**HARD LEVEL**
````markdown
a) This problem is solved using Multi-Source BFS (Breadth First Search).

The idea:

-> Every rotten orange (2) spreads rot to adjacent fresh oranges (1) every minute.

-> Instead of starting BFS from one node, we start from all rotten oranges together.

-> Each BFS level = 1 minute.

**Step-by-Step Algorithm**


->**Add all rotten oranges into queue**
   
if (grid[i][j] == 2) {
    q.add(new Edge(i,j));
}


-> **Why?**

i)Because all rotten oranges start spreading simultaneously.

ii)So BFS starts from multiple sources.


-> **BFS Traversal**

i)while (!q.isEmpty())

ii)Each iteration of outer loop = 1 minute



-> **Level Order BFS**

i)int size = q.size();

ii)All oranges currently in queue rot neighbors simultaneously.

iii)So we process entire level together.
````

->  https://leetcode.com/problems/rotting-oranges/description/ 

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



Core Idea

This solution uses:

BFS + Cost Relaxation

instead of normal Dijkstra.

Why?

Because:

We must limit number of stops

BFS naturally processes level-by-level

Each BFS level = one extra stop



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

This problem uses:

Dijkstra’s Algorithm on a Grid

because:

Each movement has variable cost/time

and we need:

minimum time to reach bottom-right cell


---

Problem Understanding

moveTime[i][j] means:

You cannot ENTER cell (i,j)
before this time.

If you arrive earlier:

you must wait

Moving to adjacent cell always takes:

1 second


---

Core Idea

At every move:

nextTime =
max(currentTime, moveTime[nextCell]) + 1

Why?

Because:

if current time is smaller than allowed move time → wait

then spend 1 second to move



---

Pair Class

static class Pair implements Comparable<Pair>

Represents:

(x, y, minT)

where:

x,y → cell coordinates

minT → minimum time to reach this cell



---

Priority Queue

PriorityQueue<Pair> pq = new PriorityQueue<>();

Works as:

Min Heap

Smallest time processed first.

Exactly how Dijkstra works.


---

Distance Array

int dist[][] = new int[m][n];

Stores:

minimum time needed to reach each cell

Initially:

Integer.MAX_VALUE

because initially unreachable.


---

Visited Array

boolean vis[][] = new boolean[m][n];

Once shortest path to cell finalized:

mark visited

Same as Dijkstra.


---

Source Initialization

dist[0][0] = 0;
pq.add(new Pair(0, 0, 0));

Start from top-left cell at time 0.


---

Main Dijkstra Loop

while (!pq.isEmpty())

Continue until all reachable nodes processed.


---

Get Minimum Time Cell

Pair curr = pq.remove();

Priority queue guarantees:

smallest time cell comes first


---

Skip Already Finalized Cells

if (!vis[curr.x][curr.y])

Important optimization.

Once shortest path fixed:

no need to process again


---

Relaxation Process

For every direction:

Down

Up

Right

Left


we calculate:

minimum time to enter neighbor


---

Key Logic

Example:

if (dist[ux1][uy1] < moveTime[vx1][vy1]) {
    nT = moveTime[vx1][vy1] + 1;
}

Meaning:

We arrived too early.
Need to wait.

Suppose:

currentTime = 2
moveTime[next] = 5

Cannot enter at 2.

Wait till 5, then move:

time = 6


---

Otherwise

nT = dist[ux1][uy1] + 1;

If already late enough:

move immediately.


---

Relaxation Condition

if (!vis[vx][vy] && nT < dist[vx][vy])

If better path found:

update shortest time.


---

Update Distance

dist[vx][vy] = nT;

Store improved shortest time.


---

Push Into PQ

pq.add(new Pair(vx, vy, dist[vx][vy]));

So neighbor can further relax others.


---

Final Answer

return dist[m - 1][n - 1];

Minimum time to reach bottom-right.


---

Actual Formula Behind Code

Your repeated logic is basically:

\text{nextTime} = \max(\text{currentTime},\text{moveTime}[nx][ny]) + 1

This single formula represents entire waiting logic.


---

Why Dijkstra Works

Because:

edge cost is not fixed

waiting may happen

shortest path needed


Dijkstra handles:

weighted graphs with non-negative weights

Perfect fit.


---

Visualization Example

Suppose:

moveTime =
[
 [0,1],
 [4,2]
]

Start:

(0,0) at time 0


---

Move to (0,1)

Need:

max(0,1)+1 = 2

Reach at time 2.


---

Move to (1,1)

Current time:

2

Cell opens at:

2

So:

max(2,2)+1 = 3

Answer = 3.


---

Time Complexity

Grid has:

V = m*n cells

Each cell explores 4 directions.

Using priority queue:

O(V log V)

More precisely:

O((m \times n)\log(m \times n))


---

Space Complexity

O(m*n)

for:

distance array

visited array

priority queue



---

Important Observation

This is NOT BFS.

Why?

Because BFS only works when:

all edges have equal weight

Here effective movement cost changes because of waiting times.

So:

BFS fails
Dijkstra succeeds

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
b)

This problem is:

Find Closest Node to Given Two Nodes

Goal:

Find a node reachable from both:

node1
node2

such that:

max(distance from node1,
    distance from node2)

is minimized.


---

Important Graph Property

Given:

int[] edges

where:

edges[i] = j

means:

i -> j

Each node has at most:

1 outgoing edge

So graph is called:

Functional Graph


---

Graph Construction

Edge Class

static class Edge {
    int to;
    int weight;
}

Represents:

destination + edge weight

Though weight is always 1.


---

Build Adjacency List

List<List<Edge>> graph = new ArrayList<>();

Stores graph representation.


---

Create Edges

if (edges[i] != -1) {
    graph.get(i).add(
        new Edge(edges[i], 1)
    );
}

If:

edges[0] = 3

Then:

0 -> 3


---

Core Idea

We calculate:

distance from node1 to all nodes
distance from node2 to all nodes

Then choose node minimizing:

\max(dist1[i],dist2[i])

because both people should reach meeting node as fairly as possible.


---

Dijkstra Function

static int[] dijikstra(...)

Actually this behaves more like:

BFS on directed graph

because:

all edge weights are 1

no priority queue used



---

Distance Array

int[] dist = new int[n];

Stores shortest distance from source.

Initially:

Arrays.fill(dist, Integer.MAX_VALUE);

Meaning:

unreachable initially


---

Visited Array

boolean[] vis = new boolean[n];

Avoids revisiting finalized nodes.


---

Queue

Queue<int[]> pq = new LinkedList<>();

Stores:

(distance, node)


---

Source Initialization

dist[src] = 0;
pq.offer(new int[] { 0, src });

Start BFS from source node.


---

BFS Traversal

while (!pq.isEmpty())

Process nodes level-by-level.


---

Remove Current Node

int[] cur = pq.poll();

Get:

d = current distance
u = current node


---

Skip Already Visited

if (vis[u])
    continue;

Ensures node processed once.


---

Explore Neighbor

for (Edge e : graph.get(u))

Since each node has at most one outgoing edge:

loop runs at most once.


---

Relaxation

int newDist = d + 1;

Every edge costs 1.


---

Update Neighbor

if (!vis[v]) {
    dist[v] = newDist;
    pq.offer(new int[] { newDist, v });
}

Store shortest distance.


---

Two Distance Arrays

int[] dist1 = dijikstra(graph, node1);
int[] dist2 = dijikstra(graph, node2);

Now we know:

distance from node1 → every node
distance from node2 → every node


---

Find Closest Meeting Node

Loop through all nodes.

int k = Math.max(dist1[i], dist2[i]);

Meaning:

worst distance among the two persons

We minimize this value.


---

Why Use Maximum?

Suppose:

Node A:
dist1=1
dist2=100

Node B:
dist1=50
dist2=50

Meeting at B is better because:

max(50,50)=50
max(1,100)=100

We want balanced meeting point.


---

Final Answer

if (k < min)

Choose smallest maximum distance.

Store node index.


---

Small Example

edges = [2,2,3,-1]

0 -> 2
1 -> 2
2 -> 3

Suppose:

node1 = 0
node2 = 1


---

Distances From Node1

0 -> 0 = 0
0 -> 2 = 1
0 -> 3 = 2


---

Distances From Node2

1 -> 1 = 0
1 -> 2 = 1
1 -> 3 = 2


---

Candidate Nodes

Node 2

max(1,1)=1

Node 3

max(2,2)=2

Minimum is node 2.


---

Time Complexity

Each node visited once.

O(V)

Two traversals:

O(2V) = O(V)


---

Space Complexity

O(V)

for:

graph

queue

distance arrays

visited arrays



---

Important Bug in Your Code

You initialized:

Arrays.fill(dist, Integer.MAX_VALUE);

but later checked:

if (dist1[i] != -1 && dist2[i] != -1)

This is incorrect.

Unreachable nodes remain:

Integer.MAX_VALUE

not -1.

Correct condition should be:

if (dist1[i] != Integer.MAX_VALUE &&
    dist2[i] != Integer.MAX_VALUE)

Otherwise unreachable nodes may incorrectly participate.
 https://leetcode.com/problems/find-closest-node-to-given-two-nodes/

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
            if (dist1[i] != Integer.MAX_VALUE && dist2[i] != Integer.MAX_VALUE) {
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
