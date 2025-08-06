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
