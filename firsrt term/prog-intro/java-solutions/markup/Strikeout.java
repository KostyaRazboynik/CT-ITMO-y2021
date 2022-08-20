package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements MarkdownInterface {
    public Strikeout(List<MarkdownInterface> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder itog) {
        toMarkdown(itog, "~");
    }
    @Override
    public void toBBCode(StringBuilder itog) {
        toBBCode(itog, "s");
    }
}