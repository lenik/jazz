package net.bodz.swt.viz.styles;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.gui.viz.IVisualization;
import net.bodz.swt.c.test.ControlTestApp;
import net.bodz.swt.c3.ia.DialogInteraction;
import net.bodz.swt.viz.GridVisualization;
import net.bodz.swt.viz.IRefEntry_SWT;

@PreferredSize(width = 640, height = 400)
public class TestMain
        extends ControlTestApp {

    Object obj;

    @Override
    protected void createInitialView(Composite comp)
            throws SWTException {
        IVisualization strategy = new GridVisualization();
        IRefEntry_SWT<Object> var = GUIVars.wrap(obj);
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
