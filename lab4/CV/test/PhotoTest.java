import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    @Test
    void writeHTML() {

        Photo testPhoto = new Photo("Test URL");
        String testURL = "Test URL";

        String htmlContent="<img src=\""+testURL+"\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        testPhoto.writeHTML(ps);

        assertEquals(htmlContent, os.toString());
    }
}