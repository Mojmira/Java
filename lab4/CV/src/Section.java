import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {

    String title;
    List<Paragraph> paragraps = new ArrayList<>();


    Section setTitle(String title){
        this.title=title;
        return this;
    }
    Section addParagraph(String paragraphText){
        Paragraph tmp = new Paragraph();
        tmp.content=paragraphText;
        paragraps.add(tmp);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraps.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        out.println("<h1>"+title+"</h1>");
        for(Paragraph p:paragraps)
            p.writeHTML(out);

    }

}
