package net.bodz.swt.draw.app.tools;

import net.bodz.swt.draw.app.DesignerState;
import net.bodz.swt.draw.app.DesignerStateGraph;

import org.eclipse.swt.events.MouseEvent;

public class ViewPan
        extends DesignerState {

    public ViewPan(DesignerStateGraph graph) {
        super(graph);
    }

    @Override
    public DesignerState onMouseMove(MouseEvent e, MouseEvent d) {
        // vt.translate(e.x - d.x, e.y - d.y);
        d.x = e.x;
        d.y = e.y;
        return this;
    }

    // TODO Scroll/MouseWheel
    private static final long serialVersionUID = -6417492993883699928L;

}
