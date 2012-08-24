package net.bodz.swt.draw.app;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import net.bodz.swt.draw.utils.vt.VisualTypes;

public class DesignerProperties2f {

    Shell shell;
    PropertyEntry2f[] entries;

    Object object;

    public DesignerProperties2f(Object object, Field[] fields) {
        createContents1();
        createEntries(object, fields);
        createContents2();
    }

    public DesignerProperties2f(Object object, String[] fieldNames) {
        createContents1();
        createEntries(object, fieldNames);
        createContents2();
    }

    public DesignerProperties2f(Object object) {
        createContents1();
        createEntries(object);
        createContents2();
    }

    protected void createContents1() {
        shell = new Shell(SWT.DIALOG_TRIM | SWT.RESIZE | SWT.TOOL);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.fill = true;
        shell.setLayout(rowLayout);
        shell.setSize(500, 375);
        shell.setText("Properties");
    }

    protected void createContents2() {
        // ?
    }

    protected void createEntries(Object object, Field[] fields) {
        assert object != null;
        assert fields != null;

        this.object = object;

        int n = fields.length;
        entries = new PropertyEntry2f[n];
        for (int i = 0; i < n; i++) {
            entries[i] = new PropertyEntry2f(shell, object, fields[i]);
        }
    }

    protected void createEntries(Object object, String[] fieldNames) {
        assert object != null;
        assert fieldNames != null;

        this.object = object;

        int n = fieldNames.length;
        entries = new PropertyEntry2f[n];
        for (int i = 0; i < n; i++) {
            entries[i] = new PropertyEntry2f(shell, object, fieldNames[i]);
        }
    }

    protected void createEntries(Object object) {
        assert object != null;

        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getFields();
        int n = fields.length;
        List<PropertyEntry2f> entries = new ArrayList<PropertyEntry2f>(n);

        for (int i = 0; i < n; i++) {
            Field field = fields[i];
            if (!VisualTypes.isSupportedType(field.getType()))
                continue;
            entries.add(new PropertyEntry2f(shell, object, field));
        }

        Method[] methods = clazz.getMethods();
        n = methods.length;

        for (int i = 0; i < n; i++) {
            Method method = methods[i];
            String name = method.getName();

            if (name.startsWith("get")) {
                // type getName(void)
                Class<?>[] params = method.getParameterTypes();

                // is void?
                if (params.length != 0)
                    continue;

                // is type supported?
                Class<?> retType = method.getReturnType();
                if (!VisualTypes.isSupportedType(retType))
                    continue;

                // is name valid?
                String getName = name.substring(3);
                char c0 = getName.charAt(0);
                if (Character.isLowerCase(c0))
                    continue;

                String setName = "set" + getName;
                params = new Class[] { retType };

                try {
                    method = clazz.getMethod(setName, params);
                } catch (NoSuchMethodException e) {
                    continue;
                }

                // add this get/set field.
                name = Character.toLowerCase(c0) + getName.substring(1);

                entries.add(new PropertyEntry2f(shell, object, name));
            }
        } // for

        this.entries = entries.toArray(new PropertyEntry2f[0]);
    }

    public void open() {
        final Display display = Display.getDefault();

        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    public static class SimpleData {
        public int year;
        public String name;
        public float height;
        public Color color = new Color(null, 0, 0, 0);
    }

    public static void main(String[] args) {
        SimpleData sd = new SimpleData();
        sd.year = 1998;
        sd.name = "Lenik.";
        sd.height = 165.3f;

        DesignerProperties2f props = new DesignerProperties2f(sd);
        props.open();
    }
}
