
/**
 * 
 * 
 * 최소값, 최대값을 빠르게 찾기위한 완전이진트리(Complete Binary Tree) O(logN)의 탐색 속도를 가짐.
 * 
 * 문자열의 경우 문열의 최대 길이가 M이라고 할때 O(MlogN)의 시간이걸려버림 Trie는 모든 노드가 최대 26개의 자식을 가지고,
 * O(M)의 검색시간을 가진다.
 * 
 * 
 * 구현은 당장하지않으셨는데 그만큼 구현이 쉽지 않은 것 같다.
 * 다음에 구현하기로.
 * 
 */

class Heap1 {

    static class Node {
        int data;
        Node left;
        Node right;
    }

    static class Heap {

        Node root;
        Node last;

        public Node getRoot() {
            return this.root;
        }
        public void setRoot(Node n) {
            this.root = n;
        }
        
        public void insert() {



        }

        // public Node remove() {



        // }

        
        void preorder(Node root) {
            if(root == null) {
                return;
            }
            System.out.println(root.data);
            preorder(root.left);
            preorder(root.right);
        }


    }

    public static void main(String[] args) {

    }

}