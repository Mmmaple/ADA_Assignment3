
package question1;


public class RBTreeTest {
    private static final int a[] = {7, 13, 8, 6, 27, 11, 22, 18};
    public static void main(String[] args) {
        int i, len = a.length;
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
        System.out.println("--the numbers is : ");
        for (int j = 0; j < len; j++) {
            System.out.print(a[j] + ", ");
        }
        System.out.println("");
        for (int j = 0; j < len; j++) {
            tree.add(a[j]);
            tree.minGap();
//            System.out.print(a[j] + ", ");
//            tree.printTree();
        }
        tree.printTree();
        System.out.println("the minGap of the tree is " + tree.getRoot().minGap);
    }
}
