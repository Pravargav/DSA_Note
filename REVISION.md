```java
//REVISION
public static void levelOrder(Node root) {
    if (root == null) return;

    Queue<Node> q = new LinkedList<>();
    q.add(root);
    q.add(null);

    while (!q.isEmpty()) {
        Node curr = q.remove();

        if (curr == null) {
            System.out.println();
            if (q.isEmpty()){
               break;
             }
            q.add(null);
        } else {
            System.out.print(curr.data + " ");
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
    }
}
```

```java
    static class Edge {
        int src;
        int dest;
        int wt;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }
    static void bfs(List<List<Edge>> graph) {

        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();


        visited[0] = true;
        q.add(0);
        while (!q.isEmpty()) {

            int curr = q.remove();
            System.out.print(curr + " ");

            for (Edge e : graph.get(curr)) {
                if (!visited[e.dest]) {
                    visited[e.dest] = true;
                    q.add(e.dest);
                }
            }
        }

        System.out.println();
    }


public static int[] bellmanFord(int V, List<Edge> edges, int src) {

    int[] dist = new int[V];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;

    // Relax edges V-1 times
    for (int i = 1; i <= V - 1; i++) {
        for (Edge e : edges) {
            if (dist[e.src] != Integer.MAX_VALUE &&
                dist[e.src] + e.wt < dist[e.dest]) {

                dist[e.dest] = dist[e.src] + e.wt;
            }
        }
    }

    // Detect negative weight cycle
    for (Edge e : edges) {
        if (dist[e.src] != Integer.MAX_VALUE &&
            dist[e.src] + e.wt < dist[e.dest]) {

            System.out.println("Negative weight cycle detected!");
            return null;
        }
    }

    return dist;
}
```


```java
    static class Edge {
        int dest, wt;

        Edge(int dest, int wt) {
            this.dest = dest;
            this.wt = wt;
        }
    }

    static class Pair implements Comparable<Pair> {
        int node, dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        public int compareTo(Pair p) {
            return this.dist - p.dist;
        }
    }

    public static int[] dijkstra(List<List<Edge>> graph, int src) {

        int V = graph.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        dist[src] = 0;
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();
            int u = curr.node;
            int w = curr.dist;

            int oldDist = dist[u];

            // Skip outdated entry
            if (w > oldDist) continue;

            for (Edge e : graph.get(u)) {
                int v = e.dest;
                int newDist = oldDist + e.wt;
 
                if (oldDist + e.wt < dist[v]) {
                    dist[v] = oldDist + e.wt;
                    //we only add relaxed nodes in priority queue
                    //unlike bfs which only adds unvisited nodes
                    //bfs uses queue but dijikstras use priority queue
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        return dist;
    }

    static int spanningTree(int V, List<List<Edge>> graph) {
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    boolean[] vis = new boolean[V];

    pq.add(new Pair(0, 0)); // (node, dist)
    int sum = 0;

    while (!pq.isEmpty()) {
        Pair curr = pq.poll();

        int node = curr.node;
        int wt = curr.dist;

        if (vis[node]) continue;

        vis[node] = true;
        sum += wt;

        for (Edge e : graph.get(node)) {
            if (!vis[e.dest]) {
                pq.add(new Pair(e.dest, e.wt));
            }
        }
    }
    return sum;
}


```
```java

List<List<Edge>> graph = new ArrayList<>();


for (int i = 0; i < V; i++) {
    graph.add(new ArrayList<>());

graph.get(0).add(new Edge(0, 1, 5));

```
