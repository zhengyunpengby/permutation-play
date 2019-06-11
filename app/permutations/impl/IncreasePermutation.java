package permutations.impl;

import permutations.PermutationStream;

/**
 * 使用递增方式生成序列
 * 1.输入数字n、或输如序列
 * 2.根据输入参数初始化或生成数字数组arr
 * 3.对数组倒序遍历，当arr[n]>arr[n-1]，将第n位之后（包含）数据
 *   进行快速正序排序
 * 4.将arr[n-1]与n之后数据依次比较，若arr[n-1]<arr[m],则互换
 */
public class IncreasePermutation implements PermutationStream {

    private int num;
    private int[] curr = null;

    public IncreasePermutation(int n) {
        num = n;
    }

    public IncreasePermutation(int[] start) {
        num = start.length;
        this.curr = start;
    }

    public IncreasePermutation(String first) {
        String[] ss = first.split(" ");
        num = ss.length;
        int n = ss.length;
        curr = new int[n];
        for (int i = 0; i < n; i++) {
            curr[i] = Integer.parseInt(ss[i] + "");
        }
    }


    @Override
    public String generate() {
        StringBuffer sb = new StringBuffer(num);
        int flag = 1;
        flag = next();
        if(flag==0){
            return "";
        }
        for (int i = 0; i < num; i++) {
            sb.append(curr[i]+" ");
        }
        return sb.toString();
    }

    /**
     * 获取初始化序列或参数下一个序列
     * @return
     */
    private int next() {
        if(curr==null){
            curr = new int[num];
            for (int i = 1; i <= num; i++) {
                curr[i - 1] = i;
            }
            return 1;
        }

        int flag = 0;
        for (int i = num - 1; i > 0; i--) {
            if (curr[i] > curr[i - 1]) {
                flag = 1;
                sort(i);
                for(int j=i;j<num;j++){
                    if(curr[i-1]<curr[j]){
                        swap(curr,i-1,j);
                        break;
                    }
                }
                return flag;
            }
        }
        return flag;
    }

    /**
     * 对数组i后的数据进行排序
     * @param i
     */
    private void sort(int i) {
        int len = num - i;
        int[] tmp = new int[len];
        System.arraycopy(curr, i, tmp, 0, len);
        tmp = quitSort(tmp, 0, len - 1);
        System.arraycopy(tmp, 0, curr, i, len);
    }

    /**
     * 快速排序
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public int[] quitSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quitSort(arr, left, partitionIndex - 1);
            quitSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    /**
     * 快速排序递归分段排序
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] arr, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
