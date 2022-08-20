package markup;

import java.util.List;

public class Paragraph extends AbstractElement {
    public Paragraph(List<MarkdownInterface> elements) {
        super(elements);
    }

    public void toMarkdown(StringBuilder itog) {
        toMarkdown(itog, "");
    }
    public void toBBCode(StringBuilder itog) {
        toBBCode(itog, "");
    }

}