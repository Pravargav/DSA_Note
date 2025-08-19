getAns(0, 0) [Start]
├── getAns(1, 0) [Skip buying]
│   ├── getAns(2, 0) [Skip buying]
│   │   ├── getAns(3, 0) [Skip buying]
│   │   │   ├── getAns(4, 0) = 0 [Base Case]
│   │   │   └── getAns(4, 1) = -5 [Base Case]
│   │   │   Result = max(0, -5) = 0 return Result;
│   │   └── getAns(3, 1) [Buy at 6]
│   │       ├── getAns(4, 1) = 0 [Base Case]
│   │       └── getAns(4, 0) = 5 [Sell at 5]
│   │       Result = max(0, 5) = 5 return Result;
│   │   Result = max(0, -6 + 5) = 0 return Result;
│   └── getAns(2, 1) [Buy at 2]
│       ├── getAns(3, 1) [Skip selling]
│       │   ├── getAns(4, 1) = 0 [Base Case]
│       │   └── getAns(4, 0) = 5 [Sell at 5]
│       │   Result = max(0, 5) = 5 return Result;
│       └── getAns(3, 0) [Sell at 6]
│           ├── getAns(4, 0) = 0 [Base Case]
│           └── getAns(4, 1) = -5 [Base Case]
│           Result = max(0, -5) = 0 return Result;
│       Result = max(5, 6 + 0) = 6 return Result;
│   Result = max(0, -2 + 6) = 4 return Result;
├── getAns(1, 1) [Buy at 3]
│   ├── getAns(2, 1) [Skip selling]
│   │   ├── getAns(3, 1) [Skip selling]
│   │   │   ├── getAns(4, 1) = 0 [Base Case]
│   │   │   └── getAns(4, 0) = 5 [Sell at 5]
│   │   │   Result = max(0, 5) = 5 return Result;
│   │   └── getAns(3, 0) [Sell at 6]
│   │       ├── getAns(4, 0) = 0 [Base Case]
│   │       └── getAns(4, 1) = -5 [Base Case]
│   │       Result = max(0, -5) = 0 return Result;
│   │   Result = max(5, 6 + 0) = 6 return Result;
│   └── getAns(2, 0) [Sell at 2]
│       ├── getAns(3, 0) [Skip buying]
│       │   ├── getAns(4, 0) = 0 [Base Case]
│       │   └── getAns(4, 1) = -5 [Base Case]
│       │   Result = max(0, -5) = 0 return Result;
│       └── getAns(3, 1) [Buy at 6]
│           ├── getAns(4, 1) = 0 [Base Case]
│           └── getAns(4, 0) = 5 [Sell at 5]
│           Result = max(0, 5) = 5  return Result;
│       Result = max(0, -6 + 5) = 0  return Result;
│   Result = max(6, 2 + 0) = 6  return Result;
Result = max(4, -3 + 6) = 6 return Result;


long getAns(long *Arr, int ind, int buy, int n, vector<vector<long>> &dp) {
   
    if (ind == n) {
        return 0;
    }

  
   

    long Result = 0;

    if (buy == 0) { 
        Result = max(0 + getAns(Arr, ind + 1, 0, n, dp), -Arr[ind] + getAns(Arr, ind + 1, 1, n, dp));
    }

    if (buy == 1) { 
        Result = max(0 + getAns(Arr, ind + 1, 1, n, dp), Arr[ind] + getAns(Arr, ind + 1, 0, n, dp));
    }


    return  Result;
}

//taking 2 arrays for profit ie profit1 and profit2 is equivalent to taking a 2d dynamic array of [n][2]
//for k transactions allowed we take [n][k]
//profit1+ profit2=> dp[n][2] that's it
//my personal reference note on gFG solution for stock problems

--------------------------------------------------------------------------------------------------------------------------


f(1, 3)
├── Split at ind=1 (cut at 1): cost = cuts[4] - cuts[0] + f(1, 0) + f(2, 3)
│   ├── f(1, 0): Base case → 0
│   └── f(2, 3)
│       ├── Split at ind=2 (cut at 3): cost = cuts[4] - cuts[1] + f(2, 1) + f(3, 3)
│       │   ├── f(2, 1): Base case → 0
│       │   └── f(3, 3)
│       │       ├── Split at ind=3 (cut at 4): cost = cuts[4] - cuts[2] + f(3, 2) + f(4, 3)
│       │       │   ├── f(3, 2): Base case → 0
│       │       │   └── f(4, 3): Base case → 0
│       │       └── Result: f(3, 3) = 4
│       └── Split at ind=3 (cut at 4): cost = cuts[4] - cuts[2] + f(2, 2) + f(4, 3)
│           ├── f(2, 2)
│           │   ├── Split at ind=2 (cut at 3): cost = cuts[3] - cuts[1] + f(2, 1) + f(3, 2)
│           │   │   ├── f(2, 1): Base case → 0
│           │   │   └── f(3, 2): Base case → 0
│           │   └── Result: f(2, 2) = 4
│           └── f(4, 3): Base case → 0
│       └── Result: f(2, 3) = 8
├── Split at ind=2 (cut at 3): cost = cuts[4] - cuts[0] + f(1, 1) + f(3, 3)
│   ├── f(1, 1)
│   │   ├── Split at ind=1 (cut at 1): cost = cuts[2] - cuts[0] + f(1, 0) + f(2, 1)
│   │   │   ├── f(1, 0): Base case → 0
│   │   │   └── f(2, 1): Base case → 0
│   │   └── Result: f(1, 1) = 4
│   └── f(3, 3)
│       ├── Split at ind=3 (cut at 4): cost = cuts[4] - cuts[2] + f(3, 2) + f(4, 3)
│       │   ├── f(3, 2): Base case → 0
│       │   └── f(4, 3): Base case → 0
│       └── Result: f(3, 3) = 4
│   └── Result: f(1, 3) for ind=2 = 12
├── Split at ind=3 (cut at 4): cost = cuts[4] - cuts[0] + f(1, 2) + f(4, 3)
│   ├── f(1, 2)
│   │   ├── Split at ind=1 (cut at 1): cost = cuts[3] - cuts[0] + f(1, 0) + f(2, 2)
│   │   │   ├── f(1, 0): Base case → 0
│   │   │   └── f(2, 2)
│   │   │       ├── Split at ind=2 (cut at 3): cost = cuts[3] - cuts[1] + f(2, 1) + f(3, 2)
│   │   │       │   ├── f(2, 1): Base case → 0
│   │   │       │   └── f(3, 2): Base case → 0
│   │   │       └── Result: f(2, 2) = 4
│   │   │   └── Result: f(1, 2) for ind=1 = 10
│   │   ├── Split at ind=2 (cut at 3): cost = cuts[3] - cuts[0] + f(1, 1) + f(3, 2)
│   │   │   ├── f(1, 1)
│   │   │   │   ├── Split at ind=1 (cut at 1): cost = cuts[2] - cuts[0] + f(1, 0) + f(2, 1)
│   │   │   │   │   ├── f(1, 0): Base case → 0
│   │   │   │   │   └── f(2, 1): Base case → 0
│   │   │   │   └── Result: f(1, 1) = 4
│   │   │   └── f(3, 2): Base case → 0
│   │   └── Result: f(1, 2) for ind=2 = 10
│   └── f(4, 3): Base case → 0
│   └── Result: f(1, 3) for ind=3 = 16


//cuts = [0, 1, 3, 4, 6]

    static int f(int i, int j, ArrayList<Integer> cuts) {
        // Base case
        if (i > j) {
            return 0;
        }

        int mini = Integer.MAX_VALUE;

        for (int ind = i; ind <= j; ind++) {
            int ans = cuts.get(j + 1) - cuts.get(i - 1) +
                      f(i, ind - 1, cuts) +
                      f(ind + 1, j, cuts);

            mini = Math.min(mini, ans);
        }

        return mini;
    }
