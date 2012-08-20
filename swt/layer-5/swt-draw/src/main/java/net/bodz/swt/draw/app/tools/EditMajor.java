package net.bodz.swt.draw.app.tools;

import org.eclipse.swt.events.MouseEvent;

import net.bodz.swt.draw.app.DesignerState;
import net.bodz.swt.draw.app.DesignerStateGraph;

public class EditMajor
        extends DesignerState {

    public EditMajor(DesignerStateGraph graph) {
        super(graph);
    }

    @Override
    public DesignerState onMouseDown(MouseEvent e) {
        return super.onMouseDown(e);
    }

    @Override
    public DesignerState onMouseMove(MouseEvent e, MouseEvent d) {
        return super.onMouseMove(e, d);
    }

    @Override
    public DesignerState onMouseUp(MouseEvent e, MouseEvent d) {
        return super.onMouseUp(e, d);
    }

    @Override
    public DesignerState onMouseDoubleClick(MouseEvent e) {
        return super.onMouseDoubleClick(e);
    }

    private static final long serialVersionUID = -5929768636672028188L;

}
