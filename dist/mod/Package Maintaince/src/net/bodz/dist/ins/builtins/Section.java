package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.IComponent;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.InstallException;
import net.bodz.dist.ins._Component;

import org.eclipse.swt.graphics.ImageData;

public class Section extends _Component {

    private static ImageData sectionImage = null;

    public Section(boolean visible, boolean readOnly, boolean selection,
            String name, ImageData image, String text, String doc,
            IComponent... children) {
        super(true, selection);
        if (image == null)
            image = sectionImage;
        if (text == null)
            throw new NullPointerException("text");
        if (doc == null)
            doc = text;
        setVisible(visible);
        setReadOnly(readOnly);
        setSelection(selection);
        setImage(image);
        setName(name);
        setText(text);
        setDoc(doc);
        for (IComponent child : children)
            add(child);
    }

    @Override
    public boolean install(ISession session) throws InstallException {
        return false;
    }

}
