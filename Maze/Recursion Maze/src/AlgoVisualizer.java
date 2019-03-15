import java.awt.*;

public class AlgoVisualizer {
    private static int DELAY = 2;
    private static int blockSide = 6;
    // TODO: 创建自己的数据
    private MazeData data;        // 数据
    private AlgoFrame frame;    // 视图

    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(String mazeFile) {

        // 初始化数据
        // TODO: 初始化数据
        data = new MazeData(mazeFile);
        int sceneWidth = data.N() * blockSide;
        int sceneHeight = data.M() * blockSide;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze Solver Visualization", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        // TODO: 编写自己的动画逻辑
        setData(-1, -1, false);
        if (!go(data.getEntranceX(), data.getEntranceY()))
            System.out.println("The Maze has no solution!");

        setData(-1, -1, false);
    }

    private boolean go(int x, int y) {
        if (!data.isInArea(x, y))
            throw new IllegalArgumentException("x,y is out of index in go function");

        data.visited[x][y] = true;
        setData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY())
            return true;

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.isInArea(newX, newY)
                    && data.getMaze(newX, newY) == MazeData.ROAD
                    && !data.visited[newX][newY])
                if (go(newX, newY))
                    return true;
        }
        //四个方向遍历后均无道路，此时置这条路为false
//        data.path[x][y]=false;
        setData(x, y, false);
//        go(x,y-1);
//        go(x+1,y);
//        go(x,y+1);
//        go(x-1,y);
        return false;

    }

    private void setData(int x, int y, boolean isPath) {
        //判断当前位置是否在迷宫内
        if (data.isInArea(x, y))
            data.path[x][y] = isPath;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String mazeFile = "/Users/guo/Downloads/Java图形化编程/Test/11-Templates/src/maze_101_101.txt";
        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(mazeFile);
    }
}
