https://youtube.com/playlist?list=PLM68oyaqFM7QsfZJ8W2YHgiA2EDp3FryV&si=LTEcZVE1UBx4uWq3
--------------------------------------------------------------
**1)floydd warshall**

```java
class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class FloydWarshall {
    static final int INF = (int) 1e9;

    public static int[][] floydWarshall(List<List<Edge>> graph, int V) {
        int[][] dist = new int[V][V];

        // Step 1: Initialize the distance matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        // Step 2: Fill distances from graph edges
        for (int u = 0; u < V; u++) {
            for (Edge edge : graph.get(u)) {
                dist[u][edge.to] = edge.weight;
            }
        }

        // Step 3: Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        // Example edges
        graph.get(0).add(new Edge(1, 5));
        graph.get(0).add(new Edge(3, 10));
        graph.get(1).add(new Edge(2, 3));
        graph.get(2).add(new Edge(3, 1));

        int[][] result = floydWarshall(graph, V);

        // Print the result
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (result[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
```

**2)Smallest Sum Contiguos Array(practice)**

**3)Stictly Increasing Array(practice)**

----------------------------------------------------------

**1)Count ways To Reach Nth Stair(practice)**

**2)Stickler Thief(practice)**

----------------------------------------------------------

**1)Maximum sum subarray(practice)**

**2)Maximum product subarray(practice)**

**3)Trapping water(practice)**

----------------------------------------------------------

**Matrix Chain Multiplication concept**

----------------------------------------------------------

**1)Longest Arthimatic Progression(practice)**

**2)Largest Square found in matrix(practice)**

----------------------------------------------------------

**1)Diameter of Tree(Trees)**

**2)Maximum path sum between two leaf nodes(Trees)**

----------------------------------------------------------

**1)Number of Paths(Grid)**

**2)Maximum path Sum(Grid)**

----------------------------------------------------------

**1)Only one transation(Stocks)**

**2)Infinite Transactions(Stocks)**

**3)At most two transactions(Stocks)**

**4)At most k transactions(Stocks)**

----------------------------------------------------------

**1)Longest Common Subsequence(String/Sequence)**

**2)Longest Common Substring(String/Sequence)**

**3)Longest Increasing Subsequence(String/Sequence)**

----------------------------------------------------------

**1)0-1 knapsack bounded(Knapsack)**
```java

import java.util.*;

class TUF {
    // Helper function to solve the knapsack problem recursively
    static int knapsackUtil(int[] wt, int[] val, int ind, int W, int[][] dp) {
        // Base case: If there are no items or the knapsack capacity is zero
        if (ind == 0) {
            if (wt[0] <= W) {
                // Include the item if its weight is within the capacity
                return val[0];
            } else {
                // Otherwise, exclude the item
                return 0;
            }
        }

        // If the result for this subproblem is already calculated, return it
        if (dp[ind][W] != -1) {
            return dp[ind][W];
        }

        // Calculate the maximum value when the current item is not taken
        int notTaken = 0 + knapsackUtil(wt, val, ind - 1, W, dp);

        // Calculate the maximum value when the current item is taken
        int taken = Integer.MIN_VALUE;
        if (wt[ind] <= W) {
            taken = val[ind] + knapsackUtil(wt, val, ind - 1, W - wt[ind], dp);
        }

        // Store and return the result for the current state
        dp[ind][W] = Math.max(notTaken, taken);
        return dp[ind][W];
    }

    // Function to solve the 0/1 Knapsack problem using dynamic programming
    static int knapsack(int[] wt, int[] val, int n, int W) {
        // Create a 2D DP array to store the maximum value for each subproblem
        int dp[][] = new int[n][W + 1];

        // Initialize the DP array with -1 to indicate that subproblems are not solved
        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }

        // Call the recursive knapsackUtil function to solve the problem
        return knapsackUtil(wt, val, n - 1, W, dp);
    }

    public static void main(String args[]) {
        int wt[] = {1, 2, 4, 5};
        int val[] = {5, 4, 8, 6};
        int W = 5;
        int n = wt.length;

        // Calculate and print the maximum value of items the thief can steal
        System.out.println("The Maximum value of items the thief can steal is " + knapsack(wt, val, n, W));
    }
}

```
**2)is Subset Sum(Knapsack)**
```java

import java.util.*;

class TUF {
    // Helper function to solve subset sum problem using dynamic programming
    static boolean subsetSumUtil(int ind, int target, int[] arr, int[][] dp) {
        // If the target sum is achieved, return true
        if (target == 0)
            return true;

        // If we have considered all elements but haven't reached the target, return false
        if (ind == 0)
            return arr[0] == target;

        // If the result for this subproblem has already been calculated, return it
        if (dp[ind][target] != -1)
            return dp[ind][target] == 0 ? false : true;

        // Try not taking the current element
        boolean notTaken = subsetSumUtil(ind - 1, target, arr, dp);

        // Try taking the current element if it doesn't exceed the target
        boolean taken = false;
        if (arr[ind] <= target)
            taken = subsetSumUtil(ind - 1, target - arr[ind], arr, dp);

        // Store the result in the DP table and return whether either option was successful
        dp[ind][target] = notTaken || taken ? 1 : 0;
        return notTaken || taken;
    }

    // Main function to check if there exists a subset with a given target sum
    static boolean subsetSumToK(int n, int k, int[] arr) {
        // Create a DP table with dimensions [n][k+1]
        int dp[][] = new int[n][k + 1];

        // Initialize DP table with -1 (unprocessed)
        for (int row[] : dp)
            Arrays.fill(row, -1);

        // Call the recursive helper function
        return subsetSumUtil(n - 1, k, arr, dp);
    }

    public static void main(String args[]) {
        int arr[] = { 1, 2, 3, 4 };
        int k = 4;
        int n = arr.length;

        // Check if there exists a subset with the given target sum
        if (subsetSumToK(n, k, arr))
            System.out.println("Subset with the given target found");
        else
            System.out.println("Subset with the given target not found");
    }
}

```
----------------------------------------------------------
