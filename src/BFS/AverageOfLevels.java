package BFS;
import java.util.*;
public class AverageOfLevels {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BFSQues.BinaryTree bt = new BFSQues.BinaryTree();
        List<Double> ans = new ArrayList<>();
        bt.populate(sc);
        Queue<BFSQues.BinaryTree.Node> q = new LinkedList<>();
        BFSQues.BinaryTree.Node root = new BFSQues.BinaryTree.Node(3);
        q.offer(root);

        while(!q.isEmpty()){
            int level = q.size();
            double levelAvg = 0;
            for (int i = 0; i < level; i++) {
                BFSQues.BinaryTree.Node currNode = q.poll();
                levelAvg += currNode.val;

                if(currNode.left != null){
                    q.offer(currNode.left);
                }
                if(currNode.right != null){
                    q.offer(currNode.right);
                }
            }
            levelAvg = levelAvg / level;
            ans.add(levelAvg);
        }

        System.out.println(ans);
    }
}
