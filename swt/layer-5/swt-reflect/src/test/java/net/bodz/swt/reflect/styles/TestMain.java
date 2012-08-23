package net.bodz.swt.reflect.styles;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.ui.UIException;
import net.bodz.bas.ui.a.PreferredSize;
import net.bodz.swt.reflect.SwtEntry;
import net.bodz.swt.reflect.GUIVars;
import net.bodz.swt.reflect.styles.grid.SWTGridStrategy;
import net.bodz.swt.reflect.util.DialogUI;

@PreferredSize(width = 640, height = 400)
public class TestMain
        extends BasicGUI {

    Object obj;

    @Override
    protected void createInitialView(Composite comp)
            throws UIException, SWTException {
        SWTGridStrategy strategy = new SWTGridStrategy();
        SwtEntry<Object> var = GUIVars.wrap(obj);
        strategy.render(null, var, comp, SWT.NONE);
    }

    public static void test(Object obj) {
        TestMain main = new TestMain();
        main.obj = obj;
        try {
            main.execute();
        } catch (Throwable e) {
            new DialogUI().alert("Error", e);
        }
    }

}
