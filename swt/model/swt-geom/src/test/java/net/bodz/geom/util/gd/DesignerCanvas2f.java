package net.bodz.geom.util.gd;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class DesignerCanvas2f
        extends Composite
        implements PaintListener {

    protected SWTStateGraphImpl sg = new SWTStateGraphImpl();

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

        canvas.addMouseListener(sg);
        canvas.addMouseMoveListener(sg);
        canvas.addKeyListener(sg);
        canvas.addPaintListener(this);

        canvas.setSize(300, 300);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    protected void checkSubclass() {
    }

    public SWTStateGraphImpl getGraph() {
        return sg;
    }

    // -o PaintListener

    public void paintControl(PaintEvent e) {
        if (major != null)
            e.gc.drawImage(major, 0, 0);
        e.gc.setBackground(new Color(null, 222, 222, 222));
        e.gc.fillOval(0, 0, 1000, 1000);
    }
}
