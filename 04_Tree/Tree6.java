import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * 주어진 트리가 이진 검색 트리인지 확인하는 함수를 구현하시오.
 * 
 * 
 */

class Tree6 {

    static class Node {
        int data;
        public Node left = null;
        public Node right = null;

        Node(final int data) {
            this.data = data;
        }
    }

    static Integer last_printed;

    static class Tree {

        public Node root;
        int size; // array 크기를 정하기 위해서 미리 만듦
        int index;

        Tree(int size) {
            this.root = makeBST(0, size - 1);
            this.size = size;
            root.left.left.left = new Node(-1);
            this.size++;
        }

        public Node makeBST(final int start, final int end) {

            if (start > end)
                return null;
            final int middle = (start + end) / 2;
            final Node parent = new Node(middle);
            parent.left = makeBST(start, middle - 1);
            parent.right = makeBST(middle + 1, end);

            return parent;

        }

        // public void inorder(final Node root) {
        // if (root != null) {
        // inorder(root.left);
        // System.out.println(root.data);
        // inorder(root.right);
        // }
        // }

        public boolean isBST(Node n) {
            return checkBST(Integer.MIN_VALUE, n, Integer.MAX_VALUE);
        }

        boolean checkBST(int min, Node n, int max) {
            if (n == null)
                return true;
            if (min >= n.data)
                return false;
            if (max <= n.data)
                return false;
            return checkBST(Integer.MIN_VALUE, n.left, n.data) && checkBST(n.data, n.right, Integer.MAX_VALUE);
        }

        // 선생님은 inorder탐색을 이용한 구현과 static Integer 변수 하나 두는 방법을 사용하심.

        /**
         * 
         * 
         */

        // array를 이용하면 O(N)의 공간이 사용된다.
        public boolean isValidateBST1() {

            int[] array = new int[size];
            this.index = 0;
            inorder(this.root, array);
            // for (int i = 0; i < this.size - 1; i++) {
            // if (array[i] >= array[i + 1]) {
            // return false;
            // }
            // }
            for (int i = 1; i < this.size; i++) {
                if (array[i - 1] > array[i]) {
                    return false;
                }
            }
            return true;
        }

        void inorder(Node n, int[] array) {
            if (n == null)
                return;
            inorder(n.left, array);
            array[this.index] = n.data;
            this.index++;
            inorder(n.right, array);
        }

        // O(1)의 공간이 사용된다.

        public boolean isValidateBST2() {
            // last_printed = 0; // 이건 트리의 최소값이 0 이상이라는 조건이 있을때에만 유용한 것. null이 되어야한다 원래
            return inorder(this.root);
        }

        boolean inorder(Node n) {
            if (n == null)
                return true;
            if (!inorder(n.left))
                return false; // 여기서 실수. 여기서 리턴하지 않으면 왼쪽의 값들은 무시된다.
            if (last_printed != null && last_printed > n.data) { // 여기서 last_printed가 null인지 체크하는 부분이 있어야 한다.
                return false;
            }
            last_printed = n.data; // null이라면 그냥 데이터 넣고 오른쪽 호출하겠지.
            // return inorder(n.right); 여기서 이것도 좋긴한데, 앞의 패턴을 생각하면 아래와 같이 코딩해야함
            if (!inorder(n.right))
                return false;
            return true;
        }

    }

    public static void main(final String[] args) {

        Tree tree = new Tree(10);

        // tree.inorder(tree.root);
        // System.out.println(tree.isBST(tree.root));
        System.out.println(tree.isValidateBST2());

    }

}