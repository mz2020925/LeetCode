package bilibili_coding_practice.p4;

public class get_max {
    public static void main(String[] args) {
        int[] arr = new int[]{-1, -2, 99, 100, 34, 6, -100, 89, 14};
        int res = process(arr, 0, arr.length - 1);
        System.out.println(res);
    }

    private static int process(int[] arr, int l, int r) {
        // 递归终止条件
        if (l == r) {
            return arr[l];
        }
        int mid = l + ((r - l) >> 1);
        return Math.max(process(arr, l, mid), process(arr, mid + 1, r));
    }
}
