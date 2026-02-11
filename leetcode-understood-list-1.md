`  3304. Find the K-th Character in String Game I `

-> returns function of return type char( function call) until actual return type char value at base condition is returned.

` 2550. Count Collisions of Monkeys on a Polygon`

-> return (int) ((max + pow(n) - 2) % max);   

->if the value obtained from pow(n) == 1/0 then (1-2 = -1 )/(0-2 = -2) then ( -1 % max will be negative)/( -2 %max will be negative)

-> (max + t) % max <=> we know max%max == 0 so <=> t%max

-> Adding max before taking % max ensures that the result remains non-negative;

-> 10^9+7 is a prime number, making it useful for modular arithmetic;

-> Integer.MAX_VALUE is larger (~2.1×10^9) than 1000000007;

-> 10^9+7 fulfills both the criteria. It is the first 10-digit prime number and fits in int data type as well. In fact, any prime number less than 2^30 will be fine in order to prevent possible overflows.

-> 1,000,000,009 ( Prime)
1,000,000,033 ( Prime)
1,000,000,039 ( Prime)
1,000,000,051 ( Prime)
1,000,000,061 ( Prime)

-> 10 ^9+9=1,000,000,009 is also a frequently used prime in modular computations.

-> https://www.geeksforgeeks.org/multiplicative-inverse-under-modulo-m/


` 2487. Remove Nodes From Linked List`

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class List {
    public static ListNode createSample() {
        // Creating nodes
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        
        // Returning the head of the linked list
        return node1;
    }

    public static void main(String[] args) {
        ListNode head = createSample();
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }
}

Linkedlist node1[val1,node2]; Linkedlist node2[val1,node3]; Linkedlist node3[val,null];
```

-> visualise head.next= remove(head.next) as 5.next=remove(5.next) , 2.next=remove(2.next) etc.. for better understanding and visualization.



` 206. Reverse Linked List `

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
```

-> ListNode newNode = reverseList(head.next);  here newnode is like temp adress variable

`

` 50. Pow(x, n) `

-> 323 - odd i.e. 323-1 = 322
-> 322 - even i.e. 322/2 = 161
-> 161 - odd i.e. 161-1 = 160
.....

returun type double for fun(x*x) / x*fun() calls



` 1823. Find the Winner of the Circular Game`

-> https://stackoverflow.com/questions/58295981/can-someone-explain-how-the-modulus-work

` 1969. Minimum Non-Zero Product of the Array Elements`



-> addition operation - write every variable `x` as `(x+mod)%mod;` 

-> multiplication operation - write every multiplication operation ```x*x``` as ```((x+mod)%mod * (x+mod)%mod)%mod;```

-> function calls operation - write function calls return ```x*rec(x,n-1)``` as ``` return ((x+mod)%mod * (rec((x+mod)%mod, (n - 1)))%mod)%mod; ```

for every mod problem....






