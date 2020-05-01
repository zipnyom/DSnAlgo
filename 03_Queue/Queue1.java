
/****
 * 
 * 
 * 
 * 큐는 앞뒤로 주소를 알고 있어야 한다?
 * 
 * head만 있는 채로 구현하면 마지막에 넣기 위해서 처음부터 계속해서 서치를 해나가야 하므로 비효율적.
 * 
 * 
 * 
 ***/

import java.util.NoSuchElementException;

public class Queue1 {

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
            if (head == null) {
                head = new Node<T>(d);
            } else {
                // insertBefore(head, d);
                Node<T> r = head;
                while (r.next != null) {
                    r = r.next;
                }
                Node<T> nn = new Node<T>(d);
                r.next = nn;
            }
        }

        public void add2(T d) {
            Node<T> el = new Node<T>(d);
            if(last != null) {
                last.next = el;
                last = el;
            }else{
                first = last = el;
            }
        }
        public T remove() {
            // Empty 예외처리 안함
            if (head == null) {
                throw new NoSuchElementException();
            }
            T data = head.data;
            head = head.next;
            return data;
        }
        
        public T remove2() { 

            if(first == null) {
                throw new NoSuchElementException();
            }

            T data = first.data;
            first = first.next;
            
            if(first == null) {
                last = null;
            }
            return data;
            
        }

        public T peek() {
            // Empty 예외처리 안함
            if (head == null) {
                throw new NoSuchElementException();
            }
            return head.data;
        }

        public T peek2() {
            if(first == null) {
                throw new NoSuchElementException();
            }
            return first.data;
        }

        public boolean isEmpty() {
            return first == null;
        }

    }

    public static void main(String[] args) {

        Queue<Integer> s = new Queue<Integer>();

        s.add2(1);
        s.add2(2);
        s.add2(3);
        s.add2(4);

        System.out.println("remove2 : " + s.remove2());
        System.out.println("remove2 : " + s.remove2());
        System.out.println("remove2 : " + s.remove2());
        System.out.println("remove2 : " + s.remove2());
        s.add2(3);
        System.out.println("remove2 : " + s.remove2());

    }
}