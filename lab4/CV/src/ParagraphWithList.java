import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph{

    List<UnorderedList> list = new ArrayList<>();

    ParagraphWithList addItemToList(String content){
        UnorderedList tmp = new UnorderedList();
        list.add(tmp.addItem(content));
        return this;

    }

    void writeHTML(PrintStream out)
    {
        out.println("<p>");
        for(UnorderedList s:list)
                s.writeHTML(out);
        out.println("</p>");
    }
}
