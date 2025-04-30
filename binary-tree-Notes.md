**build Tree**
```java
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
  static class Node{
      int data;
      Node left;
      Node right;
      
      Node(int data){
          this.data=data;
          left=null;
          right=null;
      }
  }
  
  static class BinaryTree{
      static int idx=-1;
      public static Node buildTree(int nodes[]){
          idx++;
          if(node[idx]==-1){
              return null;
          }
          Node newNode=new Node(nodes[idx]);
          newNode.left=buildTree(nodes);
          newNode.right=buildTree(nodes);
          
          return newNode;
      }
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
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
  public static int countNodes(Node root){
      if(root==null){
          return 0;
      }
      int leftNodes=countNodes(root.left);
      int rightNodes=countNodes(root.right);
      return leftNodes+rightNodes+1;
  }
}
```
**sum nodes**
```java
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
  public static int sumNodes(Node root){
      if(root==null){
          return 0;
      }
      int leftsum=sumOfNodes(root.left);
      int rightsum=sumOfNodes(root.right);
      
      return leftsum+rightsum+root.data;
  }
}
```
**find height**
```java
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
  public static int findHeight(Node root){
      if(root==null){
          return 0;
      }
      int leftH=findHeight(root.left);
      int rightH=findHeight(root.right);
      
      int height=max(leftH,rightH)+1;
      return height;
  }
}
```
**find diameter**
```java
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
   public static int findHeight(Node root){
       if(root==null){
           return 0;
       }
       int leftH=findHeight(root.left);
       int rightH=findHeight(root.right);
       int height=max(leftH,rightH)+1;
       
       return height;
   }
   
   public static int findDiameter(Node root){
       if(root==null){
           return 0;
       }
       int diaM1=findDiameter(root.left);
       int diaM2=findDiameter(root.right);
       int diaM3=height(root.left)+height(root.right)+1;
       return Math.max(diaM3,Math.max(diaM2,diaM1));
   }
}
```
**is Subtree**
```java
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
  public boolean isSubTree(TreeNode root,TreeNode subRoot){
      if(root==null){
          return true;
      }
      if(subRoot==null){
          return false;
      }
      if(root.val==subRoot.val){
          if(isIdentical(root,subRoot)){
              return true;
          }
      }
      return isSubTree(root.left,subRoot)||isSubTree(root.right,subRoot);
  }
  
  public boolean isIdentical(TreeNode root,TreeNode subRoot){
      if(root==null&&subRoot==null){
          return true;
      }
      if(root==null||subRoot==null){
          return false;
      }
      if(root.val==subRoot.val){
      return isIdentical(root.left,subRoot.left) && isIdentical(root.right,subRoot.right);
      }
      return false;
  }
}

```
