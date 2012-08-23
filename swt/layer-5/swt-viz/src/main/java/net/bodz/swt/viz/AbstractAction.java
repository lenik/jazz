package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Image;

public abstract class AbstractAction
        implements IAction {

    private Image image;
    private String text;
    private String doc;

    public AbstractAction(Image image, String text, String doc) {
        this.image = image;
        this.text = text;
        this.doc = doc;
    }

    public AbstractAction(String text) {
        this(null, text, null);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getDoc() {
        return doc;
    }

    @Override
    public Image getImage() {
        return image;
    }

}
