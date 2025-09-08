-> height of tree(diamter array not necessary) and (striver approach of diamter of tree)

```java

class Node {
    int data;
    Node left;
    Node right;

    // Constructor to initialize
    // the node with a value
    Node(int val) {
        data = val;
        left = null;
        right = null;
    }
}

class Solution {
    // Function to find the
    // diameter of a binary tree
    public int diameterOfBinaryTree(Node root) {
        // Initialize the variable to
        // store the diameter of the tree
        int[] diameter = new int[1];
        diameter[0] = 0;
        // Call the height function to traverse
        // the tree and calculate diameter
        int val=height(root, diameter);
        System.out.println("The height of the binary tree is: "+ val);
        // Return the calculated diameter
        return diameter[0];
    }

    // Function to calculate the height of
    // the tree and update the diameter
    private int height(Node node, int[] diameter) {
        // Base case: If the node is null,
        // return 0 indicating the
        // height of an empty tree
        if (node == null) {
            return 0;
        }

        // Recursively calculate the
        // height of left and right subtrees
        int[] lh = new int[1];
        int[] rh = new int[1];
        lh[0] = height(node.left, diameter);
        rh[0] = height(node.right, diameter);

        // Update the diameter with the maximum
        // of current diameter or sum of
        // left and right heights
        diameter[0] = Math.max(diameter[0], lh[0] + rh[0]);

        // Return the height of
        // the current node's subtree
        return 1 + Math.max(lh[0], rh[0]);
    }
}

// Main class
public class Main {
    // Main function
    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(6);
        root.left.right.right.right = new Node(7);
        root.right.left = new Node(8);
        root.right.left.left = new Node(9);
        root.left.right.right.right.right = new Node(10);
        // Creating an instance of the Solution class
        Solution solution = new Solution();
        

        // Calculate the diameter of the binary tree
        int diameter = solution.diameterOfBinaryTree(root);

        System.out.println("The diameter of the binary tree is: " + diameter);
    }
}    

```
-> diameter of tree( Same code as above ditto)and(diameter array necessary)and(striver approach of diamter of tree)

```java
class Node {
    int data;
    Node left;
    Node right;

    // Constructor to initialize
    // the node with a value
    Node(int val) {
        data = val;
        left = null;
        right = null;
    }
}

class Solution {
    // Function to find the
    // diameter of a binary tree
    public int diameterOfBinaryTree(Node root) {
        // Initialize the variable to
        // store the diameter of the tree
        int[] diameter = new int[1];
        diameter[0] = 0;
        // Call the height function to traverse
        // the tree and calculate diameter
        int val=height(root, diameter);
        System.out.println("The height of the binary tree is: "+ val);
        // Return the calculated diameter
        return diameter[0];
    }

    // Function to calculate the height of
    // the tree and update the diameter
    private int height(Node node, int[] diameter) {
        // Base case: If the node is null,
        // return 0 indicating the
        // height of an empty tree
        if (node == null) {
            return 0;
        }

        // Recursively calculate the
        // height of left and right subtrees
        int[] lh = new int[1];
        int[] rh = new int[1];
        lh[0] = height(node.left, diameter);
        rh[0] = height(node.right, diameter);

        // Update the diameter with the maximum
        // of current diameter or sum of
        // left and right heights
        diameter[0] = Math.max(diameter[0], lh[0] + rh[0]);

        // Return the height of
        // the current node's subtree
        return 1 + Math.max(lh[0], rh[0]);
    }
}

// Main class
public class Main {
    // Main function
    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(6);
        root.left.right.right.right = new Node(7);
        root.right.left = new Node(8);
        root.right.left.left = new Node(9);
        root.left.right.right.right.right = new Node(10);
        // Creating an instance of the Solution class
        Solution solution = new Solution();
        

        // Calculate the diameter of the binary tree
        int diameter = solution.diameterOfBinaryTree(root);

        System.out.println("The diameter of the binary tree is: " + diameter);
    }
}
```
-> Diameter of a tree( gfg approach)
```java
// Java program to find the diameter
// of a binary tree.
import java.lang.Math;

class Node {
    int data;
    Node left, right;

    Node(int x) {
        data = x;
        left = null;
        right = null;
    }
}

class GfG {

    // Recursive function which finds the 
    // height of root and also calculates 
    // the diameter of the tree.
    static int diameterRecur(Node root, int[] res) {

        // Base case: tree is empty
        if (root == null)
            return 0;

        // find the height of left and right subtree
        // (it will also find of diameter for left 
        // and right subtree).
        int lHeight = diameterRecur(root.left, res);
        int rHeight = diameterRecur(root.right, res);

        // Check if diameter of root is greater
        // than res.
        res[0] = Math.max(res[0], lHeight + rHeight);

        // return the height of current subtree.
        return 1 + Math.max(lHeight, rHeight);
    }

    // Function to get diameter of a binary tree
    static int diameter(Node root) {
        int[] res = new int[1];
        diameterRecur(root, res);
        return res[0];
    }

    public static void main(String[] args) {

        // Constructed binary tree is
        //          5
        //        /   \
        //       8     6
        //      / \   /
        //     3   7 9
        Node root = new Node(5);
        root.left = new Node(8);
        root.right = new Node(6);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.left = new Node(9);

        System.out.println(diameter(root));
    }
}
```
-> max sum between 2 leaf nodes(almost same code as above but in place of 1 use root.data and some minor changes) and(gfg approach)

```java
// Java program to find maximum path 
// sum between two leaves of a binary tree

class Node {
    int data;
    Node left, right;

    Node(int x) {
        data = x;
        left = right = null;
    }
}

class GfG {
  
    // mxPathSum function to calculate maximum path sum 
  	// between two leaves and maximum sum from node to 
  	// leaf in a single traversal
    static int mxPathSum(Node root, int[] maxPathSum) {
      
        // Base case: If the node is null, return 0
        if (root == null) {
            return 0;
        }

        // Recursively calculate the maximum sum from 
        // node to leaf for left and right subtrees
        int mxLeft = mxPathSum(root.left, maxPathSum);
        int mxRight = mxPathSum(root.right, maxPathSum);

        // If both left and right children exist,
      	// update maxPathSum
        if (root.left != null && root.right != null) {
          
            // This is the sum of the left path, 
            // right path, and the node's data
            int maxSumPathViaNode = mxLeft + mxRight + root.data;

            // Update the maximum sum path between
          	// two leaves
            maxPathSum[0] = Math.max
              				(maxPathSum[0], maxSumPathViaNode);

            // Return the maximum sum from the current
          	// node to a leaf
            return root.data + Math.max(mxLeft, mxRight);
        }

        // If only one child exists, return the sum
      	// from the node to leaf
        return root.data + 
          (root.left != null ? mxLeft : mxRight);
    }

    // Function to return the maximum path 
    // sum between any two leaves in the binary tree
    static int findMaxSum(Node root) {
      
        // Edge case: If the tree is empty, return 0
        if (root == null) {
            return 0;
        }

        int[] maxPathSum = new int[] {Integer.MIN_VALUE};
        mxPathSum(root, maxPathSum);

        return maxPathSum[0];
    }

    public static void main(String[] args) {
      
        // Construct a sample binary tree
        //
        //         1
        //       /   \
        //     -2     3
        //     / \   / \
        //    8  -1  4  -5

        Node root = new Node(1);
        root.left = new Node(-2);
        root.right = new Node(3);
        root.left.left = new Node(8);
        root.left.right = new Node(-1);
        root.right.left = new Node(4);
        root.right.right = new Node(-5);

        int result = findMaxSum(root);
        System.out.println(result);
    }
}
```
