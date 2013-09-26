package net.bodz.swt.draw.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import net.bodz.swt.c.canvas.Canvas;

public class DesignerCanvas2f
        extends Composite
        implements PaintListener {

    Document2f doc = new Document2f();
    Image major;
    Canvas canvas;

    public DesignerCanvas2f(Composite parent, int style) {
        super(parent, style & ~SWT.DOUBLE_BUFFERED);

        this.setLayout(new FillLayout());

        ScrolledComposite scroll;
        scroll = new ScrolledComposite(this, SWT.H_SCROLL | SWT.V_SCROLL);
        scroll.setLayout(new FillLayout());

        style &= SWT.DOUBLE_BUFFERED;
        canvas = new Canvas(scroll, style);
        scroll.setContent(canvas);

        canvas.addPaintListener(this);

        canvas.setSize(300, 300);
    }

    @Override
    protected void checkSubclass() {
    }

    /** ⇱ Implementaton Of {@link PaintListener}. */
    /* _____________________________ */static section.iface __PAINT_LISTENER__;

    @Override
    public void paintControl(PaintEvent e) {
        if (major != null)
            e.gc.drawImage(major, 0, 0);
        e.gc.setBackground(new Color(null, 222, 222, 222));
        e.gc.fillOval(0, 0, 1000, 1000);
    }

}
