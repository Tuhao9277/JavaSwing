import java.lang.reflect.Array;
import java.util.*;

public class MergeSort {
    //算法不产生任何实例
    private MergeSort() {
    }

    //将arr[l ... mid]和arr[mid ... r]两部分进行归并
    private static void Merge(Comparable[] arr, int l, int mid, int r) {
        //复制arr数组从下标l到r，到aux，
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        //初始化，i指向左半部分的起始索引位置，l，j指向右半部分的起始索引位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {      //如果左半部分处理完毕
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) { //如果右半部分处理完毕
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) { //左半部分元素<右半部分元素
                arr[k] = aux[i - l];
                i++;
            } else {    //左半部分元素>=右半部分元素
                arr[k] = aux[j - l];
                j++;
            }

        }
    }
    //递归使用归并排序，对arr[l...r]的范围进行排序
    public static void sort(Comparable[] arr,int l,int r,int depth){
        System.out.print(repeatCharacters('-',depth*2));
        System.out.println("Deal with["+l+","+r+"]");
        if (l>=r){
            return;
        }
        int mid = (l+r)/2;
        sort(arr,l,mid,depth+1);
        sort(arr,mid+1,r,depth+1);
        //对于arr[mid]<arr[mid+1]的情况，不需要进行merge
        if (arr[mid].compareTo(arr[mid+1])>0)
            Merge(arr,l,mid, r);
    }

    private static String repeatCharacters(char character,int length){
        StringBuilder s =new StringBuilder(length);
        for (int i = 0; i < length; i++)
            s.append(character);

        return s.toString();
    }

    public static void sort(Comparable[] arr){
        int n =arr.length;
        sort(arr,0,n-1,0);
    }
    public static void main(String [] args){
        Integer[] arr = new Integer[8];
        for (int i = 0; i < 8; i++)
            arr[i] =new Integer(8-i);
        MergeSort.sort(arr);
    }
}
