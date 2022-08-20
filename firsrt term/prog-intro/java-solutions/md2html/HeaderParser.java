package md2html;

public class HeaderParser {
    private StringBuilder source;

    HeaderParser(StringBuilder source) {
        this.source = source;
    }

    private int HLvl(StringBuilder text) {
        int pos = 0;
        while (pos < text.length() && text.charAt(pos) == '#') {
            pos++;
        }
        return pos;
    }

    protected void toHtml(StringBuilder result) {
        int lvl = HLvl(source);
        result.append("<h").append(lvl).append(">");
        new TextParser(new StringBuilder(source.substring(lvl + 1))).toHtml(result);
        result.append("</h").append(lvl).append(">");
    }
}