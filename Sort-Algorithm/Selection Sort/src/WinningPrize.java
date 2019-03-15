public class WinningPrize {
    private double chance;
    private int playTime;
    private int N;

    public WinningPrize(double chance, int playTime, int N) {
        if (chance < 0.0 || chance > 1.0){
            throw new IllegalArgumentException("chance must be larger than 0");
        }
        if (N <= 0) {
            throw new IllegalArgumentException("N must be larger than 0");
        }
            this.chance = chance;
        this.playTime = playTime;
        this.N = N;
    }

    public void run() {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play()) {
                wins++;
            }
        }
        System.out.println("winning rate" + (double) wins / N);
    }

    public boolean play() {
        for (int i = 0; i < playTime; i++)
            if (Math.random() < chance)  //Math.random()是令系统随机选取大于等于 0.0 且小于 1.0 的伪随机 double 值
                return true;        //若在chance的范围内，则中奖

        return false;

    }

    public static void main(String[] args) {
        double chance = 0.2;
        int playTime = 12;
        int N = 1000000;
        WinningPrize exp = new WinningPrize(chance, playTime, N);
        exp.run();
    }
}
