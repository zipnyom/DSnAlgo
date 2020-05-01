
// 정렬되어있지 않은 링크드리스트의 중복 데이터를 제거하시오.
// 단, 버퍼는 사용금지
// 3 -> 2 -> 1 -> 2 -> 4

// 버퍼 사용시 (hash) : 공간 복잡도 O(N), 시간복잡도 O(N)
// 포인터 사용시 : 공간복잡도 O(1), 시간 복잡도 O(N^2)


public class linkedlist3 {

    public static class LinkedList {

        public Node head = new Node();

        private class Node {
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

        public void deleteDuplicates() { // removeDups라고 짧게 쓸 수 있다.

            Node n = head;
            Node r = head;

            while (n != null && n.next != null) { // n이 null이 아닌 조건이 들어가지 않으면, 마지막 값이 중복일 때 (예를 들어 2->2일 때) null pointer exception이 발생.
                while (r != null && r.next != null) {
                    if (n.data == r.next.data) {
                        r.next = r.next.next;
                    } else { // 여기서 else를 넣지 않고 아래를 무조건 실행했더니, 연속해서 나오는 중복값을 제거못했다.
                        r = r.next;
                    }
                }
                n = r = n.next; // 여기서 r을 n의 next값으로 초기화하는 부분을 빠트렸다.
            }

        }

    }

    public static void main(String[] args) {

        LinkedList l = new LinkedList();
        l.append(3);
        l.append(2);
        l.append(1);
        l.append(2);
        l.append(4);
        l.append(4);
        l.append(3);
        l.append(3);
        // l.append(6);
        // l.append(7);
        l.append(4);
        l.retrieve();

        l.deleteDuplicates();
        l.retrieve();

    }

}