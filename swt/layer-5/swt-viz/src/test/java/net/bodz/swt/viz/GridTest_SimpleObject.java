package net.bodz.swt.viz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.junit.Test;

import net.bodz.bas.gui.a.Border;
import net.bodz.bas.gui.a.Color;
import net.bodz.bas.gui.a.Label;
import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.gui.ia.UIException;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.c3.misc.Timer;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.core.GUIVars;
import net.bodz.swt.viz.core.SwtEntry;
import net.bodz.swt.viz.style.grid.GridVisualization;
import net.bodz.swt.viz.testtypes.ComplexPerson;
import net.bodz.swt.viz.testtypes.SimplePerson;

@PreferredSize(width = 500, height = 400)
public class GridTest_SimpleObject
        extends BasicGUI {

    public static class School {

        /**
         * School Identifier
         */
        @Color(value = "blue", back = "#ccccff")
        public final String id;

        public School(String id) {
            this.id = id;
        }

        @Label("Lucy Girl")
        @Border
        public SimplePerson lucy;

        @Label("Lily Girl")
        public ComplexPerson lily;

        @Override
        public String toString() {
            return "Lucy is " + lucy + "\nLily is " + lily;
        }

    }

    private School school;

    public GridTest_SimpleObject() {
        school = new School("Dream School");
        school.lucy = new SimplePerson("Lucy", 12, false);
        school.lily = new ComplexPerson();
        school.lily.name = "Lily";
        school.lily.setASL(20, false, "Hometown");
    }

    @Override
    protected void createInitialView(Composite holder)
            throws UIException {
        SWTRenderContext rc = new SWTRenderContext();
        SwtEntry<School> schoolVar = GUIVars.wrap(school);
        GridVisualization style = new GridVisualization();
        try {
            style.render(rc, schoolVar, holder, SWT.BORDER);
        } catch (RenderException e) {
            throw new UIException(e);
        } catch (SWTException e) {
            throw new UIException(e);
        }
    }

    @Override
    protected Control createStatusBar(Composite parent) {
        final org.eclipse.swt.widgets.Label bar = new org.eclipse.swt.widgets.Label(parent, SWT.NONE);
        new Timer(100) {

            @Override
            public void run() {
                bar.setText(String.valueOf(school));
            }

            @Override
            public boolean isDisposed() {
                return bar.isDisposed();
            }

        };
        return bar;
    }

    @Test
    public void test()
            throws Throwable {
        execute();
    }

    @Override
    protected void checkHangOns() {
    }

}