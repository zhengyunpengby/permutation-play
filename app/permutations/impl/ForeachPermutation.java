package permutations.impl;

import permutations.Permutation;

import java.util.*;

/**
 * @program: permutations
 * @description: 遍历递归方式生成序列
 * 1.1 根据n分别生成长度为1的数组[1],[2]..[k]..[n]存入list
 * 1.2 遍历list，分别算出数组[k]中在1-n不存在的数[T]（长度为n-1）
 * 1.3 创建长度为2的数组，复制[k]的第一位到新数组，将[T]遍历插入复制后新数组第2位
 * 2   重复上述步骤插入第3,4..k..n位
 * @author:
 * @create: 2019-06-03 11:42
 **/
public class ForeachPermutation implements Permutation {
    private int num;
    private List<int[]> list = new ArrayList<>();

    public ForeachPermutation(int num) {
        this.num = num;
    }

    public Set<String> generate() {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < num; i++) {
            foreachNumber(i);
        }
        StringBuffer sb = new StringBuffer(num);
        for (int[] integers : list) {
            for (int integer : integers) {
                sb.append(integer);
            }
            set.add(sb.toString());
            sb.setLength(0);
        }
        return set;
    }

    /**
     * 插入数组的第index位数据
     *
     * @param index
     */
    private void foreachNumber(int index) {
        List<int[]> tmp = list;
        list = new ArrayList<>();
        int size = tmp.size();
        int[] arrNum = null;
        int i = 0;
        do {
            arrNum = new int[index+1];
            if (tmp.size() != 0) {
                System.arraycopy(tmp.get(i), 0, arrNum, 0, index);
            }
            foreachPermutation(index, arrNum);
            i++;
        }
        while (i < size);
    }

    /**
     * 将arrNumber中暂未使用到的值遍历插入到第index位
     *
     * @param index
     * @param arrNumber
     */
    private void foreachPermutation(int index, int[] arrNumber) {
        int[] tmp = null;
        List<Integer> noUse = unUseNumber(arrNumber);
        int size = arrNumber.length;
        for (Integer integer : noUse) {
            tmp = new int[size];
            System.arraycopy(arrNumber, 0, tmp, 0, index);
            tmp[index] = integer;
            list.add(tmp);
        }
    }

    /**
     * 根据num查找数组arrNumber中没有使用到的值
     *
     * @param arrNumber
     * @return
     */
    private List<Integer> unUseNumber(int[] arrNumber) {
        List<Integer> noUer = new ArrayList<>();
        int size = arrNumber.length;

        for (int i = 1; i <= num; i++) {
            int flag = 0;
            for (int j = 0; j < size; j++) {
                if (arrNumber[j] == i) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                noUer.add(i);
            }
        }
        return noUer;
    }
}
