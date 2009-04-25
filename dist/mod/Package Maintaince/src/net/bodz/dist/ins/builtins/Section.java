package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.Component;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins._Component;

import org.eclipse.swt.graphics.ImageData;

public class Section extends _Component {

    private static ImageData sectionImage = null;

    public Section(boolean visible, boolean readOnly, boolean selection, String name,
            ImageData image, String text, String doc, Component... children) {
        super(true, selection);
        if (image == null)
            image = sectionImage;
        if (text == null)
            throw new NullPointerException("text"); //$NON-NLS-1$
        if (doc == null)
            doc = text;
        setVisible(visible);
        setReadOnly(readOnly);
        setSelection(selection);
        setImage(image);
        setName(name);
        setText(text);
        setDoc(doc);
        for (Component child : children)
            add(child);
    }

    @Override
    public CJob install(ISession session) {
        return null;
    }

}
