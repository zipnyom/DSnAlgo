
public class QuickSort {

    static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    static void quickSort(int[] array, int start, int end) {

        int part2 = partition(array, start, end);

        if (part2 - 1 > start) {
            quickSort(array, start, part2 - 1);
        }
        if (part2 < end) {
            quickSort(array, part2, end);
        }

    }

    static int partition(int[] array, int start, int end) {
        int mid = array[(start + end) / 2];
        while (start <= end) {
            while (array[start] < mid)
                start++;
            while (array[end] > mid)
                end--;
            if (start <= end) {
                swap(array, start, end);
                start++;
                end--;
            }
        }
        return start;
    }

    static void swap(int[] array, int start, int end) {
        int tmp = array[start];
        array[start] = array[end];
        array[end] = tmp;
    }

    public static void main(String[] args) {
        int array[] = { 2, 3, 5, 4, 1, 5, 5, 6, 7, 9, 8, 0 };
        quickSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }
}