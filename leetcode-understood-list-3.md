**Apna college(Deletion in Bst)**
`450. Delete Node in a BST`

```java
    TreeNode delNode(TreeNode root, int x) {
      
        // Base case
        if (root == null) {
            return root;
        }

        // If key to be searched is in a subtree
        if (root.key > x) {
            root.left = delNode(root.left, x);
        } else if (root.key < x) {
            root.right = delNode(root.right, x);
        } else {
            // If root matches with the given key

            // Case when root has 0 children
              if (root.left == null&&root.right == null) {
                return root.right;
            }
            
            // When root has only right child
            if (root.left == null) {
                return root.right;
            }

            // When root has only left child
            if (root.right == null) {
                return root.left;
            }

            // When both children are present
            TreeNode succ = getSuccessor(root);
            root.key = succ.key;
            root.right = delNode(root.right, succ.key);
        }
        return root;
    }
 
    // Note that it is not a generic inorder successor 
    // function. It mainly works when the right child
    // is not empty, which is the case we need in BST
    // delete. 
    TreeNode getSuccessor(Node curr) {
        curr = curr.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }  


```
**Apna college(Insertion in Bst)**
`701. Insert into a Binary Search Tree`

```java
    TreeNode insert(Node root, int key)
    {

        // If the tree is empty, return a new node
        if (root == null)
            return new Node(key);

        // If the key is already present in the tree,
        // return the node
        if (root.key == key)
            return root;

        // Otherwise, recur down the tree
        if (key < root.key)
            root.left = insert(root.left, key);
        else
            root.right = insert(root.right, key);

        // Return the (unchanged) node pointer
        return root;
    }

```

**Apna college(Search in Bst)**
`700. Search in a Binary Search Tree`

```java
    TreeNode search(TreeNode root, int key)
    {
        if (root == null || root.key == key)
            return root;

        // Key is greater than root's key
        if (root.key < key)
            return search(root.right, key);

        // Key is smaller than root's key
        return search(root.left, key);
    }
```
