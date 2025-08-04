### Next Greater Element (NGE)/Previous Smaller Element/Next Smaller element/ Previous Greater  — Monotonic Decreasing Stack

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

```

### Many stack problems use list/map of 26 stacks as 26 characters to track indexes of character

```java
class Solution {

    public String clearStars(String s) {
        Stack<Integer>[] cnt = new Stack[26];
        for (int i = 0; i < 26; i++) {
            cnt[i] = new Stack<>();
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '*') {
                cnt[arr[i] - 'a'].push(i);
            } else {
                for (int j = 0; j < 26; j++) {
                    if (!cnt[j].isEmpty()) {
                        arr[cnt[j].pop()] = '*';
                        break;
                    }
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (char c : arr) {
            if (c != '*') {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

-> Mostly use stringbuilder insert/append rather than string st+=str;
