**Traverse LinkedList**

```java
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }
}

public void traverse(ListNode head) {
    ListNode curr = head;

    while (curr != null) {
        System.out.print(curr.val + " ");
        curr = curr.next;
    }
}
```
