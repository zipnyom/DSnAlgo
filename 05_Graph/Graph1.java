import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 
 * 
 * self edge
 * 
 * 
 * Adjacency Matrix Adjacency List ==> 총 Node의 개수는 edge의 2배
 * 
 * 
 * DFS inorder/preorder/postorder가 여기에 해당 BFS
 * 
 */
class Graph1 {

    public static class Queue<T> { // static 쓰는거 유의하자
        class Node<T> {
            T data = null;
            Node<T> next = null;

            Node(T d) {
                this.data = d;
            }
        }

        Node<T> head = null;
        Node<T> first = null;
        Node<T> last = null;

        public void add(T d) {
            Node<T> el = new Node<T>(d);
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

            T data = first.data;
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

    static class Node {
        int data;
        boolean marked;
        LinkedList<Node> adjacent;

        Node(int data) {
            this.data = data;
            this.marked = false;
            this.adjacent = new LinkedList<>();
        }
    }

    static class Graph {
        Node[] nodes;

        Graph(int num) {
            nodes = new Node[num];
            for (int i = 0; i < num; i++) {
                nodes[i] = new Node(i);
            }
        }

        public void initNodes() {
            for (Node n : nodes) {
                n.marked = false;
            }
        }

        public void addEdge(int i, int j) {
            addEdge(nodes[i], nodes[j]);
        }

        void addEdge(Node n1, Node n2) {

            if (n1.adjacent.contains(n2) == false) {
                n1.adjacent.add(n2);
            }
            if (n2.adjacent.contains(n1) == false) {
                n2.adjacent.add(n1);
            }
        }

        public void dfs() {
            dfs(0);
        }

        public void dfs(int n) {
            dfs(nodes[n]);
        }

        void dfs(Node n) {
            Stack<Node> s = new Stack<Node>();
            n.marked = true;
            s.push(n);
            while (s.isEmpty() == false) {
                Node pn = s.pop();
                for (Node an : pn.adjacent) {
                    if (an.marked == false) {
                        an.marked = true;
                        s.push(an);
                    }
                }
                System.out.println(pn.data);
            }
        }

        public void bfs() {
            bfs(0);
        }

        public void bfs(int n) {
            bfs(nodes[n]);
        }

        public void bfs(Node n) {

            Queue<Node> q = new Queue<>();
            n.marked = true;
            q.add(n);
            while (q.isEmpty() == false) {
                Node rm = q.remove();
                for (Node an : rm.adjacent) {
                    if (an.marked == false) {
                        an.marked = true;
                        q.add(an);
                    }
                }
                System.out.println(rm.data);
            }
        }

        public void dfsRNum(int i) {
            dfsR(nodes[i]);
        }

        public void dfsR(Node n) {
            if (n.marked == false) {
                n.marked = true;
                visit(n);
                for (Node an : n.adjacent) {
                    dfsR(an);
                }
                
            }
        }

        public void visit(Node n) {
            System.out.println(n.data);
        }

    }

    public static void main(String[] args) {

        Graph g = new Graph(9);

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 8);

        // g.dfs();
        g.dfsRNum(0);

    }

}