
/******************
 * 
 * 
 * 토끼가 끝에 도달했는지 여부를 따지는 조건식에서, .next.next == null을 썼다가 NullException뜸.
 * == null || .next == null 이 옳은 조건식
 * 
 * 루프 찾는 원리는 나중에 응용될듯.
 * 
 * 
 ********************/

public class linkedlist8 {

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

    public static Node findLoopEntry(Node n) {

        Node f = n;
        Node s = n;

        do {
            // if (f.next.next == null) { // 여기서 NullExecption
            if (f == null || f.next == null) {
                return null;
            }
            f = f.next.next;
            s = s.next;
        } while (f != s);

        System.out.println("We first met here : " + f.data);

        s = n;
        do {
            f = f.next;
            s = s.next;
        } while (f != s);

        return f;
    }

    public static void main(String[] args) {

        Node n3 = new Node(10);
        n3.append(11);
        n3.append(12);

        Node n1 = new Node(9);
        n1.append(1);
        n1.append(2);
        n1.append(3);
        n1.append(4);
        Node nLoop = new Node(5);
        n1.append(nLoop);
        n1.append(6);
        n1.append(7);
        n1.append(8);
        n1.append(9);
        n1.append(10);
        n1.append(nLoop);

        Node result = findLoopEntry(n1);
        if (result == null) {
            System.out.println("This is not loop");
        } else {
            System.out.println("Loop entry data : " + result.data);
        }
    }
}