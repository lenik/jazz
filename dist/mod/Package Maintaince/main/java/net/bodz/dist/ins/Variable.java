package net.bodz.dist.ins;

import org.eclipse.swt.graphics.Image;

public class Variable {

    public static final int BASE_DIR = 1;

    private Image           image;
    private String          text;
    private String          doc;
    private Object          defaultValue;

    public Variable(String text, Object defaultValue) {
        this(text, null, defaultValue);
    }

    public Variable(String text, String doc, Object defaultValue) {
        this(null, text, doc, defaultValue);
    }

    public Variable(Image image, String text, String doc, Object defaultValue) {
        this.image = image;
        this.text = text;
        this.doc = doc;
        this.defaultValue = defaultValue;
    }

    public int getType() {
        return 0;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return String.valueOf(defaultValue);
    }

}
