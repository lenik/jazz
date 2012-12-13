package net.bodz.bas.gui.viz;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.rtx.INegotiation;

public interface IVisualization {

    Object render(Object parent, IRefEntry<?> entry)
            throws RenderException;

    Object render(Object parent, IRefEntry<?> entry, INegotiation negotiation)
            throws RenderException; // , NegotiationException;

}
