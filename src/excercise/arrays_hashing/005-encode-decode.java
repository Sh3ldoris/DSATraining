package excercise.arrays_hashing;

import java.util.ArrayList;
import java.util.List;

class SolutionEncodeDecode {

    private static final Character DELIMITER = '?';

    public String encode(List<String> strgs) {
        var sb = new StringBuilder();
        for (String s : strgs) {
            sb.append(s.length());
            sb.append(DELIMITER);
            sb.append(s);
        }

        return sb.toString();
    }

    public List<String> decode(String s) {
        var result = new ArrayList<String>();

        int i = 0;
        while (i < s.length()) {
            var sbLen = new StringBuilder();
            // Get the number of decoded string
            while (s.charAt(i) != DELIMITER) {
                sbLen.append(s.charAt(i));
                i++;
            }
            i++; // Skip the delimiter
            int len = Integer.parseInt(sbLen.toString());

            // Decode the string
            int j = i;
            var sbStr = new StringBuilder();
            while (j - i < len) {
                sbStr.append(s.charAt(j));
                j++;
            }
            i = j; // Move to the next segment
            // Save the decoded string
            result.add(sbStr.toString());
        }

        return result;
    }
}
