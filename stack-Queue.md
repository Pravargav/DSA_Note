

### ✅ Java Code: Monotonic Increasing Stack (Next Greater Element)

```java
import java.util.*;

public class MonotonicStack {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            // Maintain monotonic decreasing stack
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }

        return res;
    }
}


🔷 Monotonic Queue
A Monotonic Queue is a double-ended queue (deque) where the elements are kept in monotonic order, typically used in sliding window maximum or minimum problems.

✅ Java Code: Monotonic Decreasing Queue (Sliding Window Maximum)
import java.util.*;

public class MonotonicQueue {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Remove indices out of the current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Remove smaller values (monotonic decreasing)
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Record the max value when the first window is complete
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return res;
    }
}

### ✅ Repeated template- 3589. Count Prime-Gap Balanced Subarrays
```java
import java.util.*;

class Solution {
    public int primeSubarray(int[] nums, int k) {
        int n = nums.length;
        int left = 0, ans = 0;

        // Deques to track primes and min/max in window
        Deque<Integer> minDeque = new ArrayDeque<>();  // increasing order
        Deque<Integer> maxDeque = new ArrayDeque<>();  // decreasing order
        Deque<Integer> primeIndices = new ArrayDeque<>(); // prime indices in window

        for (int right = 0; right < n; right++) {
            int num = nums[right];

            if (isPrime(num)) {
                // Maintain minDeque (increasing)
                while (!minDeque.isEmpty() && nums[minDeque.peekLast()] > num)
                    minDeque.pollLast();
                minDeque.addLast(right);

                // Maintain maxDeque (decreasing)
                while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] < num)
                    maxDeque.pollLast();
                maxDeque.addLast(right);

                // Track all prime positions
                primeIndices.addLast(right);
            }

            // Shrink window until primes are valid (max - min ≤ k)
            while (!primeIndices.isEmpty() && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
                if (primeIndices.peekFirst() == left)
                    primeIndices.pollFirst();
                if (!minDeque.isEmpty() && minDeque.peekFirst() == left)
                    minDeque.pollFirst();
                if (!maxDeque.isEmpty() && maxDeque.peekFirst() == left)
                    maxDeque.pollFirst();
                left++;
            }

            // Count valid subarrays ending at `right` with at least 2 primes
            if (primeIndices.size() >= 2) {
                Iterator<Integer> iterator = primeIndices.descendingIterator();
                iterator.next(); // skip last
                int secondLastPrimeIndex = iterator.next(); // get second last
                ans += secondLastPrimeIndex - left + 1;
            }
        }

        return ans;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++)
            if (num % i == 0)
                return false;
        return true;
    }
}
