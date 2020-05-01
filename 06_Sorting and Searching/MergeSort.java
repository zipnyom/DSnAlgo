public class MergeSort {

    public static void mergeSort(int arr[]) {

        int[] tmp = new int[arr.length];
        mergeSort(arr, tmp, 0, arr.length - 1);

    }

    public static void mergeSort(int arr[], int tmp[], int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, tmp, start, mid);
            mergeSort(arr, tmp, mid + 1, end);
            merge(arr, tmp, start, end);
        }
    }

    public static void merge(int arr[], int tmp[], int start, int end) {

        // 일단은 현재 start부터 end까지 복사
        for (int i = start; i <= end; i++) {
            tmp[i] = arr[i];
        }

        int mid = (start + end) / 2;
        int p1 = start;
        int p2 = mid + 1;
        int index = start;

        while (p1 <= mid && p2 <= end) {
            int min;
            if (tmp[p1] > tmp[p2]) {
                min = p2;
                p2++;
            } else {
                min = p1;
                p1++;
            }
            arr[index] = tmp[min];
            index++;
        }
        // 앞에 것이 남아있을 경우
        for (int i = p1; i <= mid; i++) {
            arr[index] = tmp[i];
            index++;
        }

    }

    public static void printArray(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = { 2, 0, 3, 5, 4, 6, 1, 7 , 10,15,29};
        mergeSort(arr);
        printArray(arr);

    }
}