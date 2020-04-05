import javax.swing.text.MutableAttributeSet;

public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean isEmpty=true;

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     * @param x - współrzędna x
     * @param y - współrzędna y
     */

    BoundingBox(double xmax,double xmin,double ymax,double ymin){
        this.xmax = xmax;
        this.xmin = xmin;
        this.ymax = ymax;
        this.ymin = ymin;
    }

    void addPoint(double x, double y){
        if(isEmpty) {
            xmin=x;
            ymin=y;
            xmax=x;
            ymax=y;
            isEmpty=false;
        }
        else {
            if (x < xmin) xmin = x;
            if (x > xmax) xmax = x;
            if (y < ymin) ymin = y;
            if (y > ymax) ymax = y;
        }

    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y){
        if(isEmpty)
            return false;
        return (x>=xmin&&x<=xmax) && (y>=ymin&&y<=ymax);
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb){
        return (contains(bb.xmin,bb.ymin)&&contains(bb.xmax,bb.ymax));
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb){
        if(isEmpty||bb.isEmpty) return false;
        if((bb.xmin>xmin&&bb.xmin<xmax)||(bb.xmax>xmin&&bb.xmax<xmin))
        {
            return (bb.ymin>ymin && bb.ymin<ymax) || (bb.ymax>ymin && bb.ymax<ymin);
        }
        return false;
    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){

        xmin=Math.min(xmin,bb.xmin);
        ymin=Math.min(ymin,bb.ymin);
        xmax=Math.max(xmax,bb.xmax);
        ymax=Math.max(ymax,bb.ymax);
        return this;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){
        return isEmpty;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX(){
        if(!isEmpty())
            return (xmin+xmax)/2;
        else
            throw new RuntimeException("Not implemented");
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY(){
        if(!isEmpty())
            return (ymin+ymax)/2;
        else
            throw new RuntimeException("Not implemented");
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx){
        if(!isEmpty()){
            double lat=Math.toRadians(bbx.getCenterX()-getCenterX());
            double lon=Math.toRadians((bbx.getCenterY()-getCenterY()));

            double lat1=Math.toRadians(getCenterX());
            double lat2=Math.toRadians(bbx.getCenterX());

            double a = Math.pow(Math.sin(lat / 2), 2) +
                    Math.pow(Math.sin(lon / 2), 2) *
                            Math.cos(lat1) *
                            Math.cos(lat2);
            double rad = 6371;
            double c = 2 * Math.asin(Math.sqrt(a));
            return rad * c;
        }
            else
                throw new RuntimeException("Not implemented");
    }

    public String  BoxToString(){
        return " x1: "+xmin+","+"x2: "+xmax+","+"y1: "+ymin+","+"y2: "+ymax;
    }


}