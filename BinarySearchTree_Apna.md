**insert**
```java
public static Node insert(Node root, int val) {
    if (root == null) return new Node(val);

    if (val < root.data) {
        root.left = insert(root.left, val);
    } else {
        root.right = insert(root.right, val);
    }
    return root;
}
```
**search**
```java
public static boolean search(Node root, int key) {
    if (root == null) return false;

    if (key < root.data) return search(root.left, key);

    if (key > root.data) return search(root.right, key);

    return (key == root.data);
}
```
**delete**
```java
public static Node delete(Node root, int val) {
    if (root == null) return null;

    if (val < root.data) {
        root.left = delete(root.left, val);
    } else if (val > root.data) {
        root.right = delete(root.right, val);
    } else {
        if (root.left == null && root.right == null) return null;
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;

        Node is = inorderSuccessor(root.right);
        root.data = is.data;
        root.right = delete(root.right, is.data);
    }

    return root;
}

public static Node inorderSuccessor(Node root) {
    while (root.left != null) root = root.left;
    return root;
}
```
**print in range(inorder)**
```java
public static void printInRange(Node root, int x, int y) {
    if (root == null) return;

    if (root.data >= x && root.data <= y) {
        printInRange(root.left, x, y);
        System.out.print(root.data + " ");
        printInRange(root.right, x, y);
    } else if (root.data < x) {
        printInRange(root.right, x, y);
    } else {
        printInRange(root.left, x, y);
    }
}
```

