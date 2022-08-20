package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements MarkdownInterface {
    public Emphasis(List<MarkdownInterface> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder itog) {
        toMarkdown(itog, "*");
    }
    @Override
    public void toBBCode(StringBuilder itog) {
        toBBCode(itog, "i");
    }

}