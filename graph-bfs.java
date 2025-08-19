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

//https://leetcode.com/problems/rotting-oranges/description/
