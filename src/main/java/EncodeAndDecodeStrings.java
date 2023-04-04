import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {
    private static final char DELIMITER = '$';

    public static void main(String[] args) {
        System.out.println("Result encode: " + encode(List.of("Hello","World")));
        System.out.println("Result decode: " + decode(encode(List.of("Hello","World"))));
    }

    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder resultString = new StringBuilder();
        for (String str : strs) {
            resultString.append(str.length());
            resultString.append(DELIMITER);
            resultString.append(str);
        }

        return resultString.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String str) {
        StringBuilder oneWord = new StringBuilder();
        List<String> result = new ArrayList<>();
        int stringSize = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == DELIMITER) {

            }
        }

        return result;
    }
}
