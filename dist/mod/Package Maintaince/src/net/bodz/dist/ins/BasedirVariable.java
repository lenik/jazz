package net.bodz.dist.ins;

import org.eclipse.swt.graphics.Image;

public class BasedirVariable extends Variable {

    public BasedirVariable(Image image, String text, String doc, Object defaultValue) {
        super(image, text, doc, defaultValue);
    }

    public BasedirVariable(String text, Object defaultValue) {
        super(text, defaultValue);
    }

    public BasedirVariable(String text, String doc, Object defaultValue) {
        super(text, doc, defaultValue);
    }

    @Override
    public int getType() {
        return BASE_DIR;
    }

}
