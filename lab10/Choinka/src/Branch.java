import java.awt.*;

public class Branch implements XmasShape {
    int x[]={286,286,223,200,148,119,69,45,0};
    int y[]={0,131,89,108,79,95,66,80,56};
    double xTrans=0;
    double yTrans=0;
    double xScale=1;
    double yScale=1;
    double scale=1;
    Color lineColor;
    Color fillColor;

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,new Color(0,255,0),0,100, new Color(0,10,0));
        g2d.setPaint(grad);
        g2d.translate(0,50);
        g2d.fillPolygon(x,y,x.length);
        g2d.translate(570,0);
        g2d.scale(-1,1);
        g2d.fillPolygon(x,y,x.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(xTrans,yTrans);
        g2d.scale(xScale,yScale);

    }
}