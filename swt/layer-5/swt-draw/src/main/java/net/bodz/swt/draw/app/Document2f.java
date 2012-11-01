package net.bodz.swt.draw.app;

import org.eclipse.swt.graphics.Rectangle;

import net.bodz.bas.geom_f.base.CompositeShape2d;
import net.bodz.bas.geom_f.tr.ITransformedView2d;

public class Document2f {

    Rectangle pageSize;

    ITransformedView2d vt;

    CompositeShape2d shapes = new CompositeShape2d();

}
