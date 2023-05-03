public class TreeNode {

        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;
        public int data ;
        public NodeColor color;

        public TreeNode(int val)

        {

            this.data = val;
             this.color=NodeColor.RED;

        }
}
