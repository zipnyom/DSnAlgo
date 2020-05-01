import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * 이진트리의 노드들을 각 레벨별로 LinkedList에 담는 알고리즘을 구현하시오.
 * 
 * 
 */

class Tree5 {

    static class Node {
        int data;
        public Node left = null;
        public Node right = null;

        Node(final int data) {
            this.data = data;
        }
    }

    static class Tree {

        public Node root;

        Tree(int size) {
            this.root = makeBST(0, size - 1);
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

        public void inorder(final Node root) {
            if (root != null) {
                inorder(root.left);
                System.out.println(root.data);
                inorder(root.right);
            }
        }

        // 노드가 호출될 때마다 재귀함수 getHight가 호출되므로 O(NlogN)의 시간이 걸림
        public boolean isBalanced(final Node root) { // isBalanced

            if (root == null) {
                return true; // 이것때문에 틀림. false가 아니라 true를 줘야함. 맨 아래 노드까지 도달하면 결국 null이 나올텐데. 그 상위 함수 결과도 다
                             // false가 된다.
            }

            int hightDiff = getHight(root.left) - getHight(root.right);

            // System.out.println("Math.abs(hightDiff) : " + Math.abs(hightDiff));
            if (Math.abs(hightDiff) > 1) {
                return false;
            }

            //////////////
            else {
                return isBalanced(root.left) && isBalanced(root.right);
            }

            ///////////////
            // 위 괄호친 부분을 생각치 못하고 그냥 return true 해버림. 생각조차못했는데.. 흠
            // 재귀적 호출일 때는 반드시 현재 함수 실행 결과가 자신을 호출할 함수에 어떤 영향을 줄지를 생각해야함.
            // return true;
        }

        public int getHight(Node root) {
            if (root == null) {
                return -1;
            }
            return Math.max(getHight(root.left), getHight(root.right)) + 1;
        }

        // 이번에는 끝까지 먼저 간 다음에 그 결과를 정수값으로 반환하는걸로 짜보자.
        // O(N)의 시간이 걸림
        int checkHight(Node n) {
            if (n == null) {
                return -1;
            }
            // int left = checkHight(n.left);
            // int right = checkHight(n.right);
            // if (left == Integer.MIN_VALUE || right == Integer.MIN_VALUE)
            // return Integer.MIN_VALUE;

            // 왼쪽 오른쪽 둘 다 실행한 뒤에 결과를 확인하는 것보다 아래처럼 한쪽 확인하고 반환하는
            // 방법이 훨씬 경제적이다.
            int left = checkHight(n.left);
            if (left == Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            int right = checkHight(n.right);
            if (right == Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            int hightDiff = left - right;
            // System.out.println("Math.abs(hightDiff) : " + Math.abs(hightDiff));
            if (Math.abs(hightDiff) > 1) {
                return Integer.MIN_VALUE;
            } else {
                return Math.max(left, right) + 1;
            }
        }

        public boolean isBalanced2(Node n) {
            return checkHight(n) != Integer.MIN_VALUE;
        }

        // 트리 전체를 두고 밸런스를 체크하는 함수
        // 아래 실패한 코드는 생각 자체를 반대로함.
        // Level 객체를 leaf에서 생성한 뒤 리턴하면서 더해가려고 했는데
        // 선생님은 재귀로 들어가면서 더한 뒤에 끝까지 들어간 곳에서 level의 min max를 비교하는 방식으로 구현하심

        // Level checkGlobalHight(Node n) {
        // if (n == null) {
        // return new Level();
        // }
        // Level left, right;
        // Level result;
        // left = checkGlobalHight(n.left);
        // if (left.max - left.min > 1) {
        // left.result = false;
        // }
        // right = checkGlobalHight(n.right);
        // if (right.max - right.min > 1) {
        // right.result = false;
        // }

        // return Integer.MIN_VALUE;
        // int right = checkGlobalHight(n.right);
        // if (right == Integer.MIN_VALUE)
        // return Integer.MIN_VALUE;
        // int hightDiff = left - right;
        // System.out.println("Math.abs(hightDiff) : " + Math.abs(hightDiff));
        // if (Math.abs(hightDiff) > 1) {
        // return Integer.MIN_VALUE;
        // } else {
        // return Math.max(left, right) + 1;
        // }
        // }

        // public boolean isBalanced3(Node n) {
        // Level s = checkGlobalHight(n);
        // return s.max - s.min > 1 ? false : true;
        // }

        boolean isBalanced3(Node n) {
            Level level = new Level();
            checkBalanced(n, level, 0);
            // System.out.println(level.max + " " + level.min);
            if (Math.abs(level.max - level.min) > 1)
                return false;
            else
                return true;
        }

        void checkBalanced(Node n, Level obj, int level) {
            if (n == null) {
                if (level < obj.min)
                    obj.min = level;
                if (level > obj.max)
                    obj.max = level;
                return; // return 빼먹음!!!!!!!!!
            }
            checkBalanced(n.left, obj, level + 1);
            checkBalanced(n.right, obj, level + 1);
        }

    }

    static class Level {
        int max = Integer.MIN_VALUE; // 이거 거꾸로 줬습니다.. 죄송
        int min = Integer.MAX_VALUE;
    }

    public static void main(final String[] args) {

        Tree tree = new Tree(10);
        tree.root.right.right.right.right = new Node(10);
        tree.root.right.right.left = new Node(11);
        // tree.inorder(tree.root);
        System.out.println(tree.isBalanced(tree.root));
        System.out.println(tree.isBalanced2(tree.root));
        System.out.println(tree.isBalanced3(tree.root));

    }

}