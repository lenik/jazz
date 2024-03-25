package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.fork.org.json.JSONException;

@SuppressWarnings("unchecked")
public abstract class AbstractJsonDumper<self_t>
        implements
            IJsonDumper {

    static final Logger logger = LoggerFactory.getLogger(AbstractJsonDumper.class);

    protected IJsonOut out;
    JsonFormOptions opts;
    protected JsonOutFn outFn;

    protected final PrefixPathBuilder contextPath = new PrefixPathBuilder(null);
    protected final Map<Object, String> nodePathMap = new IdentityHashMap<>();

    protected boolean includeNull = false;
    protected boolean includeFalse = false;
    protected boolean encodeExcluded = true; // use <excluded:maxDepth> for excluded entries.

    protected Set<String> includes = new HashSet<String>();
    protected Set<String> excludes = new HashSet<String>();

    protected int maxDepth = -1;

    public AbstractJsonDumper(IJsonOut out) {
        this.out = out;
        this.outFn = new JsonOutFn(out);
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

    public self_t maxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
        return (self_t) this;
    }

    boolean checkDepth(int depth) {
        if (maxDepth == -1)
            return true;
        return depth <= maxDepth;
    }

    protected boolean isIncluded(String name) {
        if (includes.contains(name))
            return true;
        if (excludes.contains(name))
            return false;
        // Default included if no includes defined.
        return includes.isEmpty();
    }

    protected boolean beginChild(String name) {
        String path = contextPath.childPath(name);
        if (! isIncluded(path)) {
            out.value(encodeExcluded("path:" + path));
            return false;
        }
        contextPath.enter(name);
        return true;
    }

    protected boolean beginChild(int index) {
        String path = contextPath.childPath(index);
        if (! isIncluded(path)) {
            out.value(encodeExcluded("path:" + path));
            return false;
        }
        contextPath.enter(index);
        return true;
    }

    protected void endChild() {
        contextPath.leave();
    }

    @Override
    public void dump(Object obj)
            throws IOException, FormatException {
        dumpVariant(obj, 0);
    }

    protected boolean dumpVariant(@NotNull Object obj, int depth)
            throws IOException, FormatException {
        Class<?> type = obj.getClass();
        if (type.isEnum()) {
            out.value(obj);
            return true;
        }

        if (type.isArray())
            return dumpArray(obj, false, depth);

        if (obj instanceof Collection<?>)
            return dumpCollection((Collection<?>) obj, false, depth);

        if (obj instanceof Map<?, ?>)
            return dumpMap((Map<?, ?>) obj, false, depth);

        if (type == JsonVariant.class) {
            JsonVariant jv = (JsonVariant) obj;
            JsonVariant encoded = JsonWithRef.encode(jv);
            out.variant(encoded);
            return true;
        }

        if (obj instanceof IJsonForm) {
            IJsonForm jsonForm = (IJsonForm) obj;
            if (! jsonForm.isJsonOutByDumper()) {
                if (depth > 0) {
                    nodePathMap.put(obj, contextPath.getPath());
                    if (outFn.jsonForm(jsonForm, opts, false))
                        return true;
                }
            }
        }

        if (ReflectOptions.copyTypes.contains(obj.getClass())) {
            out.value(obj);
            return true;
        }

        Object simpleEncoded = JsonCodec.encodeSimpleTypes(type, obj);
        if (simpleEncoded != null) {
            out.value(simpleEncoded);
            return true;
        }

        Object textForm = JsonCodec.encodeTextForm(type, obj);
        if (textForm != null) {
            out.value(textForm);
            return true;
        }

        int modifiers = type.getModifiers();
        if ((modifiers & Modifier.PUBLIC) == 0) {
            // don't try to dump members from non-public types.
            outFn.throwable(new IllegalAccessException());
            return true;
        }

        if (! checkDepth(depth)) {
            out.value(encodeExcluded("maxDepth"));
            return true;
        }

        try {
            return dumpObject(type, obj, false, depth);
        } catch (JSONException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean dumpArray(Object array, boolean spread)
            throws IOException, FormatException {
        return dumpArray(array, spread, 0);
    }

    boolean dumpArray(Object array, boolean spread, int depth)
            throws IOException, FormatException {
        if (! add(array))
            return false;
        if (! spread)
            out.array();
        int length = Array.getLength(array);
        for (int index = 0; index < length; index++) {
            if (beginChild(index))
                try {
                    dumpVariant(Array.get(array, index), depth + 1);
                } finally {
                    contextPath.leave();
                }
        }
        if (! spread)
            out.endArray();
        return true;
    }

    @Override
    public boolean dumpCollection(Collection<?> collection, boolean spread)
            throws IOException, FormatException {
        return dumpCollection(collection, spread, 0);
    }

    boolean dumpCollection(Collection<?> collection, boolean spread, int depth)
            throws IOException, FormatException {
        if (! add(collection))
            return false;
        if (! spread)
            out.array();
        int index = 0;
        for (Object item : collection) {
            if (beginChild(index++))
                try {
                    dumpVariant(item, depth + 1);
                } finally {
                    endChild();
                }
        }
        if (! spread)
            out.endArray();
        return true;
    }

    protected boolean add(Object obj) {
        if (nodePathMap.containsKey(obj)) {
            String oldPath = nodePathMap.get(obj);
            out.value(JsonWithRef.encodeRef(oldPath));
            return false;
        } else {
            nodePathMap.put(obj, contextPath.getPath());
            return true;
        }
    }

    @Override
    public boolean dumpMap(Map<?, ?> map, boolean spread)
            throws IOException, FormatException {
        return dumpMap(map, spread, 0);
    }

    boolean dumpMap(Map<?, ?> map, boolean spread, int depth)
            throws IOException, FormatException {
        if (! add(map))
            return false;
        if (! spread)
            out.object();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            if (key == null) // null-key isn't supported in json.
                continue;

            if (value == null)
                if (! includeNull)
                    continue;

            if (beginChild(key.toString()))
                try {
                    out.key(key.toString());
                    dumpVariant(value, depth + 1);
                } finally {
                    contextPath.leave();
                }
        }
        if (! spread)
            out.endObject();
        return true;
    }

    @Override
    public boolean dumpObject(Class<?> type, Object obj, boolean spread)
            throws IOException, FormatException {
        return dumpObject(type, obj, spread, 0);
    }

    protected abstract boolean dumpObject(Class<?> type, Object obj, boolean spread, int depth)
            throws IOException, FormatException;

    String encodeExcluded(String reason) {
        if (encodeExcluded)
            return JsonCodec.encodeExcluded(reason);
        else
            return null;
    }

    @Override
    public String toString() {
        return "{" + nodePathMap + "}\n" + out;
    }

}
