import java.awt.*;

public class Star implements XmasShape {
    int x[]={20,25,40,30,35,20,5,10,0,15};
    int y[]={0,15,15,25,40,30,40,25,15,15};
    double xTrans=20;
    double yTrans=20;
    double scale=1;
    Color fillColor=Color.white;
    Color lineColor=Color.white;

    @Override
    public void render(Graphics2D g2d) {

        g2d.setColor(fillColor);
        g2d.fillPolygon(x,y,x.length);
        g2d.setColor(lineColor);
        g2d.drawPolygon(x,y,x.length);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(xTrans,yTrans);
        g2d.scale(scale,scale);
    }
}