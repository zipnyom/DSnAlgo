import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * 이진 검색트리에서 주어진 노드의 다음 노드를 찾는 함수를 구현하시오. 단, 다음 노드의 순서는 inorder traverse에 입각함
 * 
 * 
 */

class Tree7 {

    static class Node {
        int data;
        public Node left = null;
        public Node right = null;
        public Node parent = null;

        Node(final int data) {
            this.data = data;
        }

        Node(final int data, Node parent) {
            this.data = data;
            this.parent = parent;
        }
    }

    static class Tree {

        public Node root;
        int index;
        Node[] nodes;

        Tree(int size) {
            nodes = new Node[size];
            this.root = makeBST(0, size - 1, null, nodes);
            // root.left.left.left = new Node(-1);
        }

        public Node makeBST(final int start, final int end, Node parent, Node[] nodes) {

            if (start > end)
                return null;
            final int middle = (start + end) / 2;
            Node node = new Node(middle, parent);
            nodes[middle] = node;
            node.left = makeBST(start, middle - 1, node, nodes);
            node.right = makeBST(middle + 1, end, node, nodes);
            return node;

        }

        public Node get(int i) {
            return nodes[i];
        }

        // public void inorder(final Node root) {
        // if (root != null) {
        // inorder(root.left);
        // System.out.println(root.data);
        // inorder(root.right);
        // }
        // }

        // 나는 아래와 같이 구현했고, 선생님은 findAbove, findBelow 재귀함수를 이용해서 구현하심)
        public Node findNextNode(Node n) {

            if (n == null)
                return null;

            if (n.right == null) {
                while (n.parent != null) {
                    Node parent = n.parent;
                    if (parent.left == n) {
                        return parent;
                    }
                    n = n.parent;
                }
                return null;
            } else {
                Node right = n.right;
                while (right.left != null) {
                    right = right.left;
                }
                return right;
            }
        }

        public void findNext(Node n) {
            if (n.right == null) {
                System.out.println(findAbove(n.parent, n).data + " is " + n.data + "'s next");
            } else {
                System.out.println(findBelow(n.right).data + " is " + n.data + "'s next");
            }
        }

        // 응용. 이전 노드를 찾는 함수를 구현해보자.
        public void findBefore(Node n) {
            if (n.left == null) {
                System.out.println(findAbove2(n.parent, n).data + " is " + n.data + "'s before");
            } else {
                System.out.println(findBelow2(n.left).data + " is " + n.data + "'s before");
            }
        }

        Node findBelow2(Node child) {
            // if (child.left == null) {
            if (child.right == null) { // 여기서 실수. right가 없을때까지 파고들어야한다.
                return child;
            } else {
                return findBelow2(child.right); // 첫번째 노드에서만 왼쪽으로 가고 그 이후에 계속 오른쪽으로 가야하는 원리를 제대로 파악하지못함
            }
        }

        Node findAbove2(Node parent, Node node) {
            if (parent == null) {
                return null;
            }
            if (parent.right == node) {
                return parent;
            } else {
                return findAbove2(parent.parent, parent);
            }
        }

        Node findAbove(Node parent, Node n) {
            if (parent == null) {
                return null;
            }
            if (parent.left == n) {
                return parent;
            } else {
                return findAbove(parent.parent, parent);
            }
        }

        Node findBelow(Node child) {
            if (child == null) {
                return null;
            }
            if (child.left == null) {
                return child;
            } else {
                return findBelow(child.left);
            }
        }
    }

    public static void main(final String[] args) {

        Tree tree = new Tree(10);

        // System.out.println(tree.findNextNode(tree.get(0)).data);
        // System.out.println(tree.findNextNode(tree.get(1)).data);
        // System.out.println(tree.findNextNode(tree.get(2)).data);
        // System.out.println(tree.findNextNode(tree.get(3)).data);
        // System.out.println(tree.findNextNode(tree.get(4)).data);
        // System.out.println(tree.findNextNode(tree.get(5)).data);
        // System.out.println(tree.findNextNode(tree.get(6)).data);
        // System.out.println(tree.findNextNode(tree.get(7)).data);
        // System.out.println(tree.findNextNode(tree.get(8)).data);
        tree.findNext(tree.get(0));
        tree.findNext(tree.get(1));
        tree.findNext(tree.get(2));
        tree.findNext(tree.get(5));
        tree.findNext(tree.get(7));

        tree.findBefore(tree.get(1));
        tree.findBefore(tree.get(2));
        tree.findBefore(tree.get(5));
        tree.findBefore(tree.get(7));
        tree.findBefore(tree.get(8));
        tree.findBefore(tree.get(9));

    }

}