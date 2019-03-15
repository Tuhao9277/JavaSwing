public class QuickSortData {
    public int array[];
    public int l,r;
    public int curPivot;
    public int curElement;
    public boolean [] fixedSort;    //标定点标记

    public QuickSortData(int N,int randomBound) {
        array = new int[N];
        fixedSort  = new boolean[N];
        for (int i = 0; i < N; i++) {
            fixedSort[i] = false;
        }
        for (int i = 0; i < N; i++) {
            array[i]=(int)(Math.random()*randomBound)+1;
        }
    }
    public int N(){
        return array.length;
    }
    public int get(int index){
        return array[index];
    }

    public void swap(int i, int j) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
