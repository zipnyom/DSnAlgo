import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * 이진 검색트리에서 주어진 노드의 첫번째 공통된 부모 노드를 찾으시오. (단, 다른 자료구조 사용금지)
 *
 * 
 * 
 * 각 노드별로 parent를 아는 경우와 알 지 않는 경우로 일단 구분
 * 
 * 
 * 
 */

class Tree8 {

    // static class Node {
    // int data;
    // public Node left = null;
    // public Node right = null;
    // public Node parent = null;

    // Node(final int data) {
    // this.data = data;
    // }

    // Node(final int data, Node parent) {
    // this.data = data;
    // this.parent = parent;
    // }
    // }

    // static class Tree {

    // public Node root;
    // int index;
    // Node[] nodes;

    // Tree(int size) {
    // nodes = new Node[size];
    // this.root = makeBST(0, size - 1, null, nodes);
    // // root.left.left.left = new Node(-1);
    // }

    // public Node makeBST(final int start, final int end, Node parent, Node[]
    // nodes) {

    // if (start > end)
    // return null;
    // final int middle = (start + end) / 2;
    // Node node = new Node(middle, parent);
    // nodes[middle] = node;
    // node.left = makeBST(start, middle - 1, node, nodes);
    // node.right = makeBST(middle + 1, end, node, nodes);
    // return node;

    // }

    // public Node get(int i) {
    // return nodes[i];
    // }

    // // 트리의 깊이를 d라고 할 때 O(d)의 시간복잡도

    // public Node findCommonParent(Node n1, Node n2) { // commonAncestor

    // int h1 = getNodeHeight(n1);
    // int h2 = getNodeHeight(n2);

    // System.out.println("h1 : " + h1 + " h2 : " + h2);
    // int gap = Math.abs(h1 - h2);

    // if (h1 > h2) {
    // if (n1.parent == n2)
    // return n2;
    // for (int i = 0; i < gap; i++)
    // n1 = n1.parent;
    // } else if (h1 == h2) {
    // if (n1 == n2) {
    // return n1.parent;
    // }
    // } else {
    // if (n2.parent == n1)
    // return n1;
    // for (int i = 0; i < gap; i++)
    // n2 = n2.parent;
    // }
    // while (n1 != null && n2 != null) {
    // n1 = n1.parent;
    // n2 = n2.parent;
    // if (n1 == n2)
    // return n1;
    // }
    // return null;

    // }

    // // 첫번째 공통 부모의 자식노드 개수를 t라할때 O(t)의 시간복잡도.

    // public Node findCommonParent2(Node n1, Node n2) {

    // Node r1 = n1.parent;
    // Node r2 = n1;
    // if (r1 == n2)
    // return n2.parent;

    // while (r1 != null) {
    // if (r1.left == r2) {
    // if (searchBelow(r1.right, n2))
    // return r1;
    // } else {
    // if (searchBelow(r1.left, n2))
    // return r1;
    // }
    // r1 = r1.parent;
    // r2 = r2.parent;
    // }
    // return null;

    // }
    // //

    // public boolean searchBelow(Node root, Node n) {
    // if (root == null)
    // return false;
    // if (root == n)
    // return true;
    // else {
    // return searchBelow(root.left, n) || searchBelow(root.right, n);
    // }
    // }

    // public int getNodeHeight(Node n) {

    // int level = 0;
    // Node r = n;
    // while (r != this.root) {
    // r = r.parent;
    // level++;
    // }
    // return level;

    // }

    /* 처움부터 5가지 방법 다시 시도해보기 */
    // 1. 두 노드의 높이를 맞추고 함께 거슬러올라가기
    // 2. 한쪽 노드에서 올라가면서 다른 노드를 반대편 자식으로 두고 있는지 부모에게 물어보기
    // 3. root에서 내려가면서 아래 자식 중에 각 노드가 있는지 확인하고, 좌우에 하나씩 있으면 해당 노드가 공통부모
    // 4. post order 사용하기
    // 5. post order + 값을 찾았는지 여부 구조체 사용하기

    // 노드 선언
    static class Node {

        int data;
        Node left;
        Node right;
        Node parent;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node parent) {
            this.data = data;
            this.parent = parent;
        }

    }

    static class Tree {

        public Node root;
        HashMap<Integer, Node> rootMap;

        public Tree(int size) {
            rootMap = new HashMap<>();
            this.root = makeBST(0, size - 1, null);
        }

        public Node makeBST(int start, int end, Node parent) {

            if (start > end)
                return null;
            int mid = (start + end) / 2;
            Node node = new Node(mid);
            node.left = makeBST(start, mid - 1, node);
            node.right = makeBST(mid + 1, end, node);
            node.parent = parent;
            rootMap.put(mid, node);
            return node;

        }

        public void printInOrder(Node n) {
            if (n == null)
                return;
            printInOrder(n.left);
            System.out.print(n.data + " ");
            printInOrder(n.right);
        }

        public Node get(int i) {
            return rootMap.get(i);
        }

        public Node commonAncestor01(int i, int j) {
            Node n1 = get(i);
            Node n2 = get(j);

            int h1 = getHeight(n1);
            int h2 = getHeight(n2);

            if (h1 > h2) {
                n1 = goUpBy(n1, h1 - h2); // 여기서 올라간 노드를 리턴 받아서 다시 저장하는 개념을 까먹었다.
            } else {
                n2 = goUpBy(n2, h2 - h1);
            }

            while (n1 != null && n2 != null) {
                if (n1 == n2) {
                    return n1;
                }
                n1 = n1.parent;
                n2 = n2.parent;
            }
            return null;
        }

        public Node goUpBy(Node n, int num) {
            while (num > 0) {
                n = n.parent;
                num--;
            }
            return n;
        }

        public int getHeight(Node n) {
            int h = 0;
            while (n != null && n.parent != null) {
                n = n.parent;
                h++;
            }
            return h;
        }

        public Node commonAncestor02(int i, int j) {

            Node p = get(i);
            Node q = get(j);
            if (!cover(root, p) || !cover(root, q)) {
                return null;
            } else if (cover(p, q)) {
                return p;
            } else if (cover(q, p)) {
                return q;
            }

            while (p != null && p.parent != null) {
                Node sibling = getSibling(p);
                if (cover(sibling, q)) {
                    return p.parent;
                }
                p = p.parent;
            }
            return null;

        }

        public Node getSibling(Node n) {
            Node p = n.parent;
            if (p.left == n) {
                return p.right;
            } else {
                return p.left;
            }
        }

        public boolean cover(int i, int j) {

            Node n1 = get(i);
            Node n2 = get(j);
            return cover(n1, n2);

        }

        public boolean cover(Node root, Node node) {

            if (root == null)
                return false;
            if (root == node)
                return true;
            if (cover(root.left, node))
                return true;
            if (cover(root.right, node))
                return true;
            return false;

        }

        public Node commonAncestor03(int i, int j) {

            Node p = get(i);
            Node q = get(j);
            if (!cover(root, p) || !cover(root, q)) {
                return null;

            }
            return ancestorHelper(root, p, q);
        }

        public Node ancestorHelper(Node r, Node n1, Node n2) {

            if (r == null || r == n1 || r == n2) // 여기서 r==n1 || r==n2 부분을 생각하지 못함
                return r;
            boolean isLeftN1 = cover(r.left, n1);
            boolean isLeftN2 = cover(r.left, n2);
            if (isLeftN1 != isLeftN2) {
                return r;
            }
            if (isLeftN1) {
                return ancestorHelper(r.left, n1, n2);
            } else {
                return ancestorHelper(r.right, n1, n2);
            }

        }

        public Node commonAncestor04(int i, int j) {

            Node p = get(i);
            Node q = get(j);

            return commonAncestor(root, p, q);

        }

        public Node commonAncestor(Node root, Node p, Node q) {

            if (root == null) {
                return null;
            }
            if (root == p && root == q)
                return root;

            Node x = commonAncestor(root.left, p, q);
            if (x != null && x != p && x != q) {
                return x;
            }
            Node y = commonAncestor(root.right, p, q);
            if (y != null && y != p && y != q) {
                return y;
            }
            if (x != null && y != null) {
                return root;
                // } else if (x == null || y == null) {
                // Node found = x == null ? y : x;
                // if (found == p) {
                // if (root == q)
                // return root;
                // } else {
                // if (root == p)
                // return root;
                // }
                // return found;
                // } else {
                // if (root == p || root == q) {
                // return root;
                // } else {
                // return null;
                // }
                // }
            } else if (root == p || root == q) {
                return root;
            } else {
                return x == null ? y : x;
            }
        }

        public Node postOrder(Node root, Node p, Node q) {
            if (root == null) {
                return null;
            }
            Node left = postOrder(root.left, p, q);
            Node right = postOrder(root.right, p, q);
            if (left == null && right == null) {
                if (root == p || root == q)
                    return root;
                else
                    return null;
            } else if (left == null || right == null) {
                Node found = left == null ? right : left;
                if (found == p) {
                    if (root == q)
                        return root;
                } else if (found == q) {
                    if (root == p)
                        return root;
                }
                return found;
            } else {
                return root;
            }
        }

        class Result {

            Node node;
            boolean hasFound;

            Result(Node node, boolean hasFound) {
                this.node = node;
                this.hasFound = hasFound;
            }

        }

        public Node commonAncestor05(int i, int j) {

            Node p = get(i);
            Node q = get(j);

            Result res = commonAncestor2(root, p, q);
            if (res.hasFound) {
                return res.node;
            }
            return null;
        }

        public Result commonAncestor2(Node root, Node p, Node q) {

            if (root == null) {
                return new Result(null, false);
            }
            if (root == p && root == q)
                return new Result(root, true);

            Result x = commonAncestor2(root.left, p, q);
            if (x.hasFound) {
                return x;
            }
            Result y = commonAncestor2(root.right, p, q);
            if (y.hasFound) {
                return y;
            }
            if (x.node != null && y.node != null) {
                return new Result(root, true);
            } else if (root == p || root == q) {
                boolean hasFound = x.node != null || y.node != null;
                return new Result(root, hasFound);
            } else {
                return x.node == null ? y : x;
            }
        }
        // class Answer {
        // public boolean isFound = false;
        // public Node answer = null;
        // }

        // public Node commonAncestor05(int i, int j) {

        // Node p = get(i);
        // Node q = get(j);

        // Answer answer = new Answer();
        // return postOrderWithAnswer(root, p, q, answer);

        // }

        // public Node postOrderWithAnswer(Node root, Node p, Node q, Answer answer) {
        // if (root == null) {
        // return null;
        // }
        // Node left = postOrderWithAnswer(root.left, p, q, answer);
        // Node right = postOrderWithAnswer(root.right, p, q, answer);
        // if (answer.isFound) {
        // return null;
        // }
        // if (left == null && right == null) {
        // if (root == p || root == q) {
        // return root;
        // } else {
        // return null;
        // }
        // } else if (left == null || right == null) {
        // Node found = left == null ? right : left;
        // if (found == p) {
        // if (root == q) {
        // answer.isFound = true;
        // answer.answer = root;
        // return null;
        // }
        // } else if (found == q) {
        // if (root == p)
        // return root;
        // }
        // return found;
        // } else {
        // answer.isFound = true;
        // answer.answer = root;
        // }
        // }

    }

    // 1. 두 노드의 높이를 맞추고 함께 거슬러올라가기

    // }

    public static void main(final String[] args) {

        Tree tree = new Tree(10);
        // System.out.println(tree.commonAncestor01(0, 9).data);
        // System.out.println(tree.cover(4, 1));
        // System.out.println(tree.commonAncestor02(0, 9).data);
        // System.out.println(tree.commonAncestor03(7, 8).data);
        System.out.println(tree.commonAncestor04(7, 10).data);
        System.out.println(tree.commonAncestor05(7, 10).data);
        // tree.printInOrder(tree.root);
        // System.out.println();
        // System.out.println(tree.getNodeHeight(tree.root.right.right));
        // System.out.println(tree.findCommonParent2(tree.get(0), tree.get(6)).data);
    }

}