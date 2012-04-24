package net.bodz.bas.c.object;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import net.bodz.bas.sio.BTreeOut;
import net.bodz.bas.sio.ITreeOut;

public abstract class AbstractDumpTreeProcess<T>
        implements IDumpTreeContext {

    protected ITreeOut out;
    protected Set<Object> occurred;
    protected Stack<DumpTreeFormat> stack = new Stack<DumpTreeFormat>();

    public AbstractDumpTreeProcess() {
        this(new BTreeOut(), new HashSet<Object>());
    }

    public AbstractDumpTreeProcess(ITreeOut out, Set<Object> occurred) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
        this.occurred = occurred;
    }

    @Override
    public DumpTreeFormat getFormat() {
        return stack.firstElement();
    }

    @Override
    public Set<Object> getOccurred() {
        return occurred;
    }

    @Override
    public int getDepth() {
        return stack.size();
    }

    public synchronized void format(T obj, DumpTreeFormat format, int depth) {
        if (format == null)
            return;

        if (format.isShowIdentity())
            printId(format, obj);

        if (format.isShowFields()) {
            if (occurred == null)
                occurred = new HashSet<Object>();

            out.print(" {");
            if (format.isLineBreak()) {
                out.println();
                out.enter();
            } else
                out.print(' ');

            formatFields(obj, obj.getClass(), format, depth);

            if (format.isLineBreak())
                out.leave();
            else
                out.print(' ');
            out.print("}");
        }
    }

    protected abstract void printId(DumpTreeFormat format, T val);

    static final Set<Class<?>> stopClasses = new HashSet<Class<?>>();
    static {
        stopClasses.add(Object.class);
    }

    public static Set<Class<?>> getStopClasses() {
        return stopClasses;
    }

    public static void addStopClass(Class<?> stopClass) {
        stopClasses.add(stopClass);
    }

    void formatFields(Object val, Class<?> clazz, DumpTreeFormat format, int depth) {
        if (stopClasses.contains(clazz))
            return;

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            formatFields(val, superclass, format, depth);

        for (Field field : clazz.getDeclaredFields()) {
            final int skips = Modifier.TRANSIENT | Modifier.STATIC;
            int modifiers = field.getModifiers();
            if ((skips & modifiers) != 0)
                continue;

            // @Skip...

            field.setAccessible(true);
            Object fieldValue;
            try {
                fieldValue = field.get(val);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (fieldValue == null)
                continue;

            // field-name =
            if (format.isShowNames()) {
                String fieldName = field.getName();
                out.print(fieldName);
                if (format.isLineBreak()) // sparse?
                    out.print(" = ");
                else
                    out.print('=');
            }

            try {
                formatValue(fieldValue, format, depth);
            } catch (Exception e) {
                out.print("(error: " + e.getMessage() + ")");
                // e.printStackTrace();
            }

            out.print("; ");
            if (format.isLineBreak())
                out.println();
        } // fields

    } // walk()

    void formatValue(Object val, DumpTreeFormat format, int depth) {
        if (!occurred.add(val)) {
            // duplicated occurence, avoid if acyclic.
            int dupId = System.identityHashCode(val);
            out.print('#');
            out.print(Integer.toHexString(dupId));
            return;
        }

        DumpTreeFormat childFormat = format.getNestedFormat();
        int childDepth = getDepth();

        if (val instanceof ITreeDumpable)
            ((ITreeDumpable) val).dump(out, this);

        else if (val.getClass().isArray())
            formatArray(val, childFormat, childDepth);

        else if (val instanceof Collection<?>)
            formatCollection((Collection<?>) val, childFormat, childDepth);

        else if (val instanceof Map<?, ?>)
            formatMap((Map<?, ?>) val, childFormat, childDepth);

        else
            out.print(val);
    }

    protected void formatArray(Object val, DumpTreeFormat format, int depth) {
        int n = Array.getLength(val);
        if (n == 0) {
            out.print("[]");
            return;
        }

        out.print("[");

        if (format.isLineBreak()) {
            out.enter();
            out.println();
        } else
            out.print(' ');

        {
            for (int i = 0; i < n; i++) {
                Object cell = Array.get(val, i);

                formatValue(cell, format, depth);

                out.print(", ");
                if (format.isLineBreak())
                    out.println();
            }
        }

        if (format.isLineBreak())
            out.leave();
        else
            out.print(' ');
        out.print("]");
    }

    protected void formatCollection(Collection<?> val, DumpTreeFormat format, int depth) {
        Collection<?> collection = (Collection<?>) val;
        if (collection.isEmpty()) {
            out.print("()");
            return;
        }

        out.print("(");

        if (format.isLineBreak()) {
            out.enter();
            out.println();
        } else
            out.print(' ');

        {
            for (Object item : collection) {

                formatValue(item, format, depth);

                out.print(", ");
                if (format.isLineBreak())
                    out.println();
            }
        }

        if (format.isLineBreak())
            out.leave();
        else
            out.print(' ');

        out.print(")");
    }

    protected void formatMap(Map<?, ?> val, DumpTreeFormat format, int depth) {
        Map<?, ?> map = (Map<?, ?>) val;
        if (map.isEmpty()) {
            out.print("{}");
            return;
        }

        out.print("{");

        if (format.isLineBreak()) {
            out.enter();
            out.println();
        } else
            out.print(' ');

        {
            for (Entry<?, ?> entry : map.entrySet()) {

                out.print(entry.getKey());
                out.print(" => ");

                formatValue(entry.getValue(), format, depth);

                out.print(", ");
                if (format.isLineBreak())
                    out.println();
            }
        }

        if (format.isLineBreak())
            out.leave();
        else
            out.print(' ');
        out.print("}");
    }

    @Override
    public String toString() {
        return out.toString();
    }

}