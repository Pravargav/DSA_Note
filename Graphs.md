-> Building arraylist of arraylist from 2d array
```java
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        
        ArrayList<ArrayList<Integer>> adj =new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int [] edge: edges){
            int u=edge[0];
            int v=edge[1];
            adj.get(u).add(v);
        }

        for(int i=0;i<adj.size();i++){
            System.out.println(i+"->"+adj.get(i));
        }

        return true;
    }
}
```
