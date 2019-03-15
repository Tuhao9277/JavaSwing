public class InsertionSortData {
    private int[] numbers;
    public int orderedIndex = -1;      //有序
    public int currentIndex = -1;      //当前
    public InsertionSortData(int N,int randomBound) {

        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        }
    }
    public int N(){
        return numbers.length;
    }
    public int get(int index){
        if(index<0 || index>=numbers.length ){
            throw new IllegalArgumentException("Invalid index to access insertSortData");
        }
        return numbers[index];
    }
    public void swap(int i,int j){
        if (i<0 || i>=numbers.length ||j<0 || j>=numbers.length){

        }
        int temp;
        temp = numbers[i];
        numbers[i]= numbers[j];
        numbers[j] = temp;
    }
}
