package net.bodz.bas.err;

import java.lang.reflect.Method;

public class DuplicatedKeyException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    static final String DEFAULT_HINT = "key";

    public DuplicatedKeyException() {
        super();
    }

    public DuplicatedKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedKeyException(String s) {
        super(s);
    }

    public DuplicatedKeyException(Throwable cause) {
        super(cause);
    }

//    public DuplicatedKeyException(Map<?, ?> map, Object key) {
//        this(map, DEFAULT_HINT, key);
//    }
//
//    public DuplicatedKeyException(Map<?, ?> map, String keyHint, Object key) {
//        this(keyHint, key, map.get(key));
//    }

    public DuplicatedKeyException(Object key, Object existingValue) {
        this(DEFAULT_HINT, key, existingValue);
    }

    public DuplicatedKeyException(String keyHint, Object key, Object existingValue) {
        this(keyHint, key, existingValue, "");
    }

    public DuplicatedKeyException(Object key, Object existingValue, String descriptionFormat, Object... args) {
        this(DEFAULT_HINT, key, existingValue, descriptionFormat, args);
    }

    public DuplicatedKeyException(String keyHint, Object key, Object existingValue, String descriptionFormat,
            Object... args) {
        this(keyHint, key, existingValue, String.format(descriptionFormat, args));
    }

    public DuplicatedKeyException(String keyHint, Object key, Object existingValue, String description) {
        super(String.format("%s: %s, existing value: %s. %s", //
                keyHint, key, describe(existingValue), description));
    }

    static String describe(Object o) {
        if (o == null)
            return "null";

        Class<?> clazz = o.getClass();

        boolean hasToString = false;
        try {
            Method method = clazz.getMethod("toString");
            hasToString = method.getDeclaringClass() != Object.class;
        } catch (NoSuchMethodException e) { // unexpected.
        }

        int hash = System.identityHashCode(o);

        if (hasToString)
            return String.format("%s (type %s, @%x)", //
                    o.toString(), o.getClass().getName(), hash);
        else
            return String.format("(type %s, @%x)", //
                    o.getClass().getCanonicalName(), hash);
    }

}
