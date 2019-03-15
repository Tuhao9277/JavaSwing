import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlgoVisualizer {
    private static int DELAY = 2;
    private static int blockSide = 6;
    // TODO: 创建自己的数据
    private MazeData data;        // 数据
    private AlgoFrame frame;    // 视图

    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(int N, int M) {

        // 初始化数据
        // TODO: 初始化数据
        data = new MazeData(N, M);
        int sceneWidth = data.N() * blockSide;
        int sceneHeight = data.M() * blockSide;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze Solver Visualization", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        // TODO: 编写自己的动画逻辑
        setRoadData(-1, -1);
        RandomQueue<Position> queue = new RandomQueue<Position>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;
        data.openMist(first.getX(),first.getY());//打开起点位置的迷雾
        while (queue.size() !=0) {
            Position curPos = queue.remove();
            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;
                    if (data.isInArea(newX,newY) && !data.visited[newX][newY]){
                        queue.add(new Position(newX, newY));
                        data.openMist(newX,newY);
                        data.visited[newX][newY] = true;
                        setRoadData(curPos.getX()+d[i][0],curPos.getY()+d[i][1]); //破墙造路
                }
            }
        }
        setRoadData(-1, -1);
    }
    private void setRoadData(int x, int y) {
        //判断当前位置是否在迷宫内
        if (data.isInArea(x, y))
            data.maze[x][y] = MazeData.ROAD;  //破墙

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                for (int i = 0; i < data.N(); i++) {
                    for (int j = 0; j < data.M(); j++) {
                        data.visited[i][j] = false;
                    }
                }
                new Thread(()->{
                    go(data.getEntranceX(),data.getEntranceY());
                }).start();
            }
        }
    }

    private boolean go(int x, int y) {
        if (!data.isInArea(x, y))
            throw new IllegalArgumentException("x,y is out of index in go function");
        data.visited[x][y] = true;
        setPathData(x, y,true);
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
        setPathData(x, y,false);
        return false;

    }

    private void setPathData(int x, int y, boolean isPath) {
        if (data.isInArea(x, y)) {
            data.path[x][y] = isPath;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String mazeFile = "/Users/guo/Downloads/Java图形化编程/Test/11-Templates/src/maze_101_101.txt";
        // TODO: 根据需要设置其他参数，初始化visualizer
        int N = 101;
        int M = 101;
        AlgoVisualizer visualizer = new AlgoVisualizer(N, M);
    }
}
