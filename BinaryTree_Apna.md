**all paths from root to leafs**
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

public void fun(List<Integer> lt, TreeNode root){
    if(root == null){
        return;
    }

    lt.add(root.val);

    if(root.left == null && root.right == null){
        System.out.println(lt);
    } else {
        fun(lt, root.left);
        fun(lt, root.right);
    }

    lt.remove(lt.size() - 1); // backtrack
}
}
```
**level order**
````markdown

####  Why `q.add(null)` is used twice?

👉 It is used as a **level separator (marker)** to distinguish between different levels of the tree.

***

#### 1️⃣ First time:

```java
q.add(root);
q.add(null);
```

✅ Purpose:

*   After inserting the root, you add `null` to mark:
    👉 **"End of level 1"**

***

#### 2️⃣ Second time:

```java
if (curr == null) {
    System.out.println();

    if (q.isEmpty()) break;

    q.add(null);   // ✅ second use
}
```

✅ Purpose:

*   When you encounter a `null`, it means:
    👉 **"Current level is finished"**

*   Then you:
    *   print a newline (move to next level)
    *   add another `null` to mark the **end of next level**

***
````
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



