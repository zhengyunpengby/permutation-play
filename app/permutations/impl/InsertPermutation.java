package permutations.impl;

import permutations.Permutation;

import java.util.*;

/**
 * @program: permutations
 * @description: 插入方式生成序列
 * 1.由1开始创建序列链表，形成1的所有序列
 * 2.将2分别插入到1的所有序列链表的各个位置，形成2的所有序列
 * 3.使用递进方式直至n，形成n的所有序列
 * 4.使用set过滤重复的序列
 *
 * 待优化点：
 * 1.序列保存集合去重，减少重复操作
 * 2.个数字序列层级间只用阻塞队列缓存，用多线程方式处理数据插入链表
 * @author:
 * @create: 2019-06-03 11:41
 **/
public class InsertPermutation implements Permutation {
    private int num;
    /**
     * 存放当前处理数字所有的序列
     */
    private List<LinkedList<Integer>> sonPermutation = new ArrayList<>();

    public InsertPermutation(int num) {
        this.num = num;
    }

    /**
     * 生成n的序列
     * @return
     */
    public Set<String> generate() {
        Set<String> set = new HashSet<>();
        for(int i=1;i<=num;i++){
            insertNumber(i);
        }
        StringBuffer sb = new StringBuffer(num);
        for (LinkedList<Integer> integers : sonPermutation) {
            for (Integer integer : integers) {
                sb.append(integer);
            }
            set.add(sb.toString());
            sb.setLength(0);
        }
        return set;
    }

    /**
     * 获取number的序列
     * @param number
     */
    private void insertNumber(int number){
        List<LinkedList<Integer>> tmp = sonPermutation;
        sonPermutation = new ArrayList<>();
        int size = tmp.size();
        LinkedList<Integer> linkedList = null;
        int i=0;
        do{
            if(tmp.size()==0){
                linkedList = new LinkedList<>();
            }else{
                linkedList = tmp.get(i);
            }
            insertPermutation(number,linkedList);
            i++;
        }
        while(i<size);
    }

    /**
     *
     * @return 该数据插入基数链表每一位形成的String列表
     * 便利链表长度i
     * 复制基连链表
     * 将number插入i位置
     */
    private void insertPermutation(int number,LinkedList<Integer> linkedList){
        LinkedList<Integer> tmp = null;
        for(int i=0;i<number;i++){
            tmp = (LinkedList<Integer>)linkedList.clone();
            tmp.add(i,number);
            sonPermutation.add(tmp);
        }
    }
}
