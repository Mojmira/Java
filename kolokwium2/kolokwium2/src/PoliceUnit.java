public class PoliceUnit {

    int id;
    int id_gl;
    String nazwa;
    String rodzaj;
    String kod;
    String miasto;
    String ulica;
    String kier;
    String tel;
    String fax;
    String internet;
    String mail;

    void Print()
    {
        System.out.println(id +", "+ id_gl+", "+nazwa+
                ", "+rodzaj+". "+kod+ miasto+", "+ ulica+", "+kier+
                ", "+tel+". "+fax+ internet+", "+mail);
    }

}
