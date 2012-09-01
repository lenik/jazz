package net.bodz.redist.installer.builtins;

import org.eclipse.swt.graphics.ImageData;

import net.bodz.redist.installer.IComponent;

public class OptionalSection
        extends Section {

    public OptionalSection(String name, ImageData image, String text, String doc, IComponent... children) {
        super(true, false, false, name, image, text, doc, children);
    }

    public OptionalSection(String name, String text, String doc, IComponent... children) {
        super(true, false, false, name, null, text, doc, children);
    }

    public OptionalSection(String name, String text, IComponent... children) {
        super(true, false, false, name, null, text, null, children);
    }

}
