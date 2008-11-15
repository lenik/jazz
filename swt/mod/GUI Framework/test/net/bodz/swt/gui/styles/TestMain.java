package net.bodz.swt.gui.styles;

import net.bodz.bas.gui.GUIException;
import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.swt.gui.BasicGUI;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.GUIVars;
import net.bodz.swt.gui.SWTInteraction;
import net.bodz.swt.gui.styles.grid.SWTGridStrategy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

@PreferredSize(width = 640, height = 400)
public class TestMain extends BasicGUI {

    Object obj;

    @Override
    protected void createInitialView(Composite comp) throws GUIException,
            SWTException {
        SWTGridStrategy strategy = new SWTGridStrategy();
        GUIVar<Object> var = GUIVars.wrap(obj);
        strategy.render(var, comp, SWT.NONE);
    }

    public static void test(Object obj) {
        TestMain main = new TestMain();
        main.obj = obj;
        try {
            main.run();
        } catch (Throwable e) {
            new SWTInteraction().alert("Error", e);
        }
    }

}
