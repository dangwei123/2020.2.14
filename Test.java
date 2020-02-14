/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list=new LinkedList<>();
        if(root==null){
            return list;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int count=queue.size();
            List<Integer> row=new LinkedList<>();
            while(count!=0){
                TreeNode node=queue.poll();
                row.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                count--;
            }
            list.add(row); 
        }
        return list;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return buildTree(preorder,0,preorder.length,inorder,0,inorder.length,map);
    }
    private TreeNode buildTree(int[] preorder,int p_s,int p_e,int[] inorder,int i_s,int i_e,HashMap<Integer,Integer> map){
        if(p_s==p_e){
            return null;
        }
        int root_val=preorder[p_s];
        TreeNode root=new TreeNode(root_val);
        int i_val=map.get(root_val);
        int leftNum=i_val-i_s;
        root.left=buildTree(preorder,p_s+1,p_s+1+leftNum,inorder,i_s,i_val,map);
        root.right=buildTree(preorder,p_s+1+leftNum,p_e,inorder,i_val+1,i_e,map);
        return root;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        index=postorder.length-1;
        return buildTree(inorder,postorder,0,postorder.length-1,map);
    }
    private TreeNode buildTree(int[] inorder,int[] postorder,int i_start,int i_end,HashMap<Integer,Integer> map){
        if(i_start>i_end){
            return null;
        }
        int root_val=map.get(postorder[index]);
        TreeNode root=new TreeNode(postorder[index--]);
        root.right=buildTree(inorder,postorder,root_val+1,i_end,map);
        root.left=buildTree(inorder,postorder,i_start,root_val-1,map);
        return root;
    }
}