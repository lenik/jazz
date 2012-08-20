package net.bodz.swt.director.layers;

import java.util.EventObject;

import org.eclipse.swt.graphics.Rectangle;

import net.bodz.bas.meta.optim.Const;

public class InvalidateEvent
        extends EventObject {

    private static final long serialVersionUID = 55636809082355563L;

    @Const
    public final Rectangle region;

    public InvalidateEvent(Object source, Rectangle region) {
        super(source);
        this.region = region;
    }

}
