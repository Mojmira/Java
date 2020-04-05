import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {

    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        photo.url=photoUrl;
        return this;
    }

    Section addSection(String sectionTitle) {
        Section tmp = new Section();
        tmp.setTitle(sectionTitle);
        sections.add(tmp);
        return tmp;
    }

    Document addSection(Section s) {
        sections.add(s);
        return this;
    }

    void writeHTML(PrintStream out){
        out.println("<?xml version=\"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">");
        out.println("<head>");
        out.println("<title>"+title+"</title>");
        out.println("</head>");

        out.println("<body>");

        for(Section s:sections)
            s.writeHTML(out);
        out.println("</body>");
    }

    public static void main(String[] args) {

        Document cv = new Document();

        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(new ParagraphWithList().addItemToList("C++")
                .addItemToList("Java"));
        cv.writeHTML(System.out);
    }


}
