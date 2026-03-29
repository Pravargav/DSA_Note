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
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
  public static void levelOrder(Node root){
      if(root==null){
          return;
      }
      Queue<Node> q=new LinkedList<>();
      q.add(root);
      q.add(null);
      
      while(!q.isEmpty()){
          Node curr=q.remove();
          if(curr==null){
              System.out.println();
              if(q.isEmpty()){
                  break;
              }else{
                  q.add(null);
              }
          }else{
              System.out.print(curr.data+" ");
              if(curr.left!=null){
                  q.add(curr.left);
              }if(curr.right!=null){
                  q.add(curr.right);
              }
          }
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
-> Imp points Binary Tree

```java
public class BinaryTreesYT {
   static class Node {
       int data;
       Node left;
       Node right;


       Node(int data) {
           this.data = data;
           this.left = null;
           this.right = null;
       }
   }


   static class BinaryTree {
       static int idx = -1;
       public static Node buildTree(int nodes[]) {
           idx++;
           if(nodes[idx] == -1) {
               return null;
           }
           Node newNode = new Node(nodes[idx]); 
           newNode.left = buildTree(nodes);
           newNode.right = buildTree(nodes);
           return newNode;
       }
   }


   public static void main(String args[]) {
       int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
       BinaryTree tree = new BinaryTree();
      
       Node root = tree.buildTree(nodes);
       System.out.println(root.data);
   }
}


//keep track of newNode pointer points to the root node
//apna college

class Node{
    int data;
    Node next;
    Node(int data1, Node next1){
        this.data=data1;
        this.next=next1;
    }
    Node(int data1){
        this.data=data1;
        this.next=null;
    }
};
public class LinkedList{
    private static Node convertarr2LL(int[] arr){
        Node head= new Node(arr[0]);
        Node mover=head;
        for (int i=1;i<arr.length;i++){
            Node temp= new Node(arr[i]);
            mover.next=temp;
            mover=mover.next;
        }
        return head;
    }
    public static void main(String[] args){
        int[] arr={2,5,8,7};
        Node head=convertarr2LL(arr);
        System.out.print(head.data);
    }
}

//keep track of head pointer pointing to the head node 
//striver
//Convert an array to a Linked List -google it..

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 5, 2};
        ListNode head = createLinkedList(arr);
        printLinkedList(head);
    }

    public static ListNode createLinkedList(int[] arr) {
        ListNode head = null, temp = null;
        for (int element : arr) {
            ListNode toAdd = new ListNode(element);
            if (head == null) {
                head = temp = toAdd;
            } else {
                temp.next = toAdd;
                temp = temp.next;
            }
        }
        return head;
    }

    public static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
}

//naukari 
//create a linked list from an array
```
