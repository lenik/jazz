package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.Component;

import org.eclipse.swt.graphics.ImageData;

public class RequiredSection extends Section {

    public RequiredSection(String name, ImageData image, String text,
            String doc, Component... children) {
        super(true, true, true, name, image, text, doc, children);
    }

    public RequiredSection(String name, String text, String doc,
            Component... children) {
        super(true, true, true, name, null, text, doc, children);
    }

    public RequiredSection(String name, String text, Component... children) {
        super(true, true, true, name, null, text, null, children);
    }

}
