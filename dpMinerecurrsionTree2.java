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
