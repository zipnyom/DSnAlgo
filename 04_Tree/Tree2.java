
/**
 * 
 * 선생님과 가능한 비슷하게 구현하기 위해서 재구현
 */

public class Tree2 {

    static class Node<T> {

        T data;
        public Node<T> left;
        public Node<T> right;

        Node(T data) {
            this.data = data;
        }

    }

    static class Tree<T> {

        Node<T> root;

        public void setRoot(Node<T> n) {
            this.root = n;
        }

        public Node<T> getRoot() {
            return this.root;
        }

        public Node<T> makeChildNode(Node<T> left, T data, Node<T> right) {
            Node<T> n = new Node<T>(data);
            n.left = left;
            n.right = right;
            return n;
        }

        public void inOrderPrint(Node<T> n) {
            if (n != null) {
                inOrderPrint(n.left);
                System.out.println(n.data);
                inOrderPrint(n.right);
            }
        }

        public void preOrderPrint(Node<T> n) {
            if (n != null) {
                System.out.println(n.data);
                preOrderPrint(n.left);
                preOrderPrint(n.right);
            }
        }

        public void postOrderPrint(Node<T> n) {
            if (n != null) {
                postOrderPrint(n.left);
                postOrderPrint(n.right);
                System.out.println(n.data);
            }
        }

    }

    public static void main(String[] args) {

        Tree<Integer> tree = new Tree<Integer>();
        Node<Integer> n5 = tree.makeChildNode(null, 5, null);
        Node<Integer> n4 = tree.makeChildNode(null, 4, null);
        Node<Integer> n3 = tree.makeChildNode(null, 3, null);
        Node<Integer> n2 = tree.makeChildNode(n4, 2, n5);
        Node<Integer> n1 = tree.makeChildNode(n2, 1, n3);

        tree.setRoot(n1);
        tree.postOrderPrint(tree.getRoot());

    }
}