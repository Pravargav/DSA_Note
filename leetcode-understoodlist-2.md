

-> for inorder,postorder,preorder use dfs
-> in dfs pointing a node 

   `if preorder print root -> print all left subtree -> print all right subtree` (dfs)

   `if inorder print all left subtree -> print root -> print all right subtree`(dfs)

   `if postorder print all left subtree -> print all right subtree -> print root`(dfs)



->recurrsive(level order)
```java
//adding new empty arrayList to arraylist of arraylist is following dfs approach only
//adding value/data by getting getLevel() at required point  makes the list of list like bfs
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderRec(root, 0, res);
        return res;
    }

    void levelOrderRec(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null)
            return;

        if (res.size() <= level)
            res.add(new ArrayList<>());

        res.get(level).add(root.val);

        levelOrderRec(root.left, level + 1, res);
        levelOrderRec(root.right, level + 1, res);
    }
}
```

`100. Same Tree`

-> always remember use always lot of &&'s , ||'s in any boolean return type sums


`1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree`

```java
    public TreeNode fun(TreeNode t,TreeNode target){
        
        if(t==null){
            return t;
        }
        if(isSameTree(t,target)){
            return t;
        }
        TreeNode l=fun(t.left,target);
        if(l!=null){
            return l;
        }
        return fun(t.right,target);
    }
```
-> traversing and return type of TreeNode( backtracking rather than void datatype return like treeNode,LinkedList etc...)

```
        5
       / \
      3   8
     / \   \
    1   4   10
```

->3rd Call: findNode(1, 4)
->root = 1 (not 4)
->Calls findNode(null, 4) for left child → Returns null
->Calls findNode(null, 4) for right child → Returns null


->Since both left and right returned null, there is no match in 1's subtree.
->Return null to its caller (findNode(3, 4)).
->Back to 2nd Call (findNode(3, 4))

```
Tree left = find(3,4)
//returned null is assigned to left ,here if left not null return left.But left is null here so return right i.e find(4,4)
if(left!=null)
  return left;
return find(4,4);
```
->findNode(1, 4) returned null, meaning 4 is not in the left subtree of 3.
->Now, call findNode(4, 4) to search the right subtree of 3.




`16)226. Invert Binary Tree`

-> same formula we used for linked list(swapping) and Treenode return sums **i.e. take temporary vairable temp of type TreeNode/linked list and swap like swap elements in array** using temp.

```java
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode t= invertTree(root.left);
        root.left=invertTree(root.right);
        root.right=t;
        return root;
        
    }

```
`671. Second Minimum Node In a Binary Tree`


`938. Range Sum of BST`

```java
public int rangeSumBST(TreeNode root, int low, int high) {
        if(root==null){
            return 0;
        }
        
        int k=rangeSumBST(root.left,low,high);
        int sum=0;
        if(root.val<=high&&root.val>=low){
            sum+=root.val;
        }
        int l=rangeSumBST(root.right,low,high);
        return k+sum+l;
    }
```



`1008. Construct Binary Search Tree from Preorder Traversal`

```java
    int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        convert(root);
        return root;
    }
    
    public void convert(TreeNode cur) {
        if (cur == null) return;
        convert(cur.right);
        cur.val += sum;
        sum = cur.val;
        convert(cur.left);
    }
```
->traverse and change all values and finlly return root



