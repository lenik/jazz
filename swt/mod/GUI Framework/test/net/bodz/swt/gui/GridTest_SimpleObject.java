package net.bodz.swt.gui;

import net.bodz.bas.a.Doc;
import net.bodz.bas.ui.RenderException;
import net.bodz.bas.ui.UIException;
import net.bodz.bas.ui.a.Border;
import net.bodz.bas.ui.a.Color;
import net.bodz.bas.ui.a.Label;
import net.bodz.bas.ui.a.PreferredSize;
import net.bodz.swt.controls.Timer;
import net.bodz.swt.gui.styles.grid.SWTGridStrategy;
import net.bodz.swt.gui.testtypes.ComplexPerson;
import net.bodz.swt.gui.testtypes.SimplePerson;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.junit.Test;

@PreferredSize(width = 500, height = 400)
public class GridTest_SimpleObject extends BasicGUI {

    public static class School {

        @Doc("School Identifier")
        @Color(value = "blue", back = "#ccccff")
        public final String id;

        public School(String id) {
            this.id = id;
        }

        @Label("Lucy Girl")
        @Border
        public SimplePerson  lucy;

        @Label("Lily Girl")
        public ComplexPerson lily;

        @Override
        public String toString() {
            return "Lucy is " + lucy + "\nLily is " + lily; //$NON-NLS-1$ //$NON-NLS-2$
        }

    }

    private School school;

    public GridTest_SimpleObject() {
        school = new School("Dream School"); //$NON-NLS-1$
        school.lucy = new SimplePerson("Lucy", 12, false); //$NON-NLS-1$
        school.lily = new ComplexPerson();
        school.lily.name = "Lily"; //$NON-NLS-1$
        school.lily.setASL(20, false, "Hometown"); //$NON-NLS-1$
    }

    @Override
    protected void createInitialView(Composite comp) throws UIException, SWTException {
        SWTRenderContext rc = new SWTRenderContext();
        GUIVar<School> schoolVar = GUIVars.wrap(school);
        SWTGridStrategy style = new SWTGridStrategy();
        try {
            style.render(rc, schoolVar, comp, SWT.BORDER);
        } catch (RenderException e) {
            throw new UIException(e);
        } catch (SWTException e) {
            throw new UIException(e);
        }
    }

    @Override
    protected Control createStatusBar(Composite parent) {
        final org.eclipse.swt.widgets.Label bar = new org.eclipse.swt.widgets.Label(parent,
                SWT.NONE);
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
    public void test() throws Throwable {
        run();
    }

    @Override
    protected void checkHangOns() {
    }

}
