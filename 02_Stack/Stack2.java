
/**
 * 
 * 
 * 
 * 
 * Stack 두 개로 큐만들기..
 * 
 * new, old 스택으로 처리하는 방법 기억
 * 
 */

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class Stack2 {

    public static class Stack<T> { // static 쓰는거 유의하자
        class Node<T> {
            T data = null;
            Node<T> next = null;

            Node(T d) {
                this.data = d;
            }
        }

        Node<T> top = null; // head가 아니라 top이라 표현하네.

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

            T data = top.data;
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
            System.out.println("--");
        }

    }

    public static class Queue<T> { // static 쓰는거 유의하자

        // 생성자 활용하자. 의미를 잘 모르는듯
        Stack<T> _new = new Stack<T>();
        Stack<T> _old = new Stack<T>();

        public void add(T d) {
            _new.push(d);
        }

        public T remove() {
            if (_old.isEmpty()) {
                while (_new.isEmpty() == false) {
                    _old.push(_new.pop());
                }
            }
            // 이미 Stack에 예외처리가 되어있기 때문에 중복이다.
            if (_old.isEmpty()) {
                throw new NoSuchElementException();
            }
            return _old.pop();
        }

        public T peek() {
            if (_old.isEmpty()) {
                while (_new.isEmpty() == false) {
                    _old.push(_new.pop());
                }
            }
            // 이미 Stack에 예외처리가 되어있기 때문에 중복이다.
            if (_old.isEmpty()) {
                throw new NoSuchElementException();
            }
            return _old.peek();
        }

        public boolean isEmpty() {
            return _new.isEmpty() && _old.isEmpty();
        }

    }

    public static void main(String[] args) {

        Queue<Integer> q = new Queue<Integer>();

        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);

        System.out.println("remove : " + q.remove());
        System.out.println("remove : " + q.remove());
        System.out.println("remove : " + q.remove());
        System.out.println("remove : " + q.remove());
        q.add(3);
        System.out.println("peek : " + q.peek());

    }
}