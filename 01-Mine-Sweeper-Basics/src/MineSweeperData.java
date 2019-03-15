public class MineSweeperData {

    public static final String blockImageURL = "resources/block.png";
    public static final String flagImageURL = "resources/flag.png";
    public static final String mineImageURL = "resources/mine.png";
    public static String numberImageURL(int num){
        if(num < 0 || num >= 8)
            throw new IllegalArgumentException("No such a number image!");
        return "resources/" + num + ".png";
    }

    private int N, M;
    private boolean[][] mines;

    public MineSweeperData(int N, int M, int mineNumber){
        if (N <= 0 || M <= 0) {
            throw new IllegalArgumentException("MineSweeper size  is invalid");
        }
        if (mineNumber<0 || mineNumber >N*M){
            throw new IllegalArgumentException("Mine number is larger than the size");
        }
        this.N = N;
        this.M = M;
        mines = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                mines[i][j] =false;
            }
        }
        generateMines(mineNumber);

    }

    private void generateMines(int mineNumber) {
        for (int i = 0; i < mineNumber; i++) {
            //依次将所有的雷摆放在前两行
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;

            //随机处理
            int swapTime = mineNumber;   //需要多次试验swapTime的合适值
            for (int j = 0; j < swapTime; j++) {
                int x1 = i/M;
                int y1 = i%N;
                int x2 = (int) (Math.random() * N);
                int y2 = (int) (Math.random() * M);
                swap(x1, y1, x2, y2);
            }

//            while(true) {
//                int x = (int) (Math.random() * N);
//                int y = (int) (Math.random() * M);

//                if (!mines[x][y]) {
//                    mines[x][y] = true;
//                    break;
//                }
//            }
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean t= mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }

    public int N(){ return N; }
    public int M(){ return M; }

    public boolean isMine(int x, int y){
        if(!inArea(x, y))
            throw new IllegalArgumentException("Out of index in isMine function!");
        return mines[x][y];
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
