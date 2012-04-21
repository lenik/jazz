package net.bodz.geom.util.gd.states;

import net.bodz.geom.util.gd.GDState;
import net.bodz.geom.util.gd.GDStateGraph;

import org.eclipse.swt.events.MouseEvent;

public class ViewPan
        extends GDState {

    public ViewPan(GDStateGraph graph) {
        super(graph);
    }

    @Override
    public GDState onMouseMove(MouseEvent e, MouseEvent d) {
        // vt.translate(e.x - d.x, e.y - d.y);
        d.x = e.x;
        d.y = e.y;
        return this;
    }

    // TODO Scroll/MouseWheel
    private static final long serialVersionUID = -6417492993883699928L;

}
