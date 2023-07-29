package bilibili_coding.class2_sort;

public class class2_reverse_order_pair_number {
    public static void main(String[] args) {
        int [] arr = new int[]{1,2,3,4};
        // int[] arr = new int[]{3, 2, 1};
        int resNumber = pairNumber(arr);
        System.out.println(resNumber);
    }

    private static int pairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    // 递归函数
    private static int process(int[] arr, int L, int R) {
        // 递归终结点
        if (L == R) {
            return 0;
        }

        // 递归体
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = help.length - 1;
        int p1 = mid;
        int p2 = R;
        int resNumber = 0;

        while (p1 >= L && p2 >= mid + 1) {
            /*
            逆序对个数问题是基于归并排序实现的，多就多在merge的时候修改的代码。
             */
            resNumber += arr[p1] > arr[p2] ? (p2 - mid) : 0;  // 算小和
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];  // 通过外排序的方式实现归并排序，以下的代码都是实现归并排序的代码
        }

        while (p1 >= L) {
            help[i--] = arr[p1--];
        }

        while (p2 >= mid + 1) {
            help[i--] = arr[p2--];
        }

        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return resNumber;
    }
}
