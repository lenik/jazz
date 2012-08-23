package net.bodz.bas.gui.viz;

import net.bodz.bas.potato.ref.IRefEntry;

public interface IRenderer {

    Object render(Object parent, IRefEntry<?> entry)
            throws RenderException;

}
