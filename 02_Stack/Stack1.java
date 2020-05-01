import java.util.EmptyStackException;

public class Stack1 {

    public static class Stack<T> { // static 쓰는거 유의하자
        class Node<T> {
            T data = null;
            Node<T> next = null;

            Node(T d) {
                this.data = d;
            }
        }

        Node<T> head = null; // head가 아니라 top이라 표현하네.

        public Node<T> insertBefore(Node<T> n, T d) { // 이건 틀림.
            Node<T> nn = new Node<T>(d);
            nn.next = n;
            return nn;
        }

        public void push(T d) {
            // if (head == null) {
            // head = new Node<T>(d);
            // } else {
            // // insertBefore(head, d);
            Node<T> nn = new Node<T>(d);
            nn.next = head; // 이거 엄청 중요. 이해하지 못하고 있었음. head의 값을 nn.next에 이미 담았으므로 head에 이후 무엇을 넣든지간에
                            // nn.next와는 별개임.
            head = nn; // 이 방법을 이해하지 못하고 있었음
            // }
        }

        public T pop() {

            // Empty 예외처리 안함
            if (head == null) {
                throw new EmptyStackException();
            }

            T data = head.data;
            head = head.next;
            return data;
        }

        public T peek() {
            // Empty 예외처리 안함
            if (head == null) {
                throw new EmptyStackException();
            }
            return head.data;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void retrieve() {
            Node<T> r = head;
            while (r != null) {
                System.out.println("|" + r.data + "|");
                r = r.next;
            }
            System.out.println("--");
        }

    }

    public static void main(String[] args) {

        Stack<Integer> s = new Stack<Integer>();

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
        System.out.println("pop : " + s.pop());
        s.push(3);
        System.out.println("pop : " + s.pop());

    }
}