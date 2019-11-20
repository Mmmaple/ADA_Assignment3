
package question1;
import java.util.*;
import sun.security.util.Length;


public class RedBlackTree<T extends Comparable<T>> {
    private Node<T> root;
    /**
     * get the root
     */
    public Node<T> getRoot(){
        return root;
    }
    /**
     * add
     */
    public void add(T key){
        Node<T> newNode = new Node<T>(key);
        add(this, newNode);
    }
    /**
     * contain
     */
    public Node contain(T aKey){
        return contain(this.root, aKey);
    }

    /**
     * print out the tree
     */
    public void printTree(){
        if (root != null) {
            print(root, root.key, 0);
        }
    }
    
    /**
     * add newNode to a tree
     * @param rbt
     * @param newNode
     * @return 
     */
    private void add(RedBlackTree<T> rbt, Node<T> z) {
        Node<T> y = null;
        Node<T> x = rbt.root;
        //find the place for putting the node
        while (x != null) {            
            y = x;
            if (z.key.compareTo(x.key) < 0) {
                x = x.left;
            }else{
                x = x.right;
            }
        }
        //add the node
        z.parent = y;
        if (y == null) {
            rbt.root = z;
        }else if (z.key.compareTo(y.key) < 0) {
            y.left = z;
        }else{
            y.right = z;
        }
        z.left = null;
        z.right = null;
        z.color = true;//true for Red
        add_fixup(rbt, z);

        
    }

    /**
     * find if aKey is in the tree or not
     * @param root
     * @param aKey
     * @return True if contain; False if not contain
     */
    private Node<T> contain(Node<T> root, T aKey) {
        if (root == null) {
            return null;
        }
        //if aKey is smaller than current node, go left subtree
        if (aKey.compareTo(root.key) < 0) {
            return contain(root.left, aKey);
        }
        //if aKey if bigger than current node, go right subtree
        else if (aKey.compareTo(root.key) > 0) {
            return contain(root.right, aKey);
        }
        //find it
        else{
            System.out.println("yes, this tree contain the key");
            return root;
        }
    }
    
    /**
     *          x                      y
     *        /   \                  /    \
     *      a      y     -->       x      c
     *    /  \    / \             / \     /\
     *            b   c          a     b
     * 
     * @param rbt: this tree
     * @param x :the node need to go down
     */
    private void left_rotate(RedBlackTree<T> rbt, Node<T> x){ 
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null){
            rbt.root = y;
        }else{
            if (x == x.parent.left) {
                x.parent.left = y;
            }else{
                x.parent.right = y;
            }    
        }
        y.left = x;
        x.parent = y;
    }
    
    /**
     *          y                      x
     *        /   \                  /   \
     *      x      c     -->       a      y
     *    /  \    / \             / \    / \
     *   a   b                           b  c
     * 
     * @param rbt:this tree
     * @param y :the node need to go down
     */
    private void right_rotate(RedBlackTree<T> rbt, Node<T> y){
        Node<T> x = y.left;
        y.left = x.right;
        if (x.right != null){
            x.right.parent = y;
        }
        x.parent = y.parent;
        if(y.parent == null){
            rbt.root = x;
        }else{
            if (y == y.parent.left) {
                y.parent.left = x;
            }else{
                y.parent.right = x;
            }
        }
        x.right = y;
        y.parent = x;
    }
    /**
     *              c(black)                                       z:c(red)
     *            /   \                                           /     \
     *        b(red)  y:d(red)      case one-->              b(black)    d(black)
     *         / \    / \                                    /     \       /  \
     *    z:a(red)                                       a(red)
     * 
     *              c(black)                                c(black)                                   b(black)
     *              /       \                              /     \                                      /     \
     *           a(red)             case two-->         b(red)                  case three-->       z:a(red)   c(red)
     *          /    \                                 /     \
     *                z:b(red)                      z:a(red)
     * 
     * 
     * 
     * @param rbt
     * @param z 
     */
    
    private void add_fixup(RedBlackTree<T> rbt, Node<T> z){
        Node<T> y;
        while((z.parent != null) && (z.parent.color == true)){
            //since z.parent is RED it is not the root of tree so z has grandparent
            if (z.parent == z.parent.parent.left) { //z.parent is left child
                y = z.parent.parent.right; //y is uncle of z or null
                if ((y != null) && (y.color == true)) {  //case one
                    z.parent.color = false;
                    y.color = false;
                    z.parent.parent.color = true;
                    z = z.parent.parent; 
                    continue;//repeat loop again
                }else{
                    if (z == z.parent.right) {  //case two
                        z = z.parent;
                        left_rotate(rbt, z);
                    }
                    z.parent.color = false; //case three
                    z.parent.parent.color = true;
                    right_rotate(rbt, z.parent.parent);
                }
            }else{//z.parent is right child
                y = z.parent.parent.left; //y is uncle of z or null
                if ((y != null) && (y.color == true)) {
                    z.parent.color = false;
                    y.color = false;
                    z.parent.parent.color = true;
                    z = z.parent.parent;
                    continue;//repeat loop again!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }else{
                    if (z == z.parent.left) {
                        z = z.parent;
                        right_rotate(rbt, z);//!!!!!!!!!!!!!!!!!!!!!!!!!!
                    }
                    z.parent.color = false;
                    z.parent.parent.color = true;
                    left_rotate(rbt, z.parent.parent);//!!!!!!!!!!!!!!!!!!!!!!!!!
                }
            }
        }
        rbt.root.color = false;
    }

    /**
     * 
     * @param root
     * @param key
     * @param direction:0,the node is root
     *                   -1,the node is its parent's left child
     *                   1,the node is its parent's right child
     */
    private void print(Node<T> root, T key, int direction) {
        if(root != null){
            if (direction == 0) {
                System.out.println(root.key + "(B) is root");
            }else{
                System.out.printf("%2d(%s) is %2d's %6s child--minGap: %2d\n", root.key, root.color?"R":"B", key, direction==1?"right" : "left", root.minGap);
            }
            print(root.left, root.key, -1);
            print(root.right, root.key, 1);
        }
    }
    /**
     * update the minGap which is between the two closest nodes
     */
    public void minGap(){
        ArrayList<Node> nodeArr = new ArrayList<>();
        inOrder(root, nodeArr);
        
        int[] arr = new int[nodeArr.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) nodeArr.get(i).key;
        }
        
        for (int i = 1; i < arr.length - 1; i++) {
            Node<T> currentNode = nodeArr.get(i);
            
            //update the minGap for the smallest node
            nodeArr.get(0).minGap = Math.abs(arr[i] - arr[i - 1]);
            
            //update the minGap for the biggest node
            nodeArr.get(arr.length-1).minGap = Math.abs(arr[arr.length-1] - arr[arr.length-2]);
            
            //difference between nearby node
            int gap1 = Math.min(Math.abs(arr[i] - arr[i - 1]), Math.abs(arr[i + 1] - arr[i]));
            
            int gap2;
            //child's minGap
            if ((currentNode.left != null) && (currentNode.right != null)) {
                gap2 = Math.min(nodeArr.get(i).left.minGap, nodeArr.get(i).right.minGap);
            }else if((currentNode.left != null) && (currentNode.right == null)){
                gap2 = currentNode.left.minGap;
            }else if ((currentNode.left == null) && (currentNode.right != null)) {
                gap2 = currentNode.right.minGap;
            }else{
                gap2 = currentNode.minGap;
            }
            
            nodeArr.get(i).minGap = Math.min(gap1, gap2);
        }
        
    }
    /**
     * inorder traversal
     */
    private void inOrder(Node<T> root, ArrayList<Node> nodeArr){
        if (root != null) {
            inOrder(root.left, nodeArr);
            nodeArr.add(root);
            inOrder(root.right,nodeArr);
        }
    }
}
