
public class linkedlist2 {

    public static class LinkedList {

        Node head = new Node();

        public class Node {
            int data;
            Node next = null;
        }

        public void append(int d) {

            Node n = head;

            while (n.next != null) {
                 n = n.next;
            }
            Node t = new Node();
            t.data = d;
            n.next = t; // end라고 변수명을 지어주면 더 직관적임
        }

        public void delete(int d) {

            Node n = head;

            if (n.next == null) {
                return;
            }

            // while(n.next.next != null) { 다음다음이 Null 이면 안된다고 착각
            while (n.next != null) {
                if (n.next.data == d) {
                    n.next = n.next.next;
                    break;
                }
                n = n.next;
            }
        }

        public void retrieve() {
            Node n = head.next;
            while (n.next != null) {
                System.out.print(n.data + "->");
                n = n.next;
            }
            System.out.println(n.data);
        }

    }

    public static void main(String[] args) {

        LinkedList n = new LinkedList();
        n.append(1);
        n.append(2);
        n.append(3);
        n.append(4);
        n.append(5);
        n.append(6);
        n.append(7);
        n.retrieve();
        n.delete(3);
        n.retrieve();

    }

}