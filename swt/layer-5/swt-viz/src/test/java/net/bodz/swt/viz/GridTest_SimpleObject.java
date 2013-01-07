package net.bodz.swt.viz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Label;
import org.junit.Test;

import user.ASL;
import user.ComplexPerson;
import user.School;
import user.SimplePerson;

import net.bodz.bas.gui.err.GUIException;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.c.test.WidgetTester;
import net.bodz.swt.c3.misc.Timer;
import net.bodz.swt.viz.grid.GridViewBuildStrategy;

/**
 * @style width: 500; height: 400
 */
public class GridTest_SimpleObject
        extends WidgetTester {

    private School school;

    public GridTest_SimpleObject() {
        school = new School("Dream School");
        school.lucy = new SimplePerson("Lucy", 12, false);
        school.lily = new ComplexPerson();
        school.lily.name = "Lily";
        school.lily.setASL(new ASL(20, false, "Hometown"));
    }

    @Test
    public void testGridViz()
            throws Throwable {
        final Label bar = new Label(shell, SWT.NONE);
        new Timer(100, bar) {
            @Override
            public void run() {
                bar.setText(String.valueOf(school));
            }
        };

        SwtRenderContext rc = new SwtRenderContext();
        ISwtGUIRefEntry<School> schoolVar = GUIVars.wrap(school);
        GridViewBuildStrategy style = new GridViewBuildStrategy();
        try {
            style.render(rc, schoolVar, body, SWT.BORDER);
        } catch (ViewBuilderException e) {
            throw new GUIException(e);
        } catch (SWTException e) {
            throw new GUIException(e);
        }
    }

}
