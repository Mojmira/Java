import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest {

    @org.junit.jupiter.api.Test
    void setContent() {
        Paragraph testParagraph = new Paragraph();
        String testContent = "Test content";
        testParagraph.setContent(testContent);
        assertEquals(testContent, testParagraph.content);

    }

    @org.junit.jupiter.api.Test
    void writeHTML() {
        Paragraph testParagraph = new Paragraph();
        String testContent = "Test content";
        testParagraph.setContent(testContent);

        String newLine = System.getProperty("line.separator");

        String htmlContent="<p>"+testContent+"</p>"+newLine;

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        testParagraph.writeHTML(ps);

        assertEquals(htmlContent, os.toString());
    }
}