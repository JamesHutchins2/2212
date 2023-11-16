
public class Action {
    private Word word;
    private boolean handled;
    private int buttonType;

    public Action(Word word, int buttonType) {
        this.word = word;
        this.buttonType = buttonType;
        this.handled = false;
    }

    public Word getWord() {
        return word;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public int getButtonType() {
        return buttonType;
    }

    public void handle() {
        Handle_Click.handle(this);
    }
}
