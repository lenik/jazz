package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.base.AbstractDateTime;
import org.joda.time.format.ISODateTimeFormat;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.t.map.IMarksetWithPath;
import net.bodz.bas.t.map.MarksetWithPath;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.fork.org.json.JSONException;

@SuppressWarnings("unchecked")
public abstract class AbstractJsonDumper<self_t>
        implements
            IJsonDumper {

    static final Logger logger = LoggerFactory.getLogger(AbstractJsonDumper.class);

    protected IJsonOut out;
    JsonFormOptions opts;

    protected boolean keyed;
    protected IMarksetWithPath markset;

    protected boolean includeNull = false;
    protected boolean includeFalse = false;

    protected Set<String> includes = new HashSet<String>();
    protected Set<String> excludes = new HashSet<String>();

    protected int maxDepth;

    String hintProperty = "_hint";

    public AbstractJsonDumper(IJsonOut out) {
        this.out = out;
        this.markset = new MarksetWithPath();
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

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public self_t depth(int maxDepth) {
        this.maxDepth = maxDepth;
        return (self_t) this;
    }

    @Override
    public void dump(Object obj)
            throws IOException, FormatException {
        _dumpOnce(false, obj, 0, null);
    }

    @Override
    public void dumpBoxed(Object obj)
            throws IOException, FormatException {
        _dumpOnce(true, obj, 0, null);
    }

    public void dumpBoxedWithName(String name, Object obj)
            throws IOException, FormatException {
        markset.enter(name);
        dumpBoxed(obj);
        markset.leave();
    }

    protected boolean isIncluded(String name) {
        if (includes.contains(name))
            return true;
        if (excludes.contains(name))
            return false;
        // Default included if no includes defined.
        return includes.isEmpty();
    }

    String hint(String name) {
        if (name == null)
            return hintProperty;
        else
            return name;
    }

    void beginBox(boolean boxed, int depth, String name) {
        if (boxed) {
            if (depth == 0) {
                out.object();
                out.key("root");
            }
        } else {
            out.key(hint(name));
        }
    }

    void endBox(boolean boxed, int depth) {
        if (boxed) {
            if (depth == 0)
                out.endObject();
        } else {
        }
    }

    protected void _dumpOnce(boolean scalar, Object obj, int depth, String name)
            throws IOException, FormatException {
        if (obj == null) {
            beginBox(scalar, depth, name);
            out.value(null);
            endBox(scalar, depth);
            return;
        }

        String path = markset.path(name);
        if (!isIncluded(path)) {
            beginBox(scalar, depth, name);
            out.value("<excluded>");
            endBox(scalar, depth);
            return;
        }

        if (name != null)
            markset.enter(name);
        try {
            String oldPath = markset.lookup(obj);
            if (oldPath != null) {
                beginBox(scalar, depth, name);
                out.value("<ref:" + oldPath + ">");
                endBox(scalar, depth);
                return;
            }
            _dumpImpl(scalar, obj, depth, name);
        } finally {
            if (name != null)
                markset.leave();
        }
    }

    protected boolean _dumpImpl(boolean scalar, @NotNull Object obj, int depth, String _name)
            throws IOException, FormatException {
        Class<?> type = obj.getClass();
        if (type.isEnum()) {
            out.value(obj);
            return true;
        }

        if (type.isArray()) {
            markset.addMark(obj);
            return dumpArray(scalar, obj, depth);
        }

        if (obj instanceof Collection<?>) {
            markset.addMark(obj);
            return dumpCollection(scalar, (Collection<?>) obj, depth);
        }

        if (obj instanceof Map<?, ?>) {
            markset.addMark(obj);
            return dumpMap(scalar, (Map<?, ?>) obj, depth);
        }

        if (obj instanceof IJsonForm) {
            markset.addMark(obj);
            if (dumpJsonSerializable(scalar, (IJsonForm) obj, depth))
                return true;
        }

        if (ReflectOptions.copyTypes.contains(obj.getClass())) {
            beginBox(scalar, depth, _name);
            out.value(obj);
            endBox(scalar, depth);
            return true;
        }

        Object simpleVal = dumpSimpleTypes(type, obj, depth);
        if (simpleVal != null) {
            beginBox(scalar, depth, _name);
            out.value(simpleVal);
            endBox(scalar, depth);
            return true;
        }

        Object scalarVal = dumpScalar(type, obj, depth);
        if (scalarVal != null) {
            beginBox(scalar, depth, _name);
            out.value(scalarVal);
            endBox(scalar, depth);
            return true;
        }

        int modifiers = type.getModifiers();
        if ((modifiers & Modifier.PUBLIC) == 0) {
            // don't try to dump members from non-public types.
            formatException(scalar, depth + 1, new IllegalAccessException());
            return true;
        }

        if (maxDepth > 0 && depth >= maxDepth) {
            beginBox(scalar, depth, _name);
            out.value(obj);
            endBox(scalar, depth);
            return true;
        }

        try {
            markset.addMark(obj);
            return dumpGenericObject(scalar, type, obj, depth);
        } catch (JSONException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // TODO Not used..
    protected boolean _dumpImplTerm(boolean scalar, @NotNull Object obj, int depth, String _name)
            throws IOException, FormatException {
        Class<?> type = obj.getClass();
        if (type.isEnum()) {
            out.value(obj);
            return true;
        }

        if (type.isArray()) {
            out.value(obj);
            return true;
        }

        if (obj instanceof Collection<?>) {
            out.value(obj);
            return true;
        }

        if (obj instanceof Map<?, ?>) {
            out.value(obj);
            return true;
        }

        if (obj instanceof IJsonForm) {
            markset.addMark(obj);
            if (dumpJsonSerializable(scalar, (IJsonForm) obj, depth))
                return true;
        }

        if (ReflectOptions.copyTypes.contains(obj.getClass())) {
            beginBox(scalar, depth, _name);
            out.value(obj);
            endBox(scalar, depth);
            return true;
        }

        Object simpleVal = dumpSimpleTypes(type, obj, depth);
        if (simpleVal != null) {
            beginBox(scalar, depth, _name);
            out.value(simpleVal);
            endBox(scalar, depth);
            return true;
        }

        Object scalarVal = dumpScalar(type, obj, depth);
        if (scalarVal != null) {
            beginBox(scalar, depth, _name);
            out.value(scalarVal);
            endBox(scalar, depth);
            return true;
        }

        int modifiers = type.getModifiers();
        if ((modifiers & Modifier.PUBLIC) == 0) {
            // don't try to dump members from non-public types.
            formatException(scalar, depth + 1, new IllegalAccessException());
            return true;
        }

        beginBox(scalar, depth, _name);
        out.value(obj);
        endBox(scalar, depth);
        return true;
    }

    protected boolean dumpArray(boolean boxed, Object obj, int depth)
            throws IOException, FormatException {
        if (boxed)
            out.array();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            String name = "[" + i + "]";
            markset.enter(name);
            _dumpOnce(true, Array.get(obj, i), depth + 1, name);
            markset.leave();
        }
        if (boxed)
            out.endArray();
        return true;
    }

    protected boolean dumpCollection(boolean boxed, Collection<?> collection, int depth)
            throws IOException, FormatException {
        if (boxed)
            out.array();
        int i = 0;
        for (Object item : collection) {
            String name = "[" + i + "]";
            markset.enter(name);
            _dumpOnce(true, item, depth + 1, name);
            markset.leave();
            i++;
        }
        if (boxed)
            out.endArray();
        return true;
    }

    protected boolean dumpMap(boolean boxed, Map<?, ?> map, int depth)
            throws IOException, FormatException {
        if (boxed)
            out.object();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key == null) // null-key isn't supported in json.
                continue;

            if (value == null)
                if (!includeNull)
                    continue;

            String name = "['" + key + "']";
            markset.enter(name);
            String qname = markset.path(name);
            if (isIncluded(qname)) {
                out.key(key.toString());
                _dumpOnce(true, value, depth + 1, name);
            }
            markset.leave();
        }
        if (boxed)
            out.endObject();
        return true;
    }

    protected boolean dumpJsonSerializable(boolean scalar, IJsonForm jser, int depth)
            throws IOException, FormatException {
        if (depth <= 0)
            return false;

        try {
            Method writeObject = jser.getClass().getMethod("writeObject", IJsonOut.class);
            DefaultDump defaultDump = writeObject.getDeclaredAnnotation(DefaultDump.class);
            if (defaultDump != null)
                return false;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        jser.jsonOut(out, opts, scalar);
        return true;
    }

    protected Object dumpSimpleTypes(Class<?> type, Object obj, int depth)
            throws IOException, FormatException {
        switch (TypeKind.getTypeId(type)) {
        case TypeId.BYTE:
        case TypeId.SHORT:
        case TypeId.INTEGER:
        case TypeId.LONG:
            return ((Number) obj).longValue();

        case TypeId.FLOAT:
            float fval = ((Number) obj).floatValue();
            if (Float.isInfinite(fval))
                return "Infinity";
            if (Float.isNaN(fval))
                return "NaN";
            return fval;

        case TypeId.DOUBLE:
            double dval = ((Number) obj).doubleValue();
            if (Double.isInfinite(dval))
                return "Infinity";
            if (Double.isNaN(dval))
                return "NaN";
            try {
                return dval;
            } catch (JSONException e) {
                throw e;
            }

        case TypeId.BOOLEAN:
            return ((Boolean) obj).booleanValue();

        case TypeId.STRING:
        case TypeId.STRING_BUFFER:
        case TypeId.STRING_BUILDER:
            return obj.toString();

        case TypeId.DATE:
            Date date = (Date) obj;

            // XXX to be reviewed.
            Calendar cal = Calendar.getInstance();
            int offset = cal.getTimeZone().getOffset(date.getTime());
            long localTime = date.getTime() + offset;

            String dateStr = Dates.ISO8601.format(localTime);
            return dateStr;

        case TypeId.JODA_DATETIME:
            AbstractDateTime jodaDateTime = (AbstractDateTime) obj;
            String jodaDateStr = ISODateTimeFormat.dateTime().print(jodaDateTime);
            return jodaDateStr;
        }
        return null;
    }

    protected Object dumpScalar(Class<?> type, Object obj, int depth)
            throws IOException, FormatException {
        if (iString.class.isAssignableFrom(type))
            return obj;

        IParser<?> parser;
        try {
            parser = Typers.getTyper(type, IParser.class);
        } catch (Throwable e) {
            logger.error("Failed to get parser of " + type, e);
            return null;
        }
        if (parser != null) {
            IFormatter<Object> formatter = Typers.getTyper(type, IFormatter.class);
            if (formatter != null) {
                String form = formatter.format(obj);
                if (form == null && includeNull)
                    form = "(null)";
                return form;
            }
        }
        return null;
    }

    protected abstract boolean dumpGenericObject(boolean boxed, Class<?> type, Object obj, int depth)
            throws IOException, FormatException;

    protected void formatException(boolean boxed, int depth, Throwable e) {
        if (boxed)
            out.object();
        try {
            out.key("type");
            out.value(e.getClass().getName());
            out.key("message");
            out.value(e.getMessage());

            Throwable cause = e.getCause();
            if (cause != null) {
                out.key("cause");
                formatException(true, depth + 1, cause);
            }
        } finally {
            if (boxed)
                out.endObject();
        }
    }

    @Override
    public String toString() {
        return "{" + markset + "}\n" + out;
    }

}
