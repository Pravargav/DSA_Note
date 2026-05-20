  
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
````markdown
-> Suppose currently we are at:
(ux1 , uy1)
at time:
Java
dist[ux1][uy1]

-> Now we want to go DOWN.
So next cell is:
Java
(vx1 , vy1)

-> **Why this check?**

if (dist[ux1][uy1] < moveTime[vx1][vy1])
Meaning:
"We reached too early."
Because:
moveTime[vx1][vy1]
means:
-> You CANNOT enter that cell before this time.
Example:
Current time:
2
Next cell open time:
5
You reached early.
So you must WAIT.
Therefore
nT = moveTime[vx1][vy1] + 1;

-> Why +1?
Because:
After waiting till time 5, moving into the cell still takes:
1 second
So:
wait till 5
move -> 6

-> **Else condition**

else if (dist[ux1][uy1] >= moveTime[vx1][vy1])
Meaning:
The cell is already open when we arrive.
So no waiting needed.
Just move normally.
Therefore
Java
nT = dist[ux1][uy1] + 1;
Meaning:
current time + movement cost

->**Final Relaxation Step**

Java
if (!vis[vx1][vy1] && nT < dist[vx1][vy1])
Same as standard Dijkstra.

->**Meaning:**

If we found a shorter time to reach this cell:
update distance
push into priority queue
````
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
````markdown
-> **This problem is:**

Find Closest Node to Given Two Nodes

-> **Goal:**

Find a node reachable from both:

node1
node2

such that:

max(distance from node1,
    distance from node2)

is minimized.



-> **Core Idea:**

We calculate:

distance from node1 to all nodes
distance from node2 to all nodes

Then choose node minimizing:

max(dist1[i],dist2[i])

because both people should reach meeting node as fairly as possible.

-> **Dijkstra Function**

static int[] dijikstra(...)

-> **Actually this behaves more like:**

BFS on directed graph

because:

all edge weights are 1

no priority queue used

-> **Explore Neighbor**

for (Edge e : graph.get(u))

Since each node has at most one outgoing edge:

loop runs at most once.

-> **Relaxation**

int newDist = d + 1;

Every edge costs 1.

-> **Update Neighbor**

if (!vis[v]) {
    dist[v] = newDist;
    pq.offer(new int[] { newDist, v });
}

Store shortest distance.

-> **Two Distance Arrays**

int[] dist1 = dijikstra(graph, node1);
int[] dist2 = dijikstra(graph, node2);

Now we know:

distance from node1 → every node
distance from node2 → every node

-> **Find Closest Meeting Node**

Loop through all nodes.

int k = Math.max(dist1[i], dist2[i]);

-> **Meaning:**

worst distance among the two persons

We minimize this value.


-> **Why Use Maximum?**

i) Suppose:

Node A:
dist1=1
dist2=100

Node B:
dist1=50
dist2=50

ii)Meeting at B is better because:

max(50,50)=50
max(1,100)=100

We want balanced meeting point.
````


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
