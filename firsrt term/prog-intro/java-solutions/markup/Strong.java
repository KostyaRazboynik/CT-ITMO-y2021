package markup;

import java.util.List;

public class Strong extends AbstractElement implements MarkdownInterface {
    public Strong(List<MarkdownInterface> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder itog) {
        toMarkdown(itog, "__");
    }
    @Override
    public void toBBCode(StringBuilder itog) {
        toBBCode(itog, "b");
    }

}