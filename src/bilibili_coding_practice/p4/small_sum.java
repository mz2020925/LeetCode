package bilibili_coding_practice.p4;

public class small_sum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};  // 小和：1+(1+2)+(1+2+3)+(1+2+3+4)
        int resSum = smallSum(arr);
        System.out.println(resSum);
    }

    private static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    // arr[L...R]既要排好序，也要求小和
    private static int process(int[] arr, int L, int R) {
        // 递归终止点
        if (L == R) {
            return 0;
        }

        // 递归体
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);  // 递归每一层中，左部分的小和 + 右部分的小和 + 这一层合并的时候求得小和
    }

    private static int merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;  // 指向help[]索引
        int p1 = L;  // 指向左部分索引
        int p2 = mid + 1;  // 指向右部分索引
        int resSum = 0;  // 存储小和

        while (p1 <= mid && p2 <= R) {
            /*
            小和问题是基于归并排序实现的，多就多在merge的时候加了求小和的代码。
             */
            resSum += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;  //算小和
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];  // 通过外排序的方式实现归并排序，以下的代码都是实现归并排序的代码
        }

        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return resSum;
    }
}
