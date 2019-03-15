/*
* Selection Sort
*
* 时间复杂度   O(n^2)
*
* 交换最少的排序
*
* */

public class SelectionSortData {

    public int[] numbers;
    public int orderedIndex= -1;       //[0，，，，，orderIndex]为有序
    public int currentMinIndex =-1;     //当前最小值元素索引
    public int currentCompareIndex=-1;     //正在比较的元素索引

    public SelectionSortData(int N ,int randomBounds) {
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = (int)(Math.random()*randomBounds)+1;
        }
    }
    public int N(){
        return numbers.length;
    }

    public int get(int index) {
        if (index<0 || index>=numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data");
        return numbers[index];
    }
    public void swap(int i,int j){
        int temp;
        temp = numbers[i];
        numbers[i]= numbers[j];
        numbers[j] = temp;

    }
}
