package net.bodz.bas.disp;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.err.LazyLoadException;

public class FieldDispatcher
        extends AbstractDispatcher {

    @Override
    public int getPriority() {
        return DispatchConfig.PRIORITY_FIELD;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws DispatchException {
        Object obj = previous.getTarget();
        if (obj == null)
            return null;

        String fieldName = tokens.peek();
        if (fieldName == null)
            return null;

        Map<String, Field> fieldMap = classMap.load(obj.getClass());

        Field field = fieldMap.get(fieldName);
        if (field == null)
            return null;

        tokens.shift();

        Object result;
        try {
            result = field.get(obj);
        } catch (Exception e) {
            throw new DispatchException(e);
        }

        return new PathArrival(previous, result, fieldName, tokens.getRemainingPath());
    }

    static final String CLASS_LOCAL_ID;
    static final ClassLocal<Map<String, Field>> classMap;
    static {
        classMap = ClassLocals.createMap(new EntryLoader());
        CLASS_LOCAL_ID = classMap.getRegisteredId();
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
