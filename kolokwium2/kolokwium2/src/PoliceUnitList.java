import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PoliceUnitList {

        List<PoliceUnit> units =new ArrayList<PoliceUnit>();



    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename,";",true);
        while(reader.next()){

            PoliceUnit tmp = new PoliceUnit();
            long id;
            long parentid = 0;


            tmp.id = Integer.parseInt(reader.get("id"));
            tmp.id_gl = Integer.parseInt(reader.get("id_gl"));
            tmp.nazwa = reader.get("nazwa");
            tmp.rodzaj = reader.get("rodzaj");
            tmp.kod = reader.get("kod");
            tmp.miasto = reader.get("miasto");
            tmp.ulica = reader.get("ulica");
            tmp.kier = reader.get("kier");
            tmp.tel = reader.get("tel");


            if( reader.isMissing("fax")){
                tmp.fax=null;}
            else tmp.fax = reader.get("fax");

            if( reader.isMissing("internet")){
                tmp.internet=null;}
            else tmp.internet = reader.get("internet");

            tmp.mail = reader.get("mail");

            units.add(tmp);
        }
    }

        PoliceUnitList sortInplaceByMiasto(){
            class MyComparator implements Comparator<PoliceUnit>{
                @Override
                public int compare(PoliceUnit adminUnit, PoliceUnit t1) {
                    return adminUnit.miasto.compareTo(t1.miasto);
                }
            }
            units.sort(new MyComparator());
            return this;
        }

        List<PoliceUnit> hasWWWByMiasto()
        {
            List<PoliceUnit> tpm=new ArrayList<PoliceUnit>();
            for(int i=0; i<units.size();i++)
            {
                if(units.get(i).internet!=null)
                    tpm.add(units.get(i));
            }

            class MyComparator implements Comparator<PoliceUnit>{
                @Override
                public int compare(PoliceUnit adminUnit, PoliceUnit t1) {
                    return adminUnit.internet.compareTo(t1.internet);
                }
            }
            tpm.sort(new MyComparator());
            return tpm;
        }
        void HasNoFax()
        {
            for(int i=0; i<units.size();i++)
            {
                if(units.get(i).fax==null)
                    units.get(i).Print();
            }
        }


    public static void main(String[] args) throws IOException {
        PoliceUnitList list=new PoliceUnitList();
        list.read("/home/eaiib/mielkema/ProgramowanieObiektowe/kolokwium2/kolokwium2/src/policja.csv");
    }
}
