package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.IComponent;

import org.eclipse.swt.graphics.ImageData;

public class DefaultSection
        extends Section {

    public DefaultSection(String name, ImageData image, String text, String doc, IComponent... children) {
        super(true, false, true, name, image, text, doc, children);
    }

    public DefaultSection(String name, String text, String doc, IComponent... children) {
        super(true, false, true, name, null, text, doc, children);
    }

    public DefaultSection(String name, String text, IComponent... children) {
        super(true, false, true, name, null, text, null, children);
    }

}
