```java
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
           
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }

            res[i] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i);
        }
```
```java
       int[] res = new int[n - k + 1];
       Deque<Integer> deque = new ArrayDeque<>();

       for (int i = 0; i < n; i++) {
   
           while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
               deque.pollFirst();
           }

 
           while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
               deque.pollLast();
           }

           deque.offerLast(i);

           if (i >= k - 1) {
               res[i - k + 1] = nums[deque.peekFirst()];
           }
       }
```
