package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.IComponent;

import org.eclipse.swt.graphics.ImageData;

public class RequiredSection extends Section {

    public RequiredSection(String name, ImageData image, String text,
            String doc, IComponent... children) {
        super(true, true, true, name, image, text, doc, children);
    }

    public RequiredSection(String name, String text, String doc,
            IComponent... children) {
        super(true, true, true, name, null, text, doc, children);
    }

    public RequiredSection(String name, String text, IComponent... children) {
        super(true, true, true, name, null, text, null, children);
    }

}
