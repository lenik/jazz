package net.bodz.swt.viz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Label;
import org.junit.Test;

import user.ComplexPerson;
import user.School;
import user.SimplePerson;

import net.bodz.bas.gui.err.GUIException;
import net.bodz.bas.gui.mda.PreferredSize;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.c.test.WidgetTester;
import net.bodz.swt.c3.misc.Timer;

@PreferredSize(width = 500, height = 400)
public class GridTest_SimpleObject
        extends WidgetTester {

    private School school;

    public GridTest_SimpleObject() {
        school = new School("Dream School");
        school.lucy = new SimplePerson("Lucy", 12, false);
        school.lily = new ComplexPerson();
        school.lily.name = "Lily";
        school.lily.setASL(20, false, "Hometown");
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

        SWTRenderContext rc = new SWTRenderContext();
        IRefEntry_SWT<School> schoolVar = GUIVars.wrap(school);
        GridVisualization style = new GridVisualization();
        try {
            style.render(rc, schoolVar, body, SWT.BORDER);
        } catch (RenderException e) {
            throw new GUIException(e);
        } catch (SWTException e) {
            throw new GUIException(e);
        }
    }

}
