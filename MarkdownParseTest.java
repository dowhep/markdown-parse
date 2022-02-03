import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;

public class MarkdownParseTest {

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testfiles() throws IOException {
        ArrayList<String> testfile1 = new ArrayList<>();
        testfile1.add("https://something.com");
        testfile1.add("some-page.html");
        String testfilemd = MarkdownParse.converter("test-file.md");
        assertEquals(testfile1, MarkdownParse.getLinks(testfilemd));
    }

    @Test
    public void testCharacterAfter() throws IOException {
        ArrayList<String> CharacterAfter = new ArrayList<>();
        CharacterAfter.add("https://something.com");
        String CharacterAfterTest = MarkdownParse.converter("innocent-character.md");
        assertEquals(CharacterAfter, MarkdownParse.getLinks(CharacterAfterTest));
    }

    @Test
    public void testInBetween() throws IOException {
        ArrayList<String> InBetween = new ArrayList<>();
        InBetween.add("https://something.com");
        String InBetweenTest = MarkdownParse.converter("evil-quotation-marks.md");
        assertEquals(InBetween, MarkdownParse.getLinks(InBetweenTest));
    }

    @Test
    public void testImage() throws IOException {
        ArrayList<String> Image = new ArrayList<>();
        Image.add("https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png");
        String ImageTest = MarkdownParse.converter("image.md");
        assertEquals(Image, MarkdownParse.getLinks(ImageTest));
    }

    // @Test
    // public void testInfiniteLoop() throws IOException {
    //     ArrayList<String> Infin
    // }
}
