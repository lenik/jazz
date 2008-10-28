package net.bodz.swt.gui.styles;

import net.bodz.bas.gui.GUIException;
import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.swt.gui.BasicGUI;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.GUIVars;
import net.bodz.swt.gui.SWTInteraction;
import net.bodz.swt.gui.styles.grid.SWTGridStyle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

@PreferredSize(width = 640, height = 400)
public class TestMain extends BasicGUI {

    Object obj;

    @Override
    protected Composite createInitialView(Composite parent)
            throws GUIException, SWTException {
        SWTGridStyle style = new SWTGridStyle();
        GUIVar<Object> var = GUIVars.wrap(obj);
        Control varRend = style.render(var, parent, SWT.NONE);
        return (Composite) varRend;
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
