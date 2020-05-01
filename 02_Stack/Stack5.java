
/**
 * 
 * 
 * 
 * 하나의 배열로 3개의 스택을 구현하시오.
 * 
 * 
 */
import java.util.EmptyStackException;
import java.util.Stack;

public class Stack5 {

    static class StackWithMin extends Stack<NodeWithMin> {

        public void push(int value) {
            int min = Math.min(min(), value);
            super.push(new NodeWithMin(value, min));
        }

        public int min() {
            if (super.isEmpty())
                return Integer.MAX_VALUE;
            else
                return peek().min;
        }

    }

    static class StackWithMin2 extends Stack<Integer> {

        Stack<Integer> minStack = new Stack<>();

        public void push(int value) {
            if (minStack.isEmpty()) {
                minStack.push(value);
            } else {
                if (value < minStack.peek()) {
                    minStack.push(value);
                }
            }
            super.push(value);
        }

        public int min() {
            if (super.isEmpty())
                return Integer.MAX_VALUE;
            else
                return minStack.peek();
        }

        public Integer pop() {
            int value = super.pop();
            if (minStack.peek() == value) {
                minStack.pop();
            }
            return value;
        }

    }

    static class NodeWithMin {
        int data;
        int min;
        public NodeWithMin next;

        NodeWithMin(int data, int min) {
            this.data = data;
            this.min = min;
        }
    }

    // static class Stack {

    // Node top;
    // Node min;

    // public void push(int d) {
    // Node node;
    // if (top == null) {
    // node = new Node(d, d);
    // top = node;
    // // top = min = node;
    // } else {
    // if (top.min > d) { //여기와 아래 3번째 줄을 .min이 아니라 .data로 했다. 간단한 기능 구현이라도 대충해선
    // 안된다.
    // node = new Node(d, d);
    // } else {
    // node = new Node(d, top.min); //
    // }
    // node.next = top;
    // top = node;

    // // 이 방법은 O(N)이 소요된다.
    // // Node np = top;
    // // min = top;
    // // while (np != null) {
    // // if (np.data < min.data) {
    // // min = np;
    // // }
    // // np = np.next;
    // // }
    // }
    // }

    // public int pop() {
    // int data = top.data;
    // top = top.next;
    // return data;
    // }

    // public int min() {
    // return top.min;
    // }

    // }

    public static void main(String[] args) {

        StackWithMin2 s = new StackWithMin2();
        s.push(5);
        s.push(6);
        s.push(9);
        s.push(10);
        s.push(7);
        s.push(2);
        s.push(1);
        System.out.println(s.min());
        System.out.println(s.pop());
        System.out.println(s.min());
        System.out.println(s.pop());
        System.out.println(s.min());
    }

}