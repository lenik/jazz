package net.bodz.swt.gui;

import net.bodz.bas.a.Doc;
import net.bodz.bas.gui.GUIException;
import net.bodz.bas.gui.RenderException;
import net.bodz.bas.gui.a.Border;
import net.bodz.bas.gui.a.Color;
import net.bodz.bas.gui.a.Label;
import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.swt.controls.Timer;
import net.bodz.swt.gui.styles.grid.SWTGridStrategy;
import net.bodz.swt.gui.testtypes.ComplexPerson;
import net.bodz.swt.gui.testtypes.SimplePerson;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

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
    protected void createInitialView(Composite comp) throws GUIException,
            SWTException {
        GUIVar<School> schoolVar = GUIVars.wrap(school);

        SWTGridStrategy style = new SWTGridStrategy();
        try {
            style.render(schoolVar, comp, SWT.BORDER);
        } catch (RenderException e) {
            throw new GUIException(e);
        } catch (SWTException e) {
            throw new GUIException(e);
        }
    }

    @Override
    protected Control createStatusBar(Composite parent) {
        final org.eclipse.swt.widgets.Label bar = new org.eclipse.swt.widgets.Label(
                parent, SWT.NONE);
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

    public static void main(String[] args) throws Throwable {
        new GridTest_SimpleObject().run(args);
    }

}
