# Monotonic Stack and Monotonic Queue in Java

## 🔷 Monotonic Stack

A **Monotonic Stack** is a stack that maintains its elements in a **monotonically increasing or decreasing order**. It's used in problems like **Next Greater Element**, **Largest Rectangle in Histogram**, etc.

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
