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

**Create LinkedList**
```java
public ListNode createList(int[] arr) {
    ListNode head = null;
    ListNode tail = null;

    for (int val : arr) {
        ListNode node = new ListNode(val);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }
    return head;
}
```
