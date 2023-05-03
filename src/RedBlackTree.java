//collaborated with  GeeksforGeeks , Programiz.com , CODESDOPE.COM,
public class RedBlackTree {

    TreeNode root;

    public void getRoot() //return the root
    {
        if (root != null)
        {
            System.out.println("Root of the tree is :" + root.data);
        }
        return;
    }

    //similar to Binary Search Tree insertion.
    // to make it RBT , we will add fixup function later.
    public void insertNode(int val){
        TreeNode newNode = new TreeNode(val);

        if(root==null)
        {
            root = newNode;
            root.color=NodeColor.BLACK;
            return;
        }

        TreeNode prev = null;
        TreeNode curr = root;

        while(curr!=null)
        {
            prev = curr;
            if (curr.data > val) //value small goes left
            {
                curr = curr.left;
            } else
            {
                curr = curr.right;
            }
        }
        // we got the place to insert the node
        // prev is one before curr. newnode at curr so we need to point prev to newnode to connect it
            newNode.parent =prev;
            if(val>prev.data)
            {
               prev.right = newNode;
            }
            else
            {
                prev.left = newNode;
             }
        insertionFixup(newNode);
        System.out.println("Insertion successful");
}

     //copied from various websites and clrs book.
    // this is used on BST insertion to make it a RB Tree.
    private void insertionFixup(TreeNode node)
    {

        while(node.parent!=null && node.parent.color==NodeColor.RED)
        { // to check if the parent is black or not, if not continue

            if(node.parent== node.parent.parent.left)
            {
                /// if the node's parent is left child

                TreeNode uncle = node.parent.parent.right;

                if(uncle!=null && uncle.color == NodeColor.RED)
                {
                    // case 1 :  parent and its cousin (uncle)  is red
                    // so we will recolor it to B
                    uncle.color = NodeColor.BLACK;
                    node.parent.color = NodeColor.BLACK;

                    // color GP (G parent)  red and make it the x
                    node.parent.parent.color = NodeColor.RED;

                    node = node.parent.parent;
                }

                else
                {
                    if(node == node.parent.right)
                    {
                        // case 2 : parent is red and uncle is null or black and node is the right child
                        node = node.parent;
                        leftRotation(node);

                    }
                    // if node is the right child
                    node.parent.color = NodeColor.BLACK;
                    node.parent.parent.color = NodeColor.RED;
                    rightRotation(node.parent.parent);
                }

            }
            else
            {
                // if node's parent is right child

                TreeNode uncle = node.parent.parent.left;
                if(uncle!=null && uncle.color == NodeColor.RED)
                {
                    // case 1 : when parent and uncle is red
                    // recolor to black
                    uncle.color = NodeColor.BLACK;
                    node.parent.color = NodeColor.BLACK;
                    // color grand parent red and make it the x
                    node.parent.parent.color = NodeColor.RED;
                    node = node.parent.parent;
                }

                else
                {
                    if(node == node.parent.left){
                        // case 2 : parent is red and uncle is null or black and node is the right child
                        node = node.parent;
                        rightRotation(node);
                    }
                    // if node is the right child
                    node.parent.color = NodeColor.BLACK;
                    node.parent.parent.color = NodeColor.RED;
                    leftRotation(node.parent.parent);
                }
            }
        }
        root.color =NodeColor.BLACK;
    }


    public void leftRotation(TreeNode x )
    {
        TreeNode y =x.right;

        x.right = y.left;// this is after rotation. after rotation x ke right mein , y ka left aaega ( to balance the tree)
        if (y.left!=null )
            y.left.parent = x; // because upar y.left ko x ke right mein daala, this means uska parent bhi change hoga( to balance the tree)

        //  x.left remains SAME
        // y.right remains SAME

        y.parent= x.parent ; // jo pehele x ka parent tha, ab y ka parent hai

        if (x.parent == null ) // x parent null means x root tha pehele . ab y x ki jagah aagaya and y becomes the root now
            this.root = y ;

        else if (x==x.parent.left) // x left child tha apne parent ka , toh x.parent.left ko y se connect  kardo
            x.parent.left= y;

        else // means x is the right child, toh x.parent.right se y ko connect kardo
            x.parent.right= y;

        // now make x as the LEFT child of Y
        y.left=x;
        x.parent=y;

    }


    public void rightRotation(TreeNode x ) //collaborated from Programiz
    {
        TreeNode y = x.left;
        x.left = y.right;

        if (y.right != null)
            y.right.parent = x;

        y.parent = x.parent;

        if (x.parent == null)
            this.root = y;


        else if (x == x.parent.right)
            x.parent.right = y;

        else
            x.parent.left = y;

        y.right = x;
        x.parent = y;
    }


    public void printRoot()
    {

       System.out.println("Root is ");
       printingTree(root,0);
    }

    private void printingTree(TreeNode node,int space)
    {

        int countBW =5;
        if (node == null)
            return;

        // Inc dist btwn levels
        space += countBW;

        // Process right child first
        printingTree(node.right, space); // RIGHT

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = countBW; i < space; i++)
            System.out.print(" ");

        String col = node.color.name(); // COLOR

        System.out.print(node.data +col+ "\n");

        // Process left child
        printingTree(node.left, space); // LEFT
    }


    public int minValOutput()
    {
        TreeNode cur = root;

        if(cur==null){
            return -1;
        }

        while (cur.left !=null)
        {
            cur=cur.left;
        }

        return cur.data;
    }


    public int maxValOutput()
    {
        TreeNode cur = root;
        if(cur==null)
            return -1 ;

        while (cur.right !=null)
            cur=cur.right;

        return cur.data;
    }




    public void predecessor(int x)
    {

        //P of X will be in the left subtree , rightmost element (max element)
        if (root == null)
        {
            System.out.println("Tree is empty");
            return;
        }

        int z= minValOutput();
        if (z==x)
        {
            System.out.println("this is minimum, no predecessor can be found for this ");
            return;
        }

        TreeNode cur = root;
        while(cur!=null)
        {

            if(cur.data==x)
                break;

            else if (cur.data > x)
            {
                cur = cur.left;
            }

            else

                cur = cur.right;

        }

        if(cur==null)
        {
            System.out.println("No key found for "+x);
            return;
        }

        if (cur.left!= null) {
            TreeNode leftmax = nodemaximum(cur.left);
            System.out.println(nodemaximum(cur.left).data);
            return;
        }

        TreeNode y = cur.parent;
        while (y != null && y.left == cur )
        {
            cur=y ;
            y=y.parent;
        }
        System.out.println(y.data);

    }






    public TreeNode nodemaximum(TreeNode node)
    {
        while (node.right != null )
            node=node.right;

        return node;
    }

    public TreeNode nodeminimum(TreeNode node)
    {
        while (node.left!=null)
            node=node.left;

        return node;
    }




    public void successor (int x)
    {

        if (root == null)
        {   System.out.println("Tree is empty");
            return;
        }

        int z= maxValOutput();
        if (z==x)
        {
            System.out.println("this is maximum, no successor can be found for this ");
            return;
        }


        TreeNode cur = root;
            while(cur!=null){
                if(cur.data==x){
                    break;
                }
                else if (cur.data>x){
                    cur=cur.left;
                }
                else {
                    cur = cur.right;
                }
            }
            if(cur==null){
                System.out.println("No key found for "+x);
                return;
            }


        //successor of X will be in the right subtree , leftmost element (minimum element)
        if (cur.right !=null) {    // CASE -- 1
            System.out.println(nodeminimum(cur.right).data);
        return;
        }
        //CASE --2  if no right subtree
        TreeNode y = cur.parent;
        while (y != null && y.right == cur )
        {
            cur=y ;
            y=y.parent;
        }
        System.out.println(y.data);
        return;

    }


    public TreeNode search (TreeNode node , int key)
    {
        if (node == null || node.data ==key )
            return node; //found

        if (node.data < key ) // means key is in right side
            return search(node.right, key );

        return search(node.left , key );
    }

    int searchValOutput(int data)
    {
        root = search(root, data);
        if ( search(root,data) !=null)
        {
            System.out.println("found my element "+ root.data);
            return root.data;
        }

        else
            return -1;
    }


    //same as bst inorder sorting LEFT ROOT RIGHT
    public void sorting(){
        System.out.println("Inorder Sorting: ");
        inorderSort(root);
    }

    private void inorderSort(TreeNode node)
    {
        if (node != null)
        {
            inorderSort(node.left);

            System.out.print(node.data + " ");

            inorderSort(node.right);
        }
    }


}

