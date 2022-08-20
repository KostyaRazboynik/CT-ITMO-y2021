package md2html;

public class ParagraphParser {
    private StringBuilder source;

    protected ParagraphParser(StringBuilder source) {
        this.source = source;
    }

    public void toHtml(StringBuilder result) {
        result.append("<p>");
        new TextParser(source).toHtml(result);
        result.append("</p>");
    }
}