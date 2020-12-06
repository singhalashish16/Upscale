/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 class Solution {
    TreeNode root;
    Map<TreeNode, TreeNode> parent;
    public static void main(String args[])  
    { 
        Solution tree = new Solution(); 
   
        /* Let us construct the tree shown in above diagram */
        tree.root = new TreeNode(20); 
        tree.root.left = new TreeNode(8); 
        tree.root.right = new TreeNode(22); 
        tree.root.left.left = new TreeNode(4); 
        tree.root.left.right = new TreeNode(12); 
        tree.root.left.right.left = new TreeNode(10); 
        tree.root.left.right.right = new TreeNode(14); 
        TreeNode target = tree.root.left.right; 
        List<Integer> ans=tree.distanceK(tree.root, target, 2);
        
        for(Integer v:ans)
        {
            System.out.print(v+" ");
        }
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parent = new HashMap();
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue)
                        ans.add(n.val);
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}

