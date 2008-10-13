package net.bodz.swt.gui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.util.MethodParameter;

public class GUIMethod extends _GUIItem implements GUIItems {

    public static class ParamItem extends _GUIItem {

        private final int index;

        public ParamItem(MethodParameter param) {
            super(param.getName(), GUIHint.get(param));
            this.index = param.getIndex();
        }

        @Override
        public Object get(Object obj) throws GUIAccessException {
            if (!(obj instanceof Object[]))
                throw new IllegalArgumentException("not an param array: " + obj);
            Object[] args = (Object[]) obj;
            return args[index];
        }

        @Override
        public void set(Object obj, Object value) throws GUIAccessException {
            if (!(obj instanceof Object[]))
                throw new IllegalArgumentException("not an param array: " + obj);
            Object[] args = (Object[]) obj;
            args[index] = value;
        }

    }

    public class CallContext {
        private Object   obj;
        private Object[] args;

        public CallContext(Object obj, Object... args) {
            this.obj = obj;
            this.args = args;
        }

        public CallContext(Object obj) {
            this(obj, new Object[argc]);
        }

        public Object getObject() {
            return obj;
        }

        public void setObject(Object obj) {
            this.obj = obj;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }

        public Object get(int argIndex) {
            return args[argIndex];
        }

        public void setArgs(int argIndex, Object value) {
            args[argIndex] = value;
        }

    }

    private final Method  method;
    private final int     argc;
    private List<GUIItem> items;

    public GUIMethod(Method method) {
        super(method.getName(), GUIHint.get(method));
        this.method = method;

        MethodParameter[] params = MethodParameter.getParameters(method);
        this.argc = params.length;
        this.items = new ArrayList<GUIItem>(argc);
        for (int i = 0; i < argc; i++)
            this.items.add(new ParamItem(params[i]));
    }

    @Override
    public List<GUIItem> getItems() {
        return items;
    }

    @Override
    public Object get(Object callContext) throws GUIAccessException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(Object obj, Object value) throws GUIAccessException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(Object obj, Object... args) throws GUIAccessException {
        try {
            Control.invoke(method, obj, args);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
