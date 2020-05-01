
public class Tree1 {

    static class Node<T> {

        public Node<T> left;
        public Node<T> right;
        public T data;

        Node(T d) {
            this.data = d;
        }

        Node(T d, Node<T> l, Node<T> r) {
            this.data = d;
            this.left = l;
            this.right = r;
        }

        Node(T d, T l, T r) {
            this.data = d;
            this.left = new Node<T>(l);
            this.right = new Node<T>(r);
        }
    }

    static class Tree<T> {
        Node<T> root;

        public void setRoot(Node<T> n) {
            this.root = n;
        }

        public Node<T> getRooot() {
            return this.root;
        }

        void inorder(Node<T> root) {
            if(root == null) {  // 예외처리 빠트림
                return;
            }
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);
        }

        void preorder(Node<T> root) {
            if(root == null) {
                return;
            }
            System.out.println(root.data);
            preorder(root.left);
            preorder(root.right);
        }

        void postorder(Node<T> root) {
            if(root == null) {
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.println(root.data);
        }

    }

    public static void main(String[] args) {

        Node<Integer> n1 = new Node<Integer>(5, 6, 7);

        Node<Integer> n2 = new Node<Integer>(2, 3, 4);

        Node<Integer> root = new Node<Integer>(1, n1, n2);

        Tree<Integer> tree = new Tree<Integer>();
        tree.setRoot(root);
        tree.postorder(tree.getRooot());

    }
}