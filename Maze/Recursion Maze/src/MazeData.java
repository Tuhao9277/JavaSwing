import java.io.*;
import java.util.Scanner;

public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';
    private int N, M; //定义行列
    private char[][] maze;
    private int entranceX,entranceY;
    private int exitX,exitY;
    public boolean[][] visited;
    public boolean[][] path;

    public MazeData(String filename) {
        if (filename == null)
            throw new IllegalArgumentException("Filename can not be null! ");
        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists())
                throw new IllegalArgumentException("File" + filename + "is doesn't exists!");
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            //读取第一行
            String nmLine = scanner.nextLine();         //   \\s表示 空格,回车,换行等空白符,
            String[] nm = nmLine.trim().split("\\s+");    // trim 去除字符串两边的空格与制表符 split 是用于按指定字符(串)或正则去分割某个字符串
            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);

            maze = new char[N][M];
            visited = new boolean[N][M];
            path = new boolean[N][M];
            //读取后续的N行
            for (int i = 0; i < N; i++) {
                String line = scanner.nextLine();

                //保证每一行都有M个字符
                if (line.length() != M)
                    throw new IllegalArgumentException("Maze File" + filename + "is error!");

                for (int j = 0; j < M; j++) {
                    maze[i][j] = line.charAt(j); //得到每行j处的字符
                    visited[i][j] = false;
                    path[i][j] = false;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }
        entranceX = 1;
        entranceY =0;
        exitX = N-2;
        exitY = N-1;

    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public  void print (){
        System.out.println(N+" "+M);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public char getMaze(int i, int j) {
        if (!isInArea(i, j))
            throw new IllegalArgumentException("i or j is out of index in getMaze");
        return maze[i][j];
    }

    public boolean isInArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
