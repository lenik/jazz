package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.lang.reflect.Array;
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

    public AbstractJsonDumper(JSONWriter out) {
        this.out = out;
        this.marks = new StackSet<>();
    }

    @Override
    public void dump(Object obj)
            throws IOException {
        formatObj(obj);
    }

    protected void formatObj(Object obj)
            throws IOException {
        if (obj == null) {
            out.value(null);
            return;
        }

        if (!marks.push(obj))
            return;

        _formatRaw(obj);

        marks.pop();
    }

    protected void _formatRaw(Object obj)
            throws IOException {
        Class<?> type = obj.getClass();

        if (type.isArray()) {
            out.array();
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                formatObj(Array.get(obj, i));
            }
            out.endArray();
            return;
        }

        if (obj instanceof Collection<?>) {
            out.array();
            for (Object item : (Collection<?>) obj)
                formatObj(item);
            out.endArray();
            return;
        }

        if (obj instanceof Map<?, ?>) {
            out.object();
            for (Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                out.key(key == null ? null : key.toString());
                formatObj(value);
            }
            out.endObject();
            return;
        }

        IParser<?> parser = Typers.getTyper(type, IParser.class);
        if (parser != null) {
            IFormatter<Object> formatter = Typers.getTyper(type, IFormatter.class);
            if (formatter != null) {
                String form = formatter.format(obj);
                out.value(form);
                return;
            }
        }

        out.object();
        try {
            formatObject(type, obj);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            out.endObject();
        }
    }

    protected abstract void formatObject(Class<?> type, Object obj)
            throws ReflectiveOperationException, IOException;

    protected static final Set<Class<?>> stopClasses = new HashSet<>();
    {
        stopClasses.add(Object.class);
    }

}
