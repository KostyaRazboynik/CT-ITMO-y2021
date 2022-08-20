package markup;

public class Text implements MarkdownInterface {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder itog) {
        itog.append(text);
    }
    @Override
    public void toBBCode(StringBuilder itog) {
        itog.append(text);
    }

}