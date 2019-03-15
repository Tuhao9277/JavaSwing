public class ThreeGatesExperiment {

    private int N;


    public ThreeGatesExperiment(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be larger than 0");
        }
        this.N = N;
    }

    public boolean play(boolean changeDoor) {
        //Door:0 1 2
        int prizeDoor = (int) (Math.random() * 3);   //奖品门
        int playerChoice = (int) (Math.random() * 3); //选择的门
        if (playerChoice == prizeDoor) {    //如果选择了奖品门
            return changeDoor ? false : true;       //这时如果改变，即失败
        } else {
            return changeDoor ? true : false;       //如果选择的不是奖品门，由于主持人已经打开了一扇非奖品门，换门则为中奖
        }


    }

    public void run(boolean changeDoor) {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play(changeDoor)) {
                wins++;
            }
        }
        System.out.println(changeDoor ? "change" : "not change");
        System.out.println("winning rate" + (double) wins / N);

    }


    public static void main(String[] args) {

        int N = 1000000;
        ThreeGatesExperiment threeGatesExperiment = new ThreeGatesExperiment(N);
        threeGatesExperiment.run(true);
        System.out.println();
        threeGatesExperiment.run(false);
    }
}
