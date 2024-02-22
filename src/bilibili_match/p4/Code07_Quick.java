package bilibili_match.p4;

import org.junit.Test;

import java.util.Arrays;

public class Code07_Quick {

    // K神的归并排序
    public void quick(int[] arr, int l, int r, int[] temp) {
        if(l >= r) {
            return;
        }

        int m = l + ((r -  l)>>1);
        quick(arr, l, m, temp);
        quick(arr, m + 1, r, temp);

        // 代码第一次执行到这里的时候，左半部分只有一个数，右半部分也只有一个数

        for(int k = l;k <= r;k++) {
            temp[k] = arr[k];  // arr[l ~ r]此时左半部分（l ~ m）和右半部分（m+1 ~ r）都是升序的
        }

        int i = l;
        int j = m + 1;

        for(int k = l;k <= r;k++) {
            if(i == m + 1) {
                arr[k] = temp[j++];
            }else if(j == r + 1 || temp[i] <= temp[j]) {
                arr[k] = temp[i++];
            }else {
                arr[k] = temp[j++];
            }
        }
    }

    public void quick(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }
        int[] temp = new int[arr.length];
        quick(arr, 0, arr.length - 1, temp);
    }

    // 官方排序
    public void arraysSort(int[] arr) {
        Arrays.sort(arr);
    }

    // 生成随机数组
    public int[] generateArrays(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];  // Math.random()是产生[0, 1)范围内的随机数，所以(maxSize + 1) * Math.random()范围是[0, maxSize + 1)
        for(int i = 0;i < arr.length; i++) {
            arr[i] = (int) ((maxValue+1)*Math.random() - (int)(maxValue*Math.random()));
        }
        return arr;
    }

    // 一个数组赋值两份，分别用归并排序和官方排序，才能做测试
    public int[] copyArray(int[] arr) {
        if(arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length;i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // 判断两数组是否相等，就是用归并排序的结果对比官方的结果
    public boolean isEqual(int[] arr1, int[] arr2) {
        if((arr1 == null && arr2 != null ) ||(arr1 != null && arr2 == null)) {
            return false;
        }
        if(arr1 == null && arr2 == null) {
            return true;
        }
        if(arr1.length != arr2.length) {
            return false;
        }
        for(int i = 0; i < arr1.length;i++) {
            if(arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    // 打印数组
    public void printArray(int[] arr){
        if(arr == null){
            return;
        }
        for(int i = 0; i < arr.length;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    // 最终测试
    @Test
    public void test() {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean res= true;

        for(int i = 0;i < count; i++){
            int[] arr1 = generateArrays(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            quick(arr1);
            arraysSort(arr2);
            if(!isEqual(arr1, arr2)){
                res = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(res ? "right!" : "wrong...");

    }
}
