import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    public static int DELAY = 40;
    private QuickSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        // 初始化数据
        // TODO: 初始化数据
        data = new QuickSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {
        setData(-1, -1, -1, -1, -1);
        // TODO: 编写自己的动画逻辑
        quickSort(0, data.N() - 1);
        setData(-1, -1, -1, -1, -1);

    }

    private void quickSort(int l, int r) {
        if (l > r) {
            return;
        }
        if (l == r){
            setData(l,r,l,-1,-1);
            return;
        }
        setData(l,r,-1,-1,-1);
        int p = partition(l, r);
        quickSort(l, p - 1);
        quickSort(p + 1, r);
    }

    public void setData(int l, int r,int fixedPivot, int curPivot, int curElement ) {
        data.r = r;
        data.l = l;
        if (fixedPivot != -1)
            data.fixedSort[fixedPivot] = true;

        data.curPivot = curPivot;
        data.curElement = curElement;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private int partition(int l, int r) {

        int v = data.get(l);    //设置哨兵
        setData(l,r,-1,l,-1);
        int j = l;   //arr[l+1 ... j] < v , arr[j+1 ... i) > v
        for (int i = j + 1; i <= r; i++) {
            setData(l,r,-1,l,i);
            if (data.get(i) < v) {
                j++;

                data.swap(i, j);
                setData(l,r,-1,l,i);
            }
        }
        data.swap(l, j);
        setData(l,r,j,-1,-1);
        return j;
    }

    public static void main(String[] args) {

        int sceneWidth = 500;
        int sceneHeight = 500;
        int N = 100;
        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
