package net.bodz.swt.auto.vt;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.bodz.swt.auto.AbstractVisualType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class VTNumber extends AbstractVisualType {

    @Override
    public Control createControl(Composite parent) {
        Text text = new Text(parent, SWT.NONE);
        return text;
    }

    @Override
    public void render(Object data, Control target) {
        assert data != null;
        assert target instanceof Text;
        Text text = (Text) target;
        text.setText(data.toString());
    }

    @Override
    public Object capture(Control source, Object data) {
        assert data != null;
        assert source instanceof Text;
        Text text = (Text) source;
        String str = text.getText();
        Class<?> clazz = data.getClass();

        Constructor<?> cstr;
        try {
            cstr = clazz.getConstructor(new Class[] { String.class });
        } catch (SecurityException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }

        Object o;
        try {
            o = cstr.newInstance(str);
            return o;
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof NumberFormatException) {
                str = "0";
                try {
                    o = cstr.newInstance(str);
                } catch (InstantiationException e1) {
                    throw new RuntimeException(e1.getMessage(), e1);
                } catch (IllegalAccessException e1) {
                    throw new RuntimeException(e1.getMessage(), e1);
                } catch (InvocationTargetException e1) {
                    throw new RuntimeException(e1.getMessage(), e1);
                }
                return o;
            } else {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    @Override
    public Class<?>[] supportTypes() {
        return SUPPORT_TYPES;
    }

    static Class<?>[] SUPPORT_TYPES = { byte.class, short.class, int.class, long.class,
            float.class, double.class, Number.class, Byte.class, Short.class, Integer.class,
            Long.class, Float.class, Double.class, };
}
