package net.bodz.swt.reflect.styles;

import net.bodz.bas.ui.UIException;
import net.bodz.bas.ui.a.PreferredSize;
import net.bodz.swt.reflect.GUIVar;
import net.bodz.swt.reflect.GUIVars;
import net.bodz.swt.reflect.styles.grid.SWTGridStrategy;
import net.bodz.swt.reflect.util.DialogUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

@PreferredSize(width = 640, height = 400)
public class TestMain
        extends BasicGUI {

    Object obj;

    @Override
    protected void createInitialView(Composite comp)
            throws UIException, SWTException {
        SWTGridStrategy strategy = new SWTGridStrategy();
        GUIVar<Object> var = GUIVars.wrap(obj);
        strategy.render(null, var, comp, SWT.NONE);
    }

    public static void test(Object obj) {
        TestMain main = new TestMain();
        main.obj = obj;
        try {
            main.run();
        } catch (Throwable e) {
            new DialogUI().alert("Error", e);
        }
    }

}
