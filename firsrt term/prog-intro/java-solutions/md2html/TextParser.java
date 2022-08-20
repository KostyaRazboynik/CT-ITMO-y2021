package md2html;

import java.util.HashMap;
import java.util.Map;

public class TextParser {

    private StringBuilder source;
    private static Map<String, Integer> markdownIndex = new HashMap<>();
    private static Map<Character, String> markdownSSHtml = new HashMap<>();
    private static String[] htmlTag = new String[]{"em", "strong", "em", "strong", "s", "code", "var"};
    private static String[] markdownTag = new String[]{"*", "**", "_", "__", "--", "`", "%"};
    private static int tagCount = markdownTag.length;
    private static char[] markdownSpecialSymbols = new char[]{'<', '>', '&'};
    private static String[] htmlSpecialSymbols = new String[]{"&lt;", "&gt;", "&amp;"};


    static {
        for (int i = 0; i < tagCount; i++) {
            markdownIndex.put(markdownTag[i], i);
        }
        for (int i = 0; i < htmlSpecialSymbols.length; i++) {
            markdownSSHtml.put(markdownSpecialSymbols[i], htmlSpecialSymbols[i]);
        }
    }

    TextParser(StringBuilder source) {
        this.source = source;
    }


    static Integer getTagPosition(String cur) {
        Integer res = markdownIndex.get(cur);
        if (res == null) {
            res = markdownIndex.get(Character.toString(cur.charAt(0)));
            if (res == null) {
                res = -1;
            }
        }
        return res;
    }


    protected void toHtml(StringBuilder result) {
        int[] checker = new int[tagCount];
        String cur;

        for (int i = 0; i < source.length(); i++) {
            int start, end;
            char c = source.charAt(i);

            if (markdownSSHtml.containsKey(c)) {
                result.append(markdownSSHtml.get(c));
                continue;
            } else if (c == '\\') {
                continue;
            }

            cur = source.substring(i, Math.min(i + 2, source.length()));
            start = getTagPosition(cur);

            if (start != -1) {
                int r = 0;
                if (i > 0) {
                    if (source.charAt(i - 1) == '\\') {
                        r++;
                    }
                }
                if (r == 0) {
                    if (markdownTag[start].length() == 2) {
                        i++;
                    }
                    checker[start]++;
                    if (checker[start] % 2 == 0) {
                        result.append("</").append(htmlTag[start]).append(">");
                        continue;
                    } else {
                        int flag = 0;
                        for (int j = i + 2; j < source.length(); j++) {
                            if (source.charAt(j) == '\\') {
                                continue;
                            }

                            cur = source.substring(j, Math.min(j + 2, source.length()));
                            end = getTagPosition(cur);
                            if (end == start && source.charAt(j - 1) != '\\') {
                                flag++;
                                result.append("<").append(htmlTag[start]).append(">");
                                break;
                            }
                        }
                        if (flag != 0) {
                            continue;
                        } else {
                            result.append(source.charAt(i));
                        }
                    }
                } else {
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }
        return;
    }
}