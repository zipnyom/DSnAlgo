
// 정렬되어있지 않은 링크드리스트의 중복 데이터를 제거하시오.
// 단, 버퍼는 사용금지
// 3 -> 2 -> 1 -> 2 -> 4

// 버퍼 사용시 (hash) : 공간 복잡도 O(N), 시간복잡도 O(N)
// 포인터 사용시 : 공간복잡도 O(1), 시간 복잡도 O(N^2)

public class linkedlist4 {

    public static class LinkedList {

        public Node head = new Node();

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


        public int getListLen(LinkedList l) {
            int count = 0;
            Node n = l.head;
            while (n != null) {
                count++;
            }
            return count;
        }

        public void kthFromBack1(int k) { // kthToLast라는 함수명이 더 직관적
            int count = 0;
            Node n = head;
            while (n != null) {
                count++;
                n = n.next;
            }
            n = head;
            int num = count - k;
            for (int i = 0; i < num; i++) {
                n = n.next;
            }
            System.out.println("kth from back : " + n.data);
        }

        public int kthFromBack2(Node n, int k) {

            if (n == null) {
                return 0;
            }

            int num = kthFromBack2(n.next, k) + 1;
            if (num == k) {
                System.out.println("2th method / kth from back : " + n.data);
            }
            return num;

        }

        //Linked List의 길이를 N으로 볼 때 O(N)의 공간을 사용
        //최악의 경우 끝까지 갔다가 돌아오는 것이므로 O(2N)의 시간복잡도
        public Node kthNodeToLast(Node n, int k, Reference r) {

            if (n == null) {
                return null;
            } else {
                // kthNodeToLast(n.next, k, r); // 찾아온 노드를 반환받아야 한다.
                Node found = kthNodeToLast(n.next, k, r);
                r.count = r.count + 1; // r.count ++; 자바에서는 이렇게 쓰자
                if (r.count == k) {
                    return n;
                }
                return found; // 이미 찾아서 반환받은 노드를 반환하는것! 중요
            }

        }

        // 공간을 전혀 사용하지 않고 구현하는 방법?! 
        // 시간복잡도 : O(N)

        public Node kthNodeToLast2(int k) {
            Node n = head;
            Node r = head;
            for(int i = 0; i < k ; i ++) {
                r = r.next;
                if(r==null) {
                    return null;
                }
            }
            while (r != null) {
                n=n.next;
                r=r.next;
            }
            return n;
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
        l.append(5);
        l.append(6);
        l.append(7);
        l.append(8);

        // l.kthFromBack2(l.head, 1);

        // Reference r = new Reference();
        // Node n = l.kthNodeToLast(l.head, 3, r);
        // ... n이 null일 경우 즉 못찾았을 경우 예외처리도 해줘야할 듯.
        // System.out.println("3th method / kth from back : " + n.data);


        Node n = l.kthNodeToLast2(8);
        System.out.println("4th method / kth from back : " + n.data);


    }

}