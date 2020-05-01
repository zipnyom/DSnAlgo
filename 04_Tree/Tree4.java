import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * 이진트리의 노드들을 각 레벨별로 LinkedList에 담는 알고리즘을 구현하시오.
 * 
 * 
 */

class Tree4 {

    static class Node {
        int data;
        public Node left;
        public Node right;

        Node(final int data) {
            this.data = data;
        }
    }

    public static class Queue<T> { // static 쓰는거 유의하자

        
        class Node<T> {
            T data = null;
            Node<T> next = null;

            Node(final T d) {
                this.data = d;
            }
        }

        Node<T> head = null;
        Node<T> first = null;
        Node<T> last = null;

        public void add(final T d) {
            final Node<T> el = new Node<T>(d);
            if (last != null) {
                last.next = el;
                last = el;
            } else {
                first = last = el;
            }
        }

        public T remove() {

            if (first == null) {
                throw new NoSuchElementException();
            }

            final T data = first.data;
            first = first.next;

            if (first == null) {
                last = null;
            }
            return data;

        }

        public T peek() {
            if (first == null) {
                throw new NoSuchElementException();
            }
            return first.data;
        }

        public boolean isEmpty() {
            return first == null;
        }

    }
   
    public static Node makeBST(final int start, final int end) {

        if (start > end) { // start가 앞에 오는게 더 자연스러움
            return null;
        }
        final int middle = (start + end) / 2;
        final Node parent = new Node(middle);
        parent.left = makeBST(start, middle - 1);
        parent.right = makeBST(middle + 1, end);
        return parent;

    }

    public static void inorder(final Node root) {
        if (root != null) {
            System.out.println(root.data);
            inorder(root.left);
            inorder(root.right);
        }
    }

    public static void searchBTree(final Node n, final int find) {

        if (n.data < find) {
            System.out.println(find + " is bigger than " + n.data);
            searchBTree(n.right, find);
        } else if (n.data > find) {
            System.out.println(find + " is smaller than " + n.data);
            searchBTree(n.left, find);
        } else {
            System.out.println(find + " is found!");
        }
    }


    //재귀적으로 푸는 함수
    public static void BST2List(final Node n, final LinkedList<LinkedList<Node>> lists, final int level) {

        if (n == null)
            return;
        // if (lists.size() < level) { // 이 부분을 잘못생각함.
        if (lists.size() == level) {
            final LinkedList<Node> list = new LinkedList<>();
            list.add(new Node(n.data));
            lists.add(list);
        } else {
            final Node item = new Node(n.data);
            // lists.get(level - 1).add(item); 위 부분을 잘못생각해서 - 1 이 나옴
            lists.get(level).add(item);
        }
        BST2List(n.left, lists, level + 1);
        BST2List(n.right, lists, level + 1);
    }


    // 큐를 이용해서 푸는 함수
    public static void BST2List2(final Node n, final LinkedList<LinkedList<Node>> lists) {

        final Queue<Node> q = new Queue<>();

        if( n != null) {
            final LinkedList<Node> list = new LinkedList<Node>();
            list.add(n);
            q.add(n);
        }
        while(q.isEmpty() == false) {

            final Node rm = q.remove();
            if(rm.left != null || rm.right != null)  {
                final LinkedList<Node> list = new LinkedList<Node>();
                if(rm.left != null) {
                    list.add(rm.left);
                }
                if(rm.right != null) {
                    list.add(rm.right);
                }
            }            
        
        }


    }

    public static void printLists(final LinkedList<LinkedList<Node>> lists) {
        for (final LinkedList<Node> list : lists) {
            for (final Node n : list) {
                System.out.print(n.data + " - ");
            }
            System.out.println();
        }
    }

    public static void main(final String[] args) {

        final Node root = makeBST(0, 9);
        final LinkedList<LinkedList<Node>> lists = new LinkedList<LinkedList<Node>>();
        // searchBTree(root, 6);
        BST2List(root, lists, 0);
        printLists(lists);
        // inorder(root);

    }

}