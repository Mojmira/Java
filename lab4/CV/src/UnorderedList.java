import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {

    List<ListItem> list = new ArrayList<>();
    UnorderedList addItem(String content){

        ListItem item = new ListItem();
        item.content=content;
        list.add(item);
        return this;

    }
    void writeHTML(PrintStream out)
    {
        out.println("<ul>");
        for(ListItem i:list)
            i.writeHTML(out);
        out.println("</ul>");
    }
}
