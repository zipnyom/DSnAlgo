
/**
 * 
 * 정렬이 되어있고, 고유한 정수로만 이루어진 배열이 있다. 이 배열로 이진검색트리를 구현하시오.
 * 
 * 
 * 두 수의 중간값을 찾는 데 뺄셈을 사용해서 코드가 길어졌다.
 * (a+b)/2로 중간값을 찾을 수 있음
 * 
 */

class Tree3 {

    static class Node {
        int data;
        public Node left;
        public Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node makeBSTree(int[] arr, int start, int end) {

        if (end < start) { // start가 앞에 오는게 더 자연스러움
            return null;
        }

        // int length = end - start + 1;
        // if (length == 1) {
        // return new Node(arr[end]);
        // }

        // int middle;
        // int gap;
        // if (length % 2 == 0) {
        // gap = length / 2 - 1;
        // } else {
        // gap = length / 2;
        // }
        // middle = start + gap;

        int middle = (start + end) / 2;

        Node parent = new Node(arr[middle]);
        Node left = makeBSTree(arr, start, middle - 1);
        Node right = makeBSTree(arr, middle + 1, end);

        parent.left = left;
        parent.right = right;

        return parent;

    }

    public static void inorder(Node root) {
        if (root != null) {
            System.out.println(root.data);
            inorder(root.left);
            inorder(root.right);
        }
    }

    public static void searchBTree(Node n , int find) {

        if(n.data < find) {
            System.out.println(find + " is bigger than " +n.data);
            searchBTree(n.right, find);
        }else if (n.data > find) {
            System.out.println(find + " is smaller than " +n.data);
            searchBTree(n.left, find);
        }else{
            System.out.println(find + " is found!");
        }
    }

    public static void main(String[] args) {

        int[] data = new int[10];

        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        Node root = makeBSTree(data, 0, data.length - 1);

        searchBTree(root, 6);
        // inorder(root);

    }

}