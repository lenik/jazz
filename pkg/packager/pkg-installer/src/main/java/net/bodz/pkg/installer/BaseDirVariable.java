package net.bodz.pkg.installer;

import java.io.File;

import org.eclipse.swt.graphics.Image;

public class BaseDirVariable
        extends Variable {

    public BaseDirVariable(Image image, String text, String doc, Object defaultValue) {
        super(image, text, doc, defaultValue);
    }

    public BaseDirVariable(String text, Object defaultValue) {
        super(text, defaultValue);
    }

    public BaseDirVariable(String text, String doc, Object defaultValue) {
        super(text, doc, defaultValue);
    }

    @Override
    public int getType() {
        return BASE_DIR;
    }

    @Override
    public File getDefaultValue() {
        return (File) super.getDefaultValue();
    }

}
