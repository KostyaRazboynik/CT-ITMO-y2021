package markup;

import java.util.List;

abstract class AbstractElement {
    private List<MarkdownInterface> elements;

    AbstractElement(List<MarkdownInterface> elements) {
        this.elements = elements;
    }

    protected void toMarkdown(StringBuilder itog, String symbol) {
        itog.append(symbol);
        for (MarkdownInterface element : elements) {
            element.toMarkdown(itog);
        }
        itog.append(symbol);
    }
    protected void toBBCode(StringBuilder itog, String symbol) {
        if (!symbol.isEmpty()) {
            itog.append("[" + symbol + "]");
        }
        for (MarkdownInterface element : elements) {
            element.toBBCode(itog);
        }
        if (!symbol.isEmpty()) {
            itog.append("[/" + symbol + "]");
        }
    }

}