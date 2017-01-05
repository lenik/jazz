package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONWriter;

import net.bodz.bas.t.set.StackSet;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;

public abstract class AbstractJsonDumper
        implements IJsonDumper {

    protected JSONWriter out;
    protected StackSet<Object> marks;

    protected boolean includeNull = false;
    protected boolean includeFalse = false;

    public AbstractJsonDumper(JSONWriter out) {
        this.out = out;
        this.marks = new StackSet<>();
    }

    @Override
    public void dump(Object obj)
            throws IOException {
        formatRaw(obj, 0);
    }

    protected void formatRaw(Object obj, int depth)
            throws IOException {
        if (obj == null) {
            out.value(null);
            return;
        }

        if (marks.push(obj)) {
            __formatRaw(obj, depth);
            marks.pop();
        }
    }

    protected void __formatRaw(Object obj, int depth)
            throws IOException {
        Class<?> type = obj.getClass();

        if (type.isArray()) {
            out.array();
            try {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    formatRaw(Array.get(obj, i), depth + 1);
                }
            } finally {
                out.endArray();
            }
            return;
        }

        if (obj instanceof Collection<?>) {
            out.array();
            try {
                for (Object item : (Collection<?>) obj)
                    formatRaw(item, depth + 1);
            } finally {
                out.endArray();
            }
            return;
        }

        if (obj instanceof Map<?, ?>) {
            out.object();
            try {
                for (Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    if (key == null) // null-key isn't supported in json.
                        continue;

                    if (value == null)
                        if (!includeNull)
                            continue;

                    out.key(key.toString());
                    formatRaw(value, depth + 1);
                }
            } finally {
                out.endObject();
            }
            return;
        }

        if (copyTypes.contains(obj.getClass())) {
            out.value(obj);
            return;
        }

        if (depth > 0)
            if (obj instanceof IJsonSerializable) {
                IJsonSerializable jser = (IJsonSerializable) obj;
                jser.writeObject(out);
                return;
            }

        IParser<?> parser = Typers.getTyper(type, IParser.class);
        if (parser != null) {
            IFormatter<Object> formatter = Typers.getTyper(type, IFormatter.class);
            if (formatter != null) {
                String form = formatter.format(obj);
                if (form != null || includeNull)
                    out.value(form);
                return;
            }
        }

        int modifiers = type.getModifiers();
        if ((modifiers & Modifier.PUBLIC) == 0) {
            // don't try to dump members from private types.
            formatException(new IllegalAccessException());
            return;
        }

        out.object();
        try {
            formatObjectMembers(type, obj, depth);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            out.endObject();
        }
    }

    protected abstract void formatObjectMembers(Class<?> type, Object obj, int depth)
            throws ReflectiveOperationException, IOException;

    protected void formatException(Throwable e) {
        out.object();
        out.key("type");
        out.value(e.getClass().getName());
        out.key("message");
        out.value(e.getMessage());

        Throwable cause = e.getCause();
        if (cause != null) {
            out.key("cause");
            formatException(cause);
        }

        out.endObject();
    }

    protected static final Set<Class<?>> stopClasses = new HashSet<>();
    protected static final Set<Class<?>> copyTypes = new HashSet<>();
    {
        stopClasses.add(Object.class);

        Class<?>[] types1 = { byte.class, short.class, int.class, long.class, float.class, double.class, boolean.class,
                Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class,
                BigDecimal.class, };
        copyTypes.addAll(Arrays.asList(types1));
    }

}
