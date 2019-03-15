import java.awt.*;
import javax.swing.*;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // TODO: 设置自己的数据
    private SelectionSortData data;
    public void render(SelectionSortData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            // TODO： 绘制自己的数据data

            int w = canvasWidth /data.N();
            for (int i = 0; i < data.N(); i++) {
                if(i<data.orderedIndex) //若在已排序的部分
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.Red); //为红色
                else
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.Grey);  //无序部分，为灰色

                if (i == data.currentCompareIndex)      //若当前值为比较元素
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.LightBlue);
                if (i == data.currentMinIndex)          //当前值为最小值元素
                    AlgoVisHelper.setColor(g2d,AlgoVisHelper.Indigo);

                AlgoVisHelper.fillRectangle(g2d,i*w,canvasHeight-data.get(i),w-1,data.get(i));
            }

        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}

