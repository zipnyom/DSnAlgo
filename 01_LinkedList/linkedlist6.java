
/******************
 * 
 * 
 * insertBefore 함수 활용이 Key 왜나면 insertBefore이 패딩에서도 요긴하게 쓰이고 본 함수에서도 쓰이면서 코드를
 * 직관적이고 간략하게 해주기 때문.
 * 
 * 내 구현방식과 선생님 구현 방식의 차이점은, 나는 Storage를 바깥에서 만들어 파라미터로 계속해서 제공한 반면, 선생님은 null을
 * 만나는 끝에서 생성해서 리턴값으로 계속 전달하는 형식이었음.
 * 
 * 결론적으로 선생님 코드가 훨씬 깔끔함.
 * 
 * 추가 교훈 : 앞->뒤로 붙인다고 반드시 앞에서부터 해당 공간을 제공할 필요가 없다. insertBefore을 기억하자.
 * 
 * 
 ********************/

public class linkedlist6 {

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

    }

    public static void doCalc(Node n1, Node n2, Storage s) {

        if (n1 == null && n2 == null) {
            if (s.carry != 0) {
                s.ll.append(s.carry);
            }
            return;
        }
        int sum = n1.data + n2.data + s.carry;
        int digit = sum % 10;
        int carry = sum / 10;
        s.ll.append(digit);
        s.carry = carry;

        doCalc(n1.next, n2.next, s);

    }

    public static LinkedList calcDigit(LinkedList l1, LinkedList l2) {

        Node n1 = l1.head;
        Node n2 = l2.head;
        Storage s = new Storage();
        doCalc(n1, n2, s);
        return s.ll;

    }

    // 위 함수는 리스트의 길이 즉 자릿수가 다를 때 NullExecption을 발생시킨다.

    public static Node sumLists(Node n1, Node n2, int carry) {

        if (n1 == null && n2 == null && carry == 0) {
            return null;
        }

        int value = carry;
        if (n1 != null) {
            value += n1.data;
        }
        if (n2 != null) {
            value += n2.data;
        }

        Node n = new Node();
        n.data = value % 10;
        Node next = sumLists(n1 == null ? null : n1.next, n2 == null ? null : n2.next, value / 10); // 여기서 null 체크하는 이유가
                                                                                                    // 다음 자리수가 아니라 현재
                                                                                                    // 자리수가 null일수도
                                                                                                    // 있어서였구나.
        n.next = next;
        return n;

    }

    // 만약 자릿수가 역방향이 아니라 순방향으로 되어있다면?

    public static Node sumList2(Node n1, Node n2) {

        // 길이를 맞춘다.
        int len1 = n1.getLength();
        int len2 = n2.getLength();

        int diff = 0;
        if (len1 > len2) {
            diff = len1 - len2;
            n2 = padZero(n2, diff);
        } else if (len2 > len1) { // else로도 충분했을듯
            diff = len2 = len1;
            n1 = padZero(n1, diff);
        }

        System.out.print("n1 : ");
        n1.retrieve();
        System.out.print("n2 : ");
        n2.retrieve();

        Storage s = new Storage();
        doSum2(n1, n2, s); // addLists라는 이름이 더 좋아보임
        if (s.carry != 0) {
            Node tmp = s.n;
            Node nn = new Node();
            nn.data = 1;
            nn.next = tmp;
            s.n = nn;
        }
        return s.n;
    }

    public static void doSum2(Node n1, Node n2, Storage s) {
        if (n1 == null && n2 == null) {
            return;
        }
        doSum2(n1.next, n2.next, s);

        int value = s.carry;
        if (n1 != null) {
            value += n1.data;
        }
        if (n2 != null) {
            value += n2.data;
        }

        Node n = new Node();
        n.data = value % 10;

        Node tmp = s.n;
        s.n = n;
        n.next = tmp;
        s.carry = value / 10; // insertBefore라는 이름으로 함수를 만드는 것이 훨씬 가독성이 좋을것이다.

    }

    public static class Storage {
        LinkedList ll = new LinkedList();
        int carry = 0;
        Node n = null; // result라는 이름이 더좋아보임.
    }

    public static Node padZero(Node n, int x) {
        if (x == 0) {
            return n;
        } else {
            Node nn = new Node();
            nn.data = 0;
            nn.next = n;
            return padZero(nn, x - 1);
        }
    }

    public static class Node {
        int data;
        Node next = null;

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
            Node t = new Node();
            t.data = d;
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

        // LinkedList l1 = new LinkedList();
        // l1.append(9);
        // l1.append(1);
        // l1.append(4);

        // LinkedList l2 = new LinkedList();
        // l2.append(6);
        // l2.append(4);
        // l2.append(7);

        // LinkedList l3 = calcDigit(l1, l2);
        // l3.retrieve();

        /*--*/

        Node n1 = new Node();
        n1.data = 9;
        n1.append(1);
        n1.append(4);
        n1.append(4);
        n1.append(4);
        n1.append(4);
        n1.append(4);
        n1.append(4);
        n1.append(4);

        Node n2 = new Node();
        n2.data = 6;
        n2.append(4);
        n2.append(3);

        // Node n3 = sumLists(n1, n2, 0);
        // n3.retrieve();

        // Node nn = padZero(n1, 0);
        // nn.retrieve();

        Node n3 = sumList2(n1, n2);
        System.out.print("n3 : ");
        n3.retrieve();
        /*--*/

    }

}