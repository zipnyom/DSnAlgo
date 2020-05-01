
// 정렬되어있지 않은 링크드리스트의 중복 데이터를 제거하시오.
// 단, 버퍼는 사용금지
// 3 -> 2 -> 1 -> 2 -> 4

// 버퍼 사용시 (hash) : 공간 복잡도 O(N), 시간복잡도 O(N)
// 포인터 사용시 : 공간복잡도 O(1), 시간 복잡도 O(N^2)

public class linkedlist5 {

    public static class LinkedList {

        public Node head = new Node();

        public void append(Node n) {

            Node r = head;
            while (r.next != null) {
                r = r.next;
            }
            r.next = n;

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

        // public void removeMiddle(Node n) {
        public boolean removeMiddle(Node n) {// boolean으로 반환하는 방법이있음
            if (n == null || n.next == null) { // 예외처리 빠트림
                return false;
            }

            n.data = n.next.data;
            n.next = n.next.next;
            return true;

        }

    }

    public static class Node {
        int data;
        Node next = null;
    }

    public static class Reference {
        public int count = 0;
    }

    public static void main(String[] args) {

        LinkedList l = new LinkedList();
        l.append(1);
        l.append(2);
        l.append(3);
        l.append(4);

        Node n = new Node();
        n.data = 5;

        // l.append(5);
        l.append(n);
        l.append(6);
        l.append(7);
        l.append(8);

        l.retrieve();
        l.removeMiddle(n);
        l.retrieve();
    }

}