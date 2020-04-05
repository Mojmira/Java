import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminUnitList{
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,AdminUnit> idToAdminUnit = new HashMap<>();
    Map<AdminUnit,Long> addminUnitToParent = new HashMap<>();
    Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();

    int offset = 20;

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename,",",true);
        while(reader.next()){

            AdminUnit tmp = new AdminUnit();
            long id;
            long parentid = 0;


            tmp.name = reader.get("name");
            tmp.adminLevel = Integer.parseInt(reader.get("admin_level"));
            tmp.population = Double.parseDouble(reader.get("population"));
            tmp.area = Double.parseDouble(reader.get("area"));
            tmp.density = Double.parseDouble(reader.get("density"));
            id = Integer.parseInt(reader.get("id"));
            if( reader.isMissing("parent")){
                tmp.parent = null;
            }else {parentid = Integer.parseInt(reader.get("parent"));}
            if(reader.isMissing("x3") || reader.isMissing("x1") || reader.isMissing("y3") || reader.isMissing("y1")){
                tmp.boundingBox = new BoundingBox(0,0,0,0);
            }else{
                tmp.boundingBox = new BoundingBox(Double.parseDouble(reader.get("x3")),Double.parseDouble(reader.get("x1")),
                        Double.parseDouble(reader.get("y3")),Double.parseDouble(reader.get("y1")));
            }
            idToAdminUnit.put(id ,tmp);
            addminUnitToParent.put(tmp,parentid);


            units.add(tmp);
        }
    }


    AdminUnitList sortInplaceByName(){
        class MyComparator implements Comparator<AdminUnit>{
            @Override
            public int compare(AdminUnit adminUnit, AdminUnit t1) {
                return adminUnit.name.compareTo(t1.name);
            }
        }
        units.sort(new MyComparator());
        return this;
    }

    AdminUnitList sortInplaceByArea(){

        class MyComparator implements Comparator<AdminUnit>{
            @Override
            public int compare(AdminUnit adminUnit, AdminUnit t1) {
                return Double.compare(adminUnit.area,t1.area);
            }
        }
        units.sort(new MyComparator());
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }
    AdminUnitList sortInPlaceByPopulation(Comparator<AdminUnit> cmp)
    {
        units.sort((a,b) -> Double.compare(a.population,b.population));
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList tmp = new AdminUnitList();
        tmp = this;
        tmp.sortInplace(cmp);
        return tmp;

    }
    AdminUnitList filter(Predicate<AdminUnit> pred){
        AdminUnitList tmp = this;
        List<AdminUnit> lista = new ArrayList<AdminUnit>();
        for(int i=0; i<tmp.units.size();i++)
        {
            if(pred.test(tmp.units.get(i)))
                lista.add(tmp.units.get(i));
        }
        tmp.units=lista;
        return tmp;
    }


    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
       if(regex)
       {
           for(int i=0; i<offset;i++)
        {
            if(units.get(i).name.matches(pattern))
           {
               ret.units.add(this.units.get(i));
           }
        }
       }
       else
       {
           for(int i=0; i<offset;i++)
           {
               if(units.get(i).name.contains(pattern))
               {
                   ret.units.add(this.units.get(i));
               }
           }

       }

        return ret;
    }

    void list()
    {
        fixMissingValues();
        //zeby nie tracic czasu wypisujemy tylko 20 pierwszych wyników
        for(int i=0; i<offset && i<units.size();i++)
            units.get(i).printString();

    }

    private void fixMissingValues(){
        for(AdminUnit unit:units){
            if(unit.parent != null){
                if(unit.density == -1){
                    unit.density = unit.parent.density;
                }
                if(unit.population == -1){
                    unit.population = unit.area*unit.density;
                }
            }

        }
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList unitList = new AdminUnitList();
        for(AdminUnit adminUnit:units){
            if(unit.adminLevel == 8){
                if(adminUnit.adminLevel == unit.adminLevel){
                    if(unit.boundingBox.distanceTo(adminUnit.boundingBox)<=maxdistance){
                        unitList.units.add(adminUnit);
                    }
                }
            }else{
                if(adminUnit.adminLevel == unit.adminLevel){
                    if(unit.boundingBox.intersects(adminUnit.boundingBox)){
                        unitList.units.add(adminUnit);
                    }
                }
            }
        }
        return unitList;
    }




    //C://Users//Mojmira//Desktop//java//po2019-mielke-magdalena//lab7//wojewodztwa//src//admin-units.csv
    public static void main(String[] args) throws IOException {
        AdminUnitList aul = new AdminUnitList();
        aul.read("/home/eaiib/mielkema/ProgramowanieObiektowe/lab7/wojewodztwa/src/admin-units.csv");
        aul.sortInplaceByArea();
        aul.filter(a->a.name.startsWith("Ż")).sortInplaceByArea().list();

    }
}

