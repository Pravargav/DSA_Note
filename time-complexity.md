
1. The **worst-case performance** (Big O),
2. The **best-case performance** (Big Omega),
3. The **tight bound** or exact growth rate (Big Theta).

Let’s break this down with **functions**, **graphs**, and **code examples**.

---

## 📘 1. The Three Main Notations

| Notation  | Meaning   | Describes                        |
| --------- | --------- | -------------------------------- |
| `O(f(n))` | Big O     | Upper bound (worst case)         |
| `Ω(f(n))` | Big Omega | Lower bound (best case)          |
| `Θ(f(n))` | Big Theta | Tight bound (average/exact case) |

---

## 📊 2. Example Function: Linear Search

### Code Example:

```java
int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}
```

### Analysis:

* **Best Case**: `target` is at index `0` → Only 1 comparison → **Ω(1)**
* **Worst Case**: `target` is not in array → All `n` elements checked → **O(n)**
* **Average Case**: On average, `target` is in the middle → \~`n/2` → **Θ(n)**

### Graph:

```
Time
│               *
│            *
│         *
│      *
│   *
│*
├─────────────────> n (input size)
     Ω(1)   Θ(n)   O(n)
```

---

## 📊 3. Example Function: Binary Search

### Code Example:

```java
int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = (left + right) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

### Analysis:

* **Best Case**: `target` is at the middle → 1 step → **Ω(1)**
* **Worst Case**: Need to divide `log₂(n)` times → **O(log n)**
* **Average Case**: Still logarithmic → **Θ(log n)**

### Graph:

```
Time
│*
│  *
│     *
│        *
│           *
│              *
├─────────────────> n
     Ω(1)   Θ(log n)   O(log n)
```

---

## ⚖️ Why Not Just Use Big O?

Big O only tells us the **worst-case scenario**. But:

* Sometimes we care about **best-case performance** too (e.g., database lookup).
* Other times we want to know how the algorithm behaves on **average**.

### Example: Sorting

| Algorithm   | Best Case  | Worst Case | Average Case |
| ----------- | ---------- | ---------- | ------------ |
| Bubble Sort | Ω(n)       | O(n²)      | Θ(n²)        |
| Merge Sort  | Ω(n log n) | O(n log n) | Θ(n log n)   |

If you only look at Big O, you might **overestimate or underestimate** performance.

