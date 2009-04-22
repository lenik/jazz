package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.Component;

import org.eclipse.swt.graphics.ImageData;

public class HiddenSection extends Section {

    public HiddenSection(String name, ImageData image, String text, String doc,
            Component... children) {
        super(false, false, true, name, image, text, doc, children);
    }

    public HiddenSection(String name, String text, String doc,
            Component... children) {
        super(false, false, true, name, null, text, doc, children);
    }

    public HiddenSection(String name, String text, Component... children) {
        super(false, false, true, name, null, text, null, children);
    }

}
