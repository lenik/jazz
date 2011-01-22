package net.bodz.swt.presents.layers;

import java.util.EventObject;

import net.bodz.bas.lang.a.Const;

import org.eclipse.swt.graphics.Rectangle;

public class InvalidateEvent extends EventObject {

    private static final long serialVersionUID = 55636809082355563L;

    @Const
    public final Rectangle    region;

    public InvalidateEvent(Object source, Rectangle region) {
        super(source);
        this.region = region;
    }

}
