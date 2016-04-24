package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableInput;

public class MutableInput
        extends _MutableInput<MutableInput> {

    public MutableInput(IHtmlTag parent) {
        super(parent);
    }

    public MutableLabel idLabel(String id) {
        id(id);
        return label().for_(id);
    }

    public MutableInput capture(Object val) {
        attr("capture", val);
        return this;
    }

    public MutableInput acceptCamera() {
        accept("image/*;capture=camera");
        capture("camera");
        return this;
    }

}
