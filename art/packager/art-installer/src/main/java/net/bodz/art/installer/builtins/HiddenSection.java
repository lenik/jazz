package net.bodz.art.installer.builtins;


import net.bodz.art.installer.IComponent;

import org.eclipse.swt.graphics.ImageData;


public class HiddenSection extends Section {

    public HiddenSection(String name, ImageData image, String text, String doc,
            IComponent... children) {
        super(false, false, true, name, image, text, doc, children);
    }

    public HiddenSection(String name, String text, String doc, IComponent... children) {
        super(false, false, true, name, null, text, doc, children);
    }

    public HiddenSection(String name, String text, IComponent... children) {
        super(false, false, true, name, null, text, null, children);
    }

}
