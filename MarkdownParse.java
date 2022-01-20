
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while (currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if (nextOpenBracket == -1)
                break;

            int nextValidCharacter = nextOpenBracket;
            // while (markdown.substring(nextValidCharacter, markdown.indexOf("]", nextValidCharacter)).contains("\"")) {
            //     nextValidCharacter = markdown.indexOf("\"", nextValidCharacter) + 1;
            //     nextValidCharacter = markdown.indexOf("\"", nextValidCharacter) + 1;
            //     if (nextValidCharacter == -1)
            //         break;
            // }

            // if (nextValidCharacter == -1)
            //     break;

            // nextValidCharacter = markdown.indexOf("]", nextValidCharacter);
            // int openParen = nextValidCharacter + 1;
            int lastCloseBracket = nextOpenBracket;
            int potentialCloseBracket;
            int quoteCount = 0;

            do {
                potentialCloseBracket = markdown.indexOf("]", lastCloseBracket + 1);
                if (potentialCloseBracket == -1)
                    break;
                for (int i = lastCloseBracket + 1; i < potentialCloseBracket; i++) {
                    if (markdown.charAt(i) == '\"') {
                        quoteCount++;
                    }
                }
                lastCloseBracket = potentialCloseBracket;
            } while ((quoteCount & 1) == 1);

            if (potentialCloseBracket == -1)
                break;

            // genious code above

            int openParen = potentialCloseBracket + 1;

            if (openParen >= markdown.length())
                break;
            if (markdown.charAt(openParen) != '(') {
                currentIndex = openParen;
                continue;
            }

            int closeParen = markdown.indexOf(")", openParen);
            if (closeParen == -1)
                break;

            if (nextOpenBracket == 0 || markdown.charAt(nextOpenBracket - 1) != '!') {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}