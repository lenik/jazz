package net.bodz.swt.viz.styles;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.gui.ia.UIException;
import net.bodz.swt.gui.ia.DialogInteraction;
import net.bodz.swt.viz.core.GUIVars;
import net.bodz.swt.viz.core.SwtEntry;
import net.bodz.swt.viz.style.grid.GridVisualization;

@PreferredSize(width = 640, height = 400)
public class TestMain
        extends BasicGUI {

    Object obj;

    @Override
    protected void createInitialView(Composite comp)
            throws UIException, SWTException {
        GridVisualization strategy = new GridVisualization();
        SwtEntry<Object> var = GUIVars.wrap(obj);
        strategy.render(null, var, comp, SWT.NONE);
    }

    public static void test(Object obj) {
        TestMain main = new TestMain();
        main.obj = obj;
        try {
            main.execute();
        } catch (Throwable e) {
            new DialogInteraction().alert("Error", e);
        }
    }

}
