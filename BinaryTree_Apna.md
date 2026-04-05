**all path sums from root to leafs**
```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        boolean k = sumLeaf(root, 0, targetSum);
        return k;
    }

    public boolean sumLeaf(TreeNode root, int sum, int target) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (sum + root.val == target) {
                return true;
            }
        }
        return sumLeaf(root.left, sum + root.val, target) || sumLeaf(root.right, sum + root.val, target);
    }
}
```
---(OR)---

```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> lt=new ArrayList<>();
        fun(root, lt);
        return new ArrayList<>();
    }

    public void fun(TreeNode root, List<Integer> lt){
         if(root==null){
            return;
         }
         if(root.left==null&&root.right==null){
            lt.add(root.val);
            System.out.println(lt);
            lt.remove(lt.size()-1);
         }
        lt.add(root.val);
         fun(root.left, lt);
        lt.remove(lt.size()-1);
        lt.add(root.val);
         fun(root.right, lt);
        lt.remove(lt.size()-1);
    }
}
```

**build Tree**
```java
static class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

static class BinaryTree {
    static int idx = -1;

    public static Node buildTree(int[] nodes) {
        idx++;
        if (nodes[idx] == -1) return null;

        Node root = new Node(nodes[idx]);
        root.left = buildTree(nodes);
        root.right = buildTree(nodes);

        return root;
    }
}
```
**level order**
```java
public static void levelOrder(Node root) {
    if (root == null) return;

    Queue<Node> q = new LinkedList<>();
    q.add(root);
    q.add(null);

    while (!q.isEmpty()) {
        Node curr = q.remove();

        if (curr == null) {
            System.out.println();
            if (q.isEmpty()) break;
            q.add(null);
        } else {
            System.out.print(curr.data + " ");
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
    }
}
```
**count nodes**
```java
public static int countNodes(Node root) {
    if (root == null) return 0;
    return countNodes(root.left) + countNodes(root.right) + 1;
}
```
**sum nodes**
```java
public static int sumNodes(Node root) {
    if (root == null) return 0;
    return sumNodes(root.left) + sumNodes(root.right) + root.data;
}
```
**find height**
```java
public static int findHeight(Node root) {
    if (root == null) return 0;
    return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
}
```
**find diameter**
```java
public static int findHeight(Node root) {
    if (root == null) return 0;
    return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
}

public static int findDiameter(Node root) {
    if (root == null) return 0;

    int leftDia = findDiameter(root.left);
    int rightDia = findDiameter(root.right);
    int selfDia = findHeight(root.left) + findHeight(root.right) + 1;

    return Math.max(selfDia, Math.max(leftDia, rightDia));
}
```
**is Subtree**
```java
public boolean isSubTree(TreeNode root, TreeNode subRoot) {
    if (subRoot == null) return true;
    if (root == null) return false;

    if (root.val == subRoot.val && isIdentical(root, subRoot)) return true;

    return isSubTree(root.left, subRoot) || isSubTree(root.right, subRoot);
}

public boolean isIdentical(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null) return true;
    if (root == null || subRoot == null) return false;

    if (root.val != subRoot.val) return false;

    return isIdentical(root.left, subRoot.left) &&
           isIdentical(root.right, subRoot.right);
}

```
**🔗Linked List and Binary Tree – Key Points**

-> *Approach 1 (Mover Pointer)*

1)Create head from first element

2)Use mover to attach next nodes

3)Move forward step by step

```java
static class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

static Node arrayToLL(int[] arr) {
    Node head = new Node(arr[0]);
    Node mover = head;

    for (int i = 1; i < arr.length; i++) {
        mover.next = new Node(arr[i]);
        mover = mover.next;
    }

    return head;
}
```

-> *Approach 2 (Head + Temp Pointer)*

1)Initialize head = null

2)First node → assign to both head and temp

3)Then keep linking

```java
static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

static ListNode createLinkedList(int[] arr) {
    ListNode head = null, temp = null;

    for (int x : arr) {
        ListNode node = new ListNode(x);

        if (head == null) {
            head = temp = node;
        } else {
            temp.next = node;
            temp = temp.next;
        }
    }

    return head;
}
```
