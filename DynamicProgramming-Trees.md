````markdown
 -> So what actually needs array here?

-> Only this:

```java
int[] diameter = new int[1];
```

-> ✅ Why Because?

-> diameter is updated inside recursion

-> We want changes to persist across all calls

_> Java is pass-by-value, so we wrap it in an array

````

-> height of tree(diamter array not necessary) and (striver approach of diamter of tree)

```java
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
    }
}

class Solution {
    public int diameterOfBinaryTree(Node root) {
        int[] diameter = new int[1];
        height(root, diameter);
        return diameter[0];
    }

    private int height(Node node, int[] diameter) {
        if (node == null) return 0;

        int left = height(node.left, diameter);
        int right = height(node.right, diameter);

        diameter[0] = Math.max(diameter[0], left + right);

        return 1 + Math.max(left, right);
    }
} 
```
-> diameter of tree( Same code as above ditto)and(diameter array necessary)and(striver approach of diamter of tree)

```java
class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

class Solution {
    private int diameter = 0;

    public int diameterOfBinaryTree(Node root) {
        height(root);
        return diameter;
    }

    private int height(Node node) {
        if (node == null) return 0;

        int left = height(node.left);
        int right = height(node.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }
}
```
-> Diameter of a tree( gfg approach) - refer to gfg website but you will find same code as striver's

-> max sum between 2 leaf nodes(almost same code as above but in place of 1 use root.data and some minor changes) and(gfg approach)

```java
class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int findMaxSum(Node root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(Node node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        if (node.left != null && node.right != null) {
            maxSum = Math.max(maxSum, left + right + node.data);
            return node.data + Math.max(left, right);
        }

        if (node.left != null) {
            return node.data + left;
        } else {
            return node.data + right;
        }
    }
}
```

-> Max path sum between any two nodes(If either left or right height is negative or zero no need to include in total sum at each node.If positive height then only adding to the total sum makes some value for max getting) and (same approach by both gfg and striver)
```java
class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(Node root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(Node node) {
        if (node == null) return 0;

        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        maxSum = Math.max(maxSum, left + right + node.data);

        return node.data + Math.max(left, right);
    }
}
```

