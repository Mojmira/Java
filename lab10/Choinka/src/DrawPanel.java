import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    List<XmasShape> shapes = new ArrayList<XmasShape>();

    DrawPanel(){
        setBackground(new Color(151, 22, 5, 243));
        moreStars();
        addRect();
        addBranches();
        addBubbles();
        addStars();



//        setOpaque(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        System.out.println(shapes.size());
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }

    void addBubbles()
    {
        for(int i=1; i<6; i++)
        {
            for(int j=0;j<i+3;j++) {
                Bubble b = new Bubble();
                b.scale = i * 0.07;
                b.x = (int)(Math.random() * ((100+50*i) + 1))+150+30*(5-i);
                //b.x = i % 2 * 50 * i + 200;
                b.y += i * 65 + 100 - j%2 *15;

                b.fillColor = new Color((int)(Math.random() * 0x1000000));
                b.lineColor = Color.black;
                shapes.add(b);
            }
        }
    }

    void addBranches()
    {
        for(int i=4; i>0;i--)
        {
            Branch b=new Branch();
            b.yScale+=(i-1)*0.25;
            b.xScale-=(4-i)*0.2;
            b.xTrans+=((200-50*i))+50;
            b.yTrans+=i*60;

            shapes.add(b);

        }

    }

    void addStars()
    {

        for(int i=1; i<6; i++)
        {
            for(int j=0;j<10;j++) {
                Star b = new Star();
                b.scale = (Math.random() * 8) * 0.1;
                b.xTrans = (int)(Math.random() * ((250+50*i - 150) + 1))+150+7*(7-i);
                //b.x = i % 2 * 50 * i + 200;
                b.yTrans += i * 60 + 100 - j%2 *10;
                //b.yTrans = (int)(Math.random() * ((200+50*i - 150) + 1))+120+7*i;
                b.fillColor =new Color(244, 195, 58, 255);
                b.lineColor=Color.black;
                shapes.add(b);
            }
        }

    }

    void addRect()
    {
        Rect r = new Rect();
        r.yScale+=4;
        r.xScale+=5;
        r.yTrans+=460;
        r.xTrans+=250;
        shapes.add(r);
    }

    void moreStars()
    {
        for(int i=0;i<100;i++)
        {
            Star b = new Star();
            b.scale = Math.random();
            b.xTrans = (int)(Math.random() * 800);
            b.yTrans= (int)(Math.random() * 600);
            b.fillColor = new Color(151, 22, 5, 0);

            shapes.add(b);
        }
    }



}