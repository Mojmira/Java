import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox boundingBox = new BoundingBox(0,0,0,0);
    List<AdminUnit> children;

    public void printString()
    {
        System.out.println(name +", "+ adminLevel+", "+population+
                ", "+area+". "+density+ boundingBox.BoxToString());
    }

}