
/**
 * 
 * 
 * 
 * 
 * Stack을 정렬하는 함수를 만드시오. 
 * 단, 하나의 Stack을 추가로 사용할 수 있고, 
 * Array 등 다른 데이터 구조를 사용할수 없음 
 * 
 *  
 */

import java.util.EmptyStackException;

public class Stack3 {

    public static class Stack<T> { // static 쓰는거 유의하자
        class Node<T> {
            T data = null;
            Node<T> next = null;

            Node(final T d) {
                this.data = d;
            }
        }

        Node<T> top = null; // head가 아니라 top이라 표현하네.

        public Node<T> insertBefore(final Node<T> n, final T d) { // 이건 틀림.
            final Node<T> nn = new Node<T>(d);
            nn.next = n;
            return nn;
        }

        public int count() {
            int count = 0;
            Node<T> n = top;
            while (n != null) {
                n = n.next;
                count++;
            }
            return count;
        }

        public void push(final T d) {
            // if (head == null) {
            // head = new Node<T>(d);
            // } else {
            // // insertBefore(head, d);
            final Node<T> nn = new Node<T>(d);
            nn.next = top; // 이거 엄청 중요. 이해하지 못하고 있었음. head의 값을 nn.next에 이미 담았으므로 head에 이후 무엇을 넣든지간에
                           // nn.next와는 별개임.
            top = nn; // 이 방법을 이해하지 못하고 있었음
            // }
        }

        public T pop() {

            // Empty 예외처리 안함
            if (top == null) {
                throw new EmptyStackException();
            }

            final T data = top.data;
            top = top.next;
            return data;
        }

        public T peek() {
            // Empty 예외처리 안함
            if (top == null) {
                throw new EmptyStackException();
            }
            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public void retrieve() {
            Node<T> r = top;
            while (r != null) {
                System.out.println("|" + r.data + "|");
                r = r.next;
            }
            System.out.println(" --");
        }

    }

    public static void sort(final Stack<Integer> s1, final Stack<Integer> s2) { // s2를 인자로 줄 필요도 없다. 내부에서 선언하고 리턴하면 된다..!!!!

        while (s1.isEmpty() == false) {

            // s1.retrieve();
            // s2.retrieve();
            if (s2.isEmpty()) {
                s2.push(s1.pop());
                continue;
            }
            final int item = s1.pop();
            while (s2.isEmpty() == false && item <= s2.peek()) { // 런타임에러!!! 여기서 s2가 비었는지 체크 안하니까 비었는데도 peek을해서 Empty
                                                                 // Exception이 났다.
                s1.push(s2.pop());
            }
            s2.push(item);
        }
    }

    public static void main(final String[] args) {

        final Stack<Integer> s1 = new Stack<Integer>();
        final Stack<Integer> s2 = new Stack<Integer>();

        s1.push(5);
        s1.push(1);
        s1.push(3);
        s1.push(2);
        s1.push(6);
        s1.push(8);

        // System.out.println(s1.count());
        sort(s1, s2);
        s2.retrieve();

    }
}