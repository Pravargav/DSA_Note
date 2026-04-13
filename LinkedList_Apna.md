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
**Remove all nodes having given input value**

->  **Approach** 
     Idea:
     
     1. Traverse the list using a pointer.
     
     2. If the NEXT node has the target value,
        skip it by changing links.
        
     3. Otherwise move forward.
     
     4. Finally handle the special case where
        the head itself must be removed.
     
     
```java
class Solution {



    public ListNode removeElements(ListNode head, int val) {

        // Edge case: empty list
        if (head == null) {
            return null;
        }

        // Pointer used to traverse the list
        ListNode curr = head;

        // Traverse until last node
        while (curr != null) {

            
             //Check the NEXT node (not current)
             //because removing current node
             //would lose the reference.
             
            if (curr.next != null && curr.next.val == val) {

                // Skip the node having target value
                // (delete curr.next)
                curr.next = curr.next.next;
                //remove consecutive nodes ahead whose value equals val

            } else {
                // Move forward only when no deletion happens
                curr = curr.next;
            }
        }

        
         //Handle head separately:
         //If head itself contains the value,
         //move head forward.
         
        if (head.val == val) {
            head = head.next;
        }

        return head;
    }
}
```
***very very important solution for head.next!=null check rather than head==null check***

-> https://leetcode.com/problems/remove-duplicates-from-sorted-list/?envType=problem-list-v2&envId=wga5p0ds

```java
class Solution {
    public ListNode delete_duplicates(ListNode head) {
        ListNode temp = head;
        while (head != null) {
            if(head.next==null){
                break;
            }
            if (head.val == head.next.val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return temp;
    }
}
```
**reverse linked list**
```java

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode newx = null;
        ListNode temp = null;
        while (curr != null) {
            prev = curr;
            curr = curr.next;
            prev.next = null;
            temp = prev;
            if(newx==null){
                newx = prev;
                temp = newx;
            }else{
                temp = prev;
                temp.next = newx;
                newx = temp;
            }
        }
        return newx;
    }
}
```
