
/**
 * 
 * 
 * 
 * 하나의 배열로 3개의 스택을 구현하시오.
 * 
 * 
 */
import java.util.EmptyStackException;

public class Stack4 {
    static class FullStackException extends Exception {

        FullStackException() {
            super();
        }

        FullStackException(String message) {
            super(message);
        }
    }

    static class FixedMultiStack {

        int numOfStack = 3;
        int stackSize;
        int values[];
        int sizes[];

        FixedMultiStack(int size) {
            this.stackSize = size;
            this.sizes = new int[numOfStack];
            this.values = new int[numOfStack * size];
            // for (int i = 0; i < this.values.length; i++) {
            // System.out.println(this.values[i]);
            // } 다 0으로 초기화 된다.
        }

        public void push(int stackNum, int data) throws FullStackException {
            if (isFull(stackNum)) {
                throw new FullStackException();
            }
            int top = getTopIndex(stackNum);
            values[top + 1] = data; // +1 안해줬었음
            sizes[stackNum]++;
        }

        public int pop(int stackNum) throws EmptyStackException {
            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }
            int top = getTopIndex(stackNum);
            int data = values[top];
            values[top] = 0;
            sizes[stackNum]--;
            return data;
        }

        public int getTopIndex(int stackNum) {
            int offset = stackNum * stackSize;
            int size = sizes[stackNum];
            return offset + size - 1; // -1 할 생각안했음.
        }

        public int peek(int stackNum) throws EmptyStackException {
            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }
            int top = getTopIndex(stackNum); // pop을 바꾸고 peek은 바꾸지 않음.
            return values[top];
        }

        public boolean isEmpty(int stackNum) {
            return sizes[stackNum] == 0;
        }

        public boolean isFull(int stackNum) {
            return sizes[stackNum] == stackSize;
        }

    }

    // 명진 구현버전
    static class DynamicMultiStack {

        int numOfStack = 3;
        int stackSize;
        int capacities[];
        int values[];
        int offsets[];
        int sizes[];

        DynamicMultiStack(int size) {
            this.stackSize = size;
            this.sizes = new int[numOfStack];
            this.offsets = new int[numOfStack];
            this.capacities = new int[numOfStack];
            for (int i = 0; i < numOfStack; i++) {
                offsets[i] = getPosition(size * i - 1); // offset 넣을때도 getPosition 함수 이용
                capacities[i] = size;
            }
            this.values = new int[numOfStack * size];
        }

        public void push(int stackNum, int data) throws FullStackException {
            if (isFull(stackNum)) {
                if (isFullAll()) {
                    throw new FullStackException();
                } else {
                    // Shift 실행
                    shift((stackNum + 1) % numOfStack);
                    capacities[stackNum]++; // 내 스택은 꽉 찼는데 전체적으로 안차있을 때만 capacity를 늘림
                }
            }
            // 여기까지 온 것은 무조건 공간이 있다는 뜻이므로 그냥 데이터를 넣음.
            int top = getTopIndex(stackNum);
            values[getPosition(top + 1)] = data;
            sizes[stackNum]++;
        }

        public void shift(int stackNum) {

            System.out.println("Shift.  stackNum : " + stackNum);
            if (isFull(stackNum)) {
                // 내가 꽉찼으면 다음 스택을 호출
                System.out.println("Stack " + stackNum + " is full");
                shift((stackNum + 1) % numOfStack);
            } else {
                // 내가 꽉 안찼으니 내 공간을 하나 양보함.
                capacities[stackNum]--;
            }

            int top = getTopIndex(stackNum);
            int newTop = getPosition(top + 1);
            System.out.println("Tops is " + top + ", New top is " + newTop + ", Offset is " + offsets[stackNum]);
            while (top != offsets[stackNum]) {
                values[newTop] = values[top];
                newTop = getPosition(--newTop);
                top = getPosition(--top);
            }
            int offset = offsets[stackNum];
            offsets[stackNum] = getPosition(++offset);

            System.out.println("- Shift is Done. New offset is " + offsets[stackNum]);
        }

        public int getPosition(int index) {
            int totalSize = numOfStack * stackSize;
            return ((index % totalSize) + totalSize) % totalSize;
        }

        public int getTopIndex(int stackNum) {
            return getPosition(offsets[stackNum] + sizes[stackNum]);
        }

        public int pop(int stackNum) {

            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }
            int top = getTopIndex(stackNum);
            int data = values[top];
            values[top] = 0;
            sizes[stackNum]--;
            return data;
        }

        public int peek(int stackNum) {
            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }
            int top = getTopIndex(stackNum);
            return values[top];
        }

        public boolean isFull(int stackNum) {
            return sizes[stackNum] == capacities[stackNum];
        }

        public boolean isFullAll() {
            for (int i = 0; i < numOfStack; i++) {
                if (isFull(i) == false)
                    return false;
            }
            return true;
        }

        public boolean isEmpty(int stackNum) {
            return sizes[stackNum] == 0;
        }

        public void printArray() {
            for (int i = 0; i < numOfStack * stackSize; i++) {
                if (i < 10) {
                    System.out.print(i + "  ");
                } else {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
            for (int i = 0; i < numOfStack * stackSize; i++) {
                System.out.print(values[i] + "  ");

            }
            System.out.println();
            System.out.println("---------------------------");
        }
    }

    // 선생님 구현버전

    static class MultiStacks {

        private class StackInfo {

            public int start, dataSize, stackSize;

            public StackInfo(int start, int stackSize) {
                this.start = start;
                this.stackSize = stackSize;
                this.dataSize = 0;
            }

            public boolean isWithinStack(int index) {
                if (index < 0 || index >= values.length) {
                    return false;
                }
                int virtualIndex = index < start ? index + values.length : index;
                int end = start + stackSize;
                return start <= virtualIndex && virtualIndex < end;
            }

            public int getLastStackIndex() {
                return adjustIndex(start + stackSize - 1);
            }

            public int getLastDataIndex() {
                return adjustIndex(start + dataSize - 1);
            }

            public int getNewDataIndex() {
                return adjustIndex(getLastDataIndex() + 1);
            }

            public boolean isFull() {
                return dataSize == stackSize;
            }

            public boolean isEmpty() {
                return dataSize == 0;
            }
        }

        private StackInfo[] info;
        private int[] values;

        public void printArray() {
            for (int i = 0; i < values.length; i++) {
                if (i < 10) {
                    System.out.print(i + "  ");
                } else {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + "  ");

            }
            System.out.println();
            System.out.println("---------------------------");
            System.out.println("Stack sizes");
            for (StackInfo si : info) {
                System.out.println("start : " + si.start + " dataSize : " + si.dataSize + " stackSize : " + si.stackSize);
            }
        }

        public MultiStacks(int numOfStack, int defaultSize) {
            info = new StackInfo[numOfStack];
            for (int i = 0; i < numOfStack; i++) {
                info[i] = new StackInfo(defaultSize * i, defaultSize);
            }
            values = new int[numOfStack * defaultSize];
        }

        private void expand(int stackNum) {
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            info[stackNum].stackSize++;
        }

        private void shift(int stackNum) {
            StackInfo stack = info[stackNum];
            if (stack.dataSize >= stack.stackSize) {
                int nextStack = (stackNum + 1) % info.length;
                shift(nextStack);
                stack.stackSize++; // 의문 나는 부분 (아래에서 한 번 빼주니까... 다음으로 토스하면 자신은 한 번 더 하는것)
            }
            int index = stack.getLastDataIndex();
            while (stack.isWithinStack(index)) {
                values[index] = values[previousIndex(index)];
                index = previousIndex(index);
            }
            values[stack.start] = 0;
            stack.start = nextIndex(stack.start);
            stack.stackSize--;
        }

        public int numberOfElements() {
            int totalDataSize = 0;
            for (StackInfo sd : info) {
                totalDataSize += sd.dataSize;
            }
            return totalDataSize;
        }

        private boolean allStacksAreFull() {
            return numberOfElements() == values.length;
        }

        private int adjustIndex(int index) {
            int max = values.length;
            return ((index % max) + max) % max;
        }

        private int nextIndex(int index) {
            return adjustIndex(index + 1);
        }

        private int previousIndex(int index) {
            return adjustIndex(index - 1);
        }

        public void push(int stackNum, int value) throws FullStackException {
            if (allStacksAreFull()) {
                throw new FullStackException();
            }

            StackInfo stack = info[stackNum];
            if (stack.isFull()) {
                expand(stackNum);
            }

            values[stack.getNewDataIndex()] = value;
            stack.dataSize++;
        }

        public int pop(int stackNum) throws Exception {
            StackInfo stack = info[stackNum];
            if (stack.isEmpty()) {
                throw new EmptyStackException();
            }
            int last = stack.getLastDataIndex();
            int value = values[last];
            values[last] = 0;
            stack.dataSize--;
            return value;
        }

        public int peek(int stackNum) throws Exception {
            StackInfo stack = info[stackNum];
            if (stack.isEmpty()) {
                throw new EmptyStackException();
            }
            int last = stack.getLastDataIndex();
            return values[last];
        }
    }

    public static void main(final String[] args) {
        FixedMultiStack fms = new FixedMultiStack(5);

        // 고정크기 Stack 테스트
        // try {
        // fms.push(0, 1);
        // fms.push(0, 2);
        // fms.push(0, 3);
        // fms.push(0, 4);
        // fms.push(0, 5);

        // fms.push(1, 1);
        // fms.push(1, 2);
        // fms.push(1, 3);
        // fms.push(1, 4);
        // fms.push(1, 5);

        // } catch (FullStackException fe) {
        // System.out.println("Stack is full");
        // }

        // try {
        // System.out.println(fms.pop(0));
        // System.out.println(fms.peek(0));
        // System.out.println(fms.pop(0));
        // System.out.println(fms.pop(0));
        // System.out.println(fms.pop(0));
        // System.out.println(fms.pop(0));

        // System.out.println(fms.pop(1));
        // System.out.println(fms.peek(1));
        // System.out.println(fms.pop(1));
        // System.out.println(fms.pop(1));
        // System.out.println(fms.pop(1));
        // System.out.println(fms.pop(1));

        // } catch (EmptyStackException e) {
        // System.out.println("Stack is empty");
        // }

        /****************************** */
        // DynamicMultiStack dms = new DynamicMultiStack(5);

        // try {
        // dms.push(1, 1);
        // dms.push(1, 2);
        // dms.push(1, 3);
        // dms.push(1, 4);
        // dms.push(1, 5);

        // // dms.push(2, 1);
        // // dms.push(2, 2);
        // // dms.push(2, 3);
        // // dms.push(2, 4);
        // // dms.push(2, 5);
        // dms.printArray();
        // dms.push(1, 6);
        // dms.printArray();
        // dms.push(1, 7);
        // dms.printArray();
        // dms.push(1, 8);
        // dms.printArray();
        // dms.push(1, 9);
        // dms.printArray();
        // dms.push(1, 10);
        // dms.printArray();

        // dms.push(1, 11);
        // dms.printArray();
        // dms.push(1, 12);
        // dms.printArray();
        // dms.push(1, 13);
        // dms.printArray();
        // dms.push(1, 14);
        // dms.printArray();
        // dms.push(1, 15);
        // dms.printArray();
        // } catch (FullStackException fe) {
        // System.out.println("Stack is full");
        // }
        // // dms.printArray();

        // try {

        // System.out.println("=== check === ");
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));

        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));
        // System.out.println(dms.pop(1));

        // } catch (EmptyStackException e) {
        // System.out.println("Stack is empty");
        // }

        // dms.printArray();

        /****************************** */
        MultiStacks ms = new MultiStacks(3, 5);

        try {
            ms.push(0, 1);
            ms.printArray();
            ms.push(0, 2);
            ms.printArray();
            ms.push(0, 3);
            ms.printArray();
            ms.push(0, 4);
            ms.printArray();
            ms.push(0, 5);
            ms.printArray();
            ms.push(1, 1);
            ms.printArray();
            ms.push(1, 2);
            ms.printArray();
            ms.push(1, 3);
            ms.printArray();
            ms.push(1, 4);
            ms.printArray();
            ms.push(1, 5);
            ms.printArray();
            ms.push(0, 6);
            ms.printArray();
            ms.push(0, 7);
            ms.printArray();
            ms.push(0, 8);
            ms.printArray();
            ms.push(0, 9);
            ms.printArray();
            ms.push(0, 10);
            ms.printArray();

           

        } catch (FullStackException e) {
            System.out.println("It's full");
        }

    }
}