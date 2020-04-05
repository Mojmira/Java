import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ListItemTest {

    @Test
    void writeHTML() {
        ListItem testItem = new ListItem();
        String testContent = "Test content";
        testItem.content = testContent;

        String newLine = System.getProperty("line.separator");

        String htmlContent="<li>"+testContent+"</li>"+newLine;

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        testItem.writeHTML(ps);

        assertEquals(htmlContent, os.toString());
    }
}