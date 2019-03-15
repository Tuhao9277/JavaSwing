import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private static int DELAY = 40;
    private MonteCarloPiData data;
    private AlgoFrame frame;    // 视图
    private int N;

    public AlgoVisualizer(int sceneWidth, int sceneHeight,int N){
        if (sceneHeight != sceneWidth ){
            throw new IllegalArgumentException("This demo must be run in a square");
        }
        // 初始化数据
        // TODO: 初始化数据
        this.N = N;
        Circle circle = new Circle(sceneWidth/2,sceneHeight/2,sceneWidth/2);
        data = new MonteCarloPiData(circle);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Get Pi with Monte Carlo", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){

        // TODO: 编写自己的动画逻辑


        for (int i = 0; i < N; i++) {

            if (i %100 == 0) {
                frame.render(data);
                AlgoVisHelper.pause(DELAY);
                System.out.println(data.estimatePi());
            }

            int x = (int) (Math.random()*frame.getCanvasWidth());
            int y = (int) (Math.random()*frame.getCanvasHeight());
            data.addPoint(new Point(x,y));
        }
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    public static void main(String[] args) {

        int sceneWidth = 500;
        int sceneHeight = 500;
        int N =20000;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight,N);
    }
}
