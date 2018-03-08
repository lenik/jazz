package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.Map.Entry;

import javax.xml.bind.DatatypeConverter;

import org.joda.time.base.AbstractDateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.t.set.StackSet;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;

@SuppressWarnings("unchecked")
public abstract class AbstractJsonDumper<self_t>
        implements IJsonDumper {

    protected IJsonOut out;
    protected StackSet<Object> marks;

    protected boolean includeNull = false;
    protected boolean includeFalse = false;

    protected Set<String> includes = new HashSet<>();
    protected Set<String> excludes = new HashSet<>();

    protected int maxDepth;

    public AbstractJsonDumper(IJsonOut out) {
        this.out = out;
        this.marks = new StackSet<>();
    }

    public boolean isIncludeNull() {
        return includeNull;
    }

    public void setIncludeNull(boolean includeNull) {
        this.includeNull = includeNull;
    }

    public boolean isIncludeFalse() {
        return includeFalse;
    }

    public void setIncludeFalse(boolean includeFalse) {
        this.includeFalse = includeFalse;
    }

    public Set<String> getIncludes() {
        return includes;
    }

    public Set<String> getExcludes() {
        return excludes;
    }

    public self_t include(String pattern) {
        includes.add(pattern);
        return (self_t) this;
    }

    public self_t exclude(String pattern) {
        excludes.add(pattern);
        return (self_t) this;
    }

    public self_t depth(int maxDepth) {
        this.maxDepth = maxDepth;
        return (self_t) this;
    }

    @Override
    public void dump(Object obj)
            throws IOException {
        formatRaw_once(obj, 0, "");
    }

    protected boolean isIncluded(String name) {
        if (includes.contains(name))
            return true;
        if (excludes.contains(name))
            return false;
        // Default included if no includes defined.
        return includes.isEmpty();
    }

    protected void formatRaw_once(Object obj, int depth, String prefix)
            throws IOException {
        if (obj == null) {
            out.value(null);
            return;
        }

        if (!isIncluded(prefix)) {
            out.value("<excluded>");
            return;
        }

        if (marks.push(obj)) {
            __formatRaw_nonnull(obj, depth, prefix);
            marks.pop();
        } else {
            out.value("<dup>");
        }
    }

    protected void __formatRaw_nonnull(Object obj, int depth, String prefix)
            throws IOException {
        Class<?> type = obj.getClass();

        if (type.isArray()) {
            out.array();
            try {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    formatRaw_once(Array.get(obj, i), depth + 1, //
                            prefix == null ? null : (prefix + "[" + i + "]"));
                }
            } finally {
                out.endArray();
            }
            return;
        }

        if (obj instanceof Collection<?>) {
            out.array();
            int i = 0;
            try {
                for (Object item : (Collection<?>) obj) {
                    formatRaw_once(item, depth + 1, //
                            prefix == null ? null : (prefix + "[" + i + "]"));
                    i++;
                }
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

                    String qname = prefix == null ? null : (prefix + "{" + key + "}");
                    if (isIncluded(qname)) {
                        out.key(key.toString());
                        formatRaw_once(value, depth + 1, qname);
                    }
                }
            } finally {
                out.endObject();
            }
            return;
        }

        if (ReflectOptions.copyTypes.contains(obj.getClass())) {
            out.value(obj);
            return;
        }

        if (depth > 0)
            if (obj instanceof IJsonSerializable) {
                IJsonSerializable jser = (IJsonSerializable) obj;
                jser.writeObject(out);
                return;
            }

        switch (TypeKind.getTypeId(type)) {
        case TypeId.BYTE:
        case TypeId.SHORT:
        case TypeId.INTEGER:
        case TypeId.LONG:
            out.value(((Number) obj).longValue());
            return;

        case TypeId.FLOAT:
            float fval = ((Number) obj).floatValue();
            if (Float.isInfinite(fval))
                out.value("Infinity");
            else if (Float.isNaN(fval))
                out.value("NaN");
            else
                out.value(fval);
            return;

        case TypeId.DOUBLE:
            double dval = ((Number) obj).doubleValue();
            if (Double.isInfinite(dval))
                out.value("Infinity");
            else if (Double.isNaN(dval))
                out.value("NaN");
            else
                try {
                    out.value(dval);
                } catch (JSONException e) {
                    throw e;
                }
            return;

        case TypeId.BOOLEAN:
            out.value(((Boolean) obj).booleanValue());
            return;

        case TypeId.STRING:
        case TypeId.STRING_BUFFER:
        case TypeId.STRING_BUILDER:
            out.value(obj.toString());
            return;

        case TypeId.DATE:
            Date date = (Date) obj;
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String dateStr = DatatypeConverter.printDateTime(cal);
            out.value(dateStr);
            return;

        case TypeId.JODA_DATETIME:
            AbstractDateTime jodaDateTime = (AbstractDateTime) obj;
            String jodaDateStr = ISODateTimeFormat.dateTime().print(jodaDateTime);
            out.value(jodaDateStr);
            return;

//            TODO Scalar
        default:
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
        }

        int modifiers = type.getModifiers();
        if ((modifiers & Modifier.PUBLIC) == 0) {
            // don't try to dump members from private types.
            formatException(new IllegalAccessException());
            return;
        }

        if (maxDepth > 0 && depth >= maxDepth) {
            out.value(obj);
            return;
        }

        try {
            out.object();
        } catch (JSONException e) {
            throw e;
        }

        try {
            formatObjectMembers(type, obj, depth, prefix);
        } catch (IOException e) {
            throw e;
        } catch (JSONException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                out.endObject();
            } catch (JSONException e) {
                 throw e;
            }
        }
    }

    protected abstract void formatObjectMembers(Class<?> type, Object obj, int depth, String prefix)
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

}
