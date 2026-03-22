
#### Example/Sample Leetcode problems

**HIGH LEVEL**


a) https://leetcode.com/problems/rotting-oranges/description/ 

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
