
public class linkedlist {

    public static class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
        }

        public void append(int d) {

            Node n = this;

            while (n.next != null) {
                n = n.next;
            }

            Node t = new Node(d);
            n.next = t; // end라고 변수명을 지어주면 더 직관적임
        }

        public void delete(int d) {

            Node n = this;

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
            Node n = this;
            while (n.next != null) {
                System.out.print(n.data + "->");
                n = n.next;
            }
            System.out.println(n.data);
        }

    }

    public static void main(String[] args) {

        Node n = new Node(1);
        n.append(2);
        n.append(3);
        n.append(4);
        n.retrieve();
        n.delete(4);
        n.retrieve();

    }

}