package md2html;

public class FirstStageParser {
    private StringBuilder source;

    FirstStageParser(StringBuilder source) {
        this.source = source;
    }

    private boolean isHeader(StringBuilder text) {
        int pos = 0;
        while (pos < text.length() && text.charAt(pos) == '#') {
            pos++;
        }
        return pos > 0 && pos < text.length() && text.charAt(pos) == ' ';
    }

    protected void toHtml(StringBuilder result) {
        if (isHeader(source)) {
            new HeaderParser(source).toHtml(result);
        } else {
            new ParagraphParser(source).toHtml(result);
        }
    }
}