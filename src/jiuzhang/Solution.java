package jiuzhang;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        //遞歸 或是 分治法
        List<Integer> result = new ArrayList<>();
        // traverse(root,result);
        if(root == null) return result;

        //divide
        List<Integer> left = preorderTraversal(root.left); //左小弟
        List<Integer> right = preorderTraversal(root.right); //右小弟
        //conqour （把左小弟右小弟結果匯總）
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);

        return result;

    }
}
