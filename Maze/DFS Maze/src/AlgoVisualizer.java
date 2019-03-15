import javafx.geometry.Pos;

import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

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
        LinkedList<Position> queue = new LinkedList<Position>();
        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
        queue.addLast(entrance);
        data.path[entrance.getX()][entrance.getY()] = true;

        boolean isSolved = false;
        while (queue.size() !=0) {
            Position curPos = queue.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()) {
                isSolved = true;
                findPath(curPos);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0];
                int newY = curPos.getY() + d[i][1];
                if (data.isInArea(newX, newY) &&
                        !data.visited[newX][newY] &&
                        data.getMaze(newX, newY) == MazeData.ROAD) {
                    queue.addLast(new Position(newX, newY,curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }
        if (!isSolved)
            throw new IllegalArgumentException("The maze is not solution");

        setData(-1, -1, false);
    }

    private void findPath(Position des) {
        Position cur = des;
        while (cur != null) {
            data.result[cur.getX()][cur.getY()] = true;
            cur = cur.getPrev();
        }

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
