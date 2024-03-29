
package question1;





public class Node<T extends Comparable<T>> {
    T key;
    boolean color;//TRUE for Red,FALSE for Black
    int minGap = 999;
    Node<T> left;
    Node<T> right;
    Node<T> parent;
    
    public Node(T key, Node<T> left, Node<T> right){
        this.key = key;
        this.left = left;
        this.right = right;
    }
    
    public Node(T key){
        this(key, null, null);
    }
    
}
