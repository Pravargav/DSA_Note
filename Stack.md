### Next Greater Element (NGE) — Monotonic Decreasing Stack

```java
import java.util.*;

public class NextGreaterElement {
    public static int[] nextGreater(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indexes

        for (int i = n - 1; i >= 0; i--) {
            // Maintain decreasing stack → pop smaller/equal elements
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }

            res[i] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        System.out.println(Arrays.toString(nextGreater(nums))); // [4, 2, 4, -1, -1]
    }
}
