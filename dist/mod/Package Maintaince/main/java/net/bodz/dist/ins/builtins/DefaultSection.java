package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.Component;

import org.eclipse.swt.graphics.ImageData;

public class DefaultSection extends Section {

    public DefaultSection(String name, ImageData image, String text, String doc,
            Component... children) {
        super(true, false, true, name, image, text, doc, children);
    }

    public DefaultSection(String name, String text, String doc, Component... children) {
        super(true, false, true, name, null, text, doc, children);
    }

    public DefaultSection(String name, String text, Component... children) {
        super(true, false, true, name, null, text, null, children);
    }

}
