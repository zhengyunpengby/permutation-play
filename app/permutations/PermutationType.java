package permutations;

public enum PermutationType {
    /**
     * 插入方式生成序列
     */
    INSERT("insert"),
    /**
     * 遍历递归方式生成序列
     */
    FOREACH("foreach");
    String name;
    PermutationType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
