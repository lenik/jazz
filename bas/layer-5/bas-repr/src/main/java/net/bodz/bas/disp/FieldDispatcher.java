package net.bodz.bas.disp;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.disp.builtin.ClassMap;


public class FieldDispatcher
        extends AbstractDispatcher {

    public FieldDispatcher() {
        super();
    }

    public FieldDispatcher(String name) {
        super(name);
    }

    @Override
    public int getOrder() {
        return DispatchConfig.ORDER_FIELD;
    }

    private transient ClassMap<String, Field> classMap;
    {
        classMap = new ClassMap<String, Field>();
    }

    @Override
    public IPathArrival dispatch(IPathArrival context, ITokenQueue tokens)
            throws DispatchException {
        Object obj = context.getTarget();
        if (obj == null)
            return null;

        String fieldName = tokens.peek();
        if (fieldName == null)
            return null;

        Class<? extends Object> contextClass = obj.getClass();

        Map<String, Field> fieldMap = classMap.get(contextClass);
        if (fieldMap == null) {
            fieldMap = new HashMap<String, Field>();

            Field[] fields = contextClass.getFields();
            for (Field f : fields)
                fieldMap.put(f.getName(), f);

            classMap.put(contextClass, fieldMap);
        }

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

        return new PathArrival(context, result, fieldName);
    }

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

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + clazz.hashCode();
        result = prime * result + fieldName.hashCode();
        return result;
    }

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
