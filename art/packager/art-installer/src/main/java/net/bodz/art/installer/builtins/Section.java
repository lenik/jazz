package net.bodz.art.installer.builtins;


import net.bodz.art.installer.AbstractComponent;
import net.bodz.art.installer.IComponent;
import net.bodz.art.installer.ISession;

import org.eclipse.swt.graphics.ImageData;


public class Section extends AbstractComponent {

    private static ImageData sectionImage = null;

    public Section(boolean visible, boolean readOnly, boolean selection, String name,
            ImageData image, String text, String doc, IComponent... children) {
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
        for (IComponent child : children)
            add(child);
    }

    @Override
    public CJob install(ISession session) {
        return null;
    }

}
