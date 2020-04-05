import java.awt.*;

public class Rect implements XmasShape {
    int x[]={0,20,20,0};
    int y[]={0,0,20,20};
    double xTrans=20;
    double yTrans=20;
    double xScale=1;
    double yScale=1;
    Color fillColor=Color.white;

    @Override
    public void render(Graphics2D g2d) {

        g2d.setColor(fillColor);
        g2d.fillPolygon(x,y,x.length);

        GradientPaint grad = new GradientPaint(0,0,new Color(66,29,29, 237),0,15, new Color(30,15,15));
        g2d.setPaint(grad);
        g2d.fillPolygon(x,y,x.length);


    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(xTrans,yTrans);
        g2d.scale(xScale,yScale);
    }
}