import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class BouncingBallsPanel extends JPanel {

    static class Ball{
        double x;
        double y;
        double diam=50; //średnica
        double speed=2;
        double vx;
        double vy;
        Color color;

    }

    List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread{

        boolean suspend=false;
        boolean stop=false;

        synchronized void wakeup(){
            suspend=false;
            notify();
        }

        public void safeSuspend(){
            suspend=true;
        }

        public void run() {
            for(;;){

            for (int i = 0; i < balls.size(); i++) {
                //przesuń kulki
                balls.get(i).x += balls.get(i).vx*-balls.get(i).speed;
                balls.get(i).y += balls.get(i).vy*-balls.get(i).speed;
                //wykonaj odbicia od ściany
                if (balls.get(i).x <= 0 || balls.get(i).x >= getWidth()-balls.get(i).diam)
                    balls.get(i).vx *= -1;
                if (balls.get(i).y <= 0 || balls.get(i).y >= getHeight()-balls.get(i).diam)
                    balls.get(i).vy *= -1;
            }
            //wykrywanie kolizji z innami kulkami
                DetectCollision();
            //wywołaj repaint
            repaint();
            //uśpij
            try {
                this.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(this) {
                try {
                    if (suspend) {
                        System.out.println("suspending");
                        wait();
                    }
                } catch (InterruptedException e) {
                }
            }
            }
        }
    }

    void DetectCollision()
    {
        for(int i=0; i<balls.size()-1;i++)
        {
            for(int j=i+1;j<balls.size();j++)
            {
                if(Distance(balls.get(i),balls.get(j))<=balls.get(i).diam)
                {
                    System.out.println("collision detected");
                    System.out.println("distance: "+(Distance(balls.get(i),balls.get(j))));
                    balls.get(i).vx-=NewVelXorY(balls.get(i),balls.get(j),true);
                    balls.get(j).vx-=NewVelXorY(balls.get(j),balls.get(i),true);
                    balls.get(i).vy-=NewVelXorY(balls.get(i),balls.get(j),false);
                    balls.get(j).vy-=NewVelXorY(balls.get(j),balls.get(i),false);
                }
            }
        }
    }

    double Distance(Ball b1, Ball b2)
    {
        double x1=b1.x-b1.diam/2;
        double y1=b1.y-b1.diam/2;
        double x2=b2.x-b2.diam/2;
        double y2=b2.y-b2.diam/2;

        return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }

    double NewVelXorY(Ball b1, Ball b2, boolean isX)
    {
        double vx1=b1.vx;
        double x1=b1.x-b1.diam/2;
        double vy1=b1.vy;
        double y1=b1.y-b1.diam/2;

        double vx2=b2.vx;
        double x2=b2.x-b2.diam/2;
        double vy2=b2.vy;
        double y2=b2.y-b2.diam/2;
        double w;
        if(isX)     w=x1-x2;
        else        w=y1-y2;


        double l=(vx1-vx2)*(x1-x2)+(vy1-vy2)*(y1-y2);
        double m=Math.abs(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));

        return w*l/m;
    }


    public void paint(Graphics g) {
        super.paint(g);


        for (Ball s : balls) {
            g.setColor(s.color);
            g.fillOval((int)s.x, (int)s.y, (int)s.diam, (int)s.diam);

        }

    }
    AnimationThread t;
    BouncingBallsPanel(){
        super();
        t=new AnimationThread();
        t.start();
        t.safeSuspend();
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

    }

    void onStart(){
        System.out.println("Start or resume animation thread");
        t.wakeup();
    }

    void onStop() throws InterruptedException {
        System.out.println("Suspend animation thread");
        t.safeSuspend();


    }

    void onPlus(){
        System.out.println("Add a ball"+balls.size());

        Ball b=new Ball();
        b.x=(int)(Math.random()*(getWidth()-b.diam));
        b.y=(int)(Math.random()*(getHeight()-b.diam));
        b.vx=Math.random()*4-2;
        b.vy=Math.random()*4-2;
        b.speed=Math.random()*7;
        b.color=new Color((int)(Math.random() * 0x1000000));
        balls.add(b);

    }

    void onMinus(){
        System.out.println("Remove a ball");
        balls.remove(0);
    }
}