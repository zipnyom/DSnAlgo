
/******************
 * 
 * 
 * 별도 함수로 만들지 않음...
 * Not Found에 대한 예외처리 없었음
 * Intersection이라는 단어 활용해야함
 * 다른 리스트의 노드를 그 다음으로 추가하면, 자연스럽게 교차점이 생김 (헷갈려서 제 3의 리스트만들었었음)
 * 
 * 
 ********************/

public class linkedlist7 {

    public static class Node {
        int data;
        Node next = null;

        Node(int d) {
            this.data = d;

        }

        public int getLength() { // getListLength로 의미를 정확하게하자.

            int count = 0;
            Node n = this;
            while (n.next != null) {
                count++;
                n = n.next;
            }
            return count;

        }

        public void append(int d) {

            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            Node t = new Node(d);
            n.next = t; // end라고 변수명을 지어주면 더 직관적임
        }

        public void append(Node t) {

            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = t; // end라고 변수명을 지어주면 더 직관적임
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

    public static class Reference {
        public int count = 0;
    }

    public static void main(String[] args) {

        Node n3 = new Node(10);
        n3.append(11);
        n3.append(12);

        Node n1 = new Node(9);
        n1.append(1);
        n1.append(4);
        n1.append(4);
        n1.append(4);
        n1.append(5);
        n1.append(6);
        n1.append(7);
        n1.append(8);
        n1.append(n3);

        Node n2 = new Node(1);
        n2.append(3);
        n2.append(5);
        n2.append(n3);
        n1.retrieve();
        n2.retrieve();

        int l1 = n1.getLength();
        int l2 = n2.getLength();

        int diff;
        if (l1 > l2) {
            diff = l1 - l2;
            for (int i = 0; i < diff; i++) {
                n1 = n1.next;
            }
        } else {
            diff = l2 - l1;
            for (int i = 0; i < diff; i++) {
                n2 = n2.next;
            }
        }

        while (n1 != null) {
            if (n1 == n2) {
                System.out.println("Found node data : " + n1.data);
                break;
            } else {
                n1 = n1.next;
                n2 = n2.next;
            }
        }
    }
}