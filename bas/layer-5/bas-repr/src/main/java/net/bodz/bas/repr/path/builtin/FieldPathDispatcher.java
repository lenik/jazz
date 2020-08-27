package net.bodz.bas.repr.path.builtin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class FieldPathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_FIELD;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        Object obj = previous.getTarget();
        if (obj == null)
            throw new PathDispatchException("null target.");

        String fieldName = tokens.peek();
        if (fieldName == null)
            return null;

        Map<String, Field> fieldMap = clsFieldMap.getOrLoad(obj.getClass());

        Field field = fieldMap.get(fieldName);
        if (field == null)
            return null;

        Object result;
        try {
            result = field.get(obj);
        } catch (Exception e) {
            throw new PathDispatchException(e);
        }

        return PathArrival.shift(previous, this, result, tokens);
    }

    static final String CLS_FIELD_MAP_ID;
    static final LazyTypeMap<Map<String, Field>> clsFieldMap;
    static {
        clsFieldMap = TypeMapRegistry.createMap(new EntryLoader());
        CLS_FIELD_MAP_ID = clsFieldMap.getRegisteredId();
    }

    static class EntryLoader
            implements IMapEntryLoader<Class<?>, Map<String, Field>> {

        @Override
        public Map<String, Field> loadValue(Class<?> type)
                throws LazyLoadException {
            Map<String, Field> fieldMap = new HashMap<String, Field>();

            Field[] fields = type.getFields();
            for (Field f : fields)
                fieldMap.put(f.getName(), f);

            return fieldMap;
        }

    } // EntryLoader

}

class FieldKey {

    private final Class<?> clazz;
    private final String fieldName;

    public FieldKey(Class<?> clazz, String fieldName) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (fieldName == null)
            throw new NullPointerException("fieldName");
        this.clazz = clazz;
        this.fieldName = fieldName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + clazz.hashCode();
        result = prime * result + fieldName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof FieldKey;
        FieldKey other = (FieldKey) obj;
        if (!clazz.equals(other.clazz))
            return false;
        if (!fieldName.equals(other.fieldName))
            return false;
        return true;
    }

}
