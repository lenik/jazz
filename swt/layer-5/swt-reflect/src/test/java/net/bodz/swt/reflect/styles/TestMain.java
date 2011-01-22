package net.bodz.swt.reflect.styles;

import net.bodz.bas.ui.UIException;
import net.bodz.bas.ui.a.PreferredSize;
import net.bodz.swt.gui.BasicGUI;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.GUIVars;
import net.bodz.swt.gui.DialogUI;
import net.bodz.swt.gui.styles.grid.SWTGridStrategy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

@PreferredSize(width = 640, height = 400)
public class TestMain extends BasicGUI {

    Object obj;

    @Override
    protected void createInitialView(Composite comp) throws UIException, SWTException {
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
            new DialogUI().alert("Error", e); //$NON-NLS-1$
        }
    }

}
