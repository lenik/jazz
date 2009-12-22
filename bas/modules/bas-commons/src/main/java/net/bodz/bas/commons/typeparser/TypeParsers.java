package net.bodz.bas.commons.typeparser;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Map;
import java.util.regex.Pattern;

import net.bodz.bas.api.exceptions.CreateException;
import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.aspect.annotation.Obtain;
import net.bodz.bas.collection.hierarchical.TypeHierMap;
import net.bodz.bas.commons.util.Types;
import net.bodz.bas.nls.TypesNLS;
import net.bodz.bas.typeinfo.Parser;
import net.bodz.bas.typeinfo.TinyParser;

public class TypeParsers {

    public static class GetFromRegistryParser
            extends TinyParser<Object> {

        private final Class<?> registryClass;

        public GetFromRegistryParser(Class<?> registryClass) {
            this.registryClass = registryClass;
        }

        @Override
        public Object parse(String entry)
                throws ParseException {
            try {
                Field field = registryClass.getField(entry);
                return field.get(null);
            } catch (NoSuchFieldException e) {
                throw new ParseException(TypesNLS.getString("TypeParsers.0") + entry //$NON-NLS-1$
                        + TypesNLS.getString("TypeParsers.1") + registryClass, e); //$NON-NLS-1$
            } catch (Exception e) {
                throw new ParseException(TypesNLS.getString("TypeParsers.2") + entry //$NON-NLS-1$
                        + TypesNLS.getString("TypeParsers.3") + registryClass); //$NON-NLS-1$
            }
        }

    }

    public static <T> Parser<T> getStringCtorParser(Class<T> type) {
        try {
            Constructor<T> ctor = type.getConstructor(String.class);
            return new StringCtorParser(ctor);
        } catch (Exception e) {
        }
        return null;
    }

    public static class GetInstanceParser
            extends TinyParser<Object> {

        @Override
        public Object parse(String className)
                throws ParseException {
            try {
                Class<?> clazz = Class.forName(className);
                return Types.getClassInstance(clazz);
            } catch (ClassNotFoundException e) {
                throw new ParseException(e.getMessage(), e);
            } catch (CreateException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class ArrayParser
            extends TinyParser<Object> {

        private Class<?> valtype;
        private Parser<?> valparser;
        private Pattern separator;

        public ArrayParser(Class<?> valtype, Parser<?> valparser, Pattern separator) {
            if (valparser == null)
                throw new NullPointerException("null valparser"); //$NON-NLS-1$
            this.valtype = valtype;
            this.valparser = valparser;
            this.separator = separator;
        }

        public ArrayParser(Class<?> valtype, Pattern separator)
                throws CreateException {
            this(valtype, TypeParsers.guess(valtype), separator);
        }

        public ArrayParser(Class<?> valtype, String separator)
                throws CreateException {
            this(valtype, Pattern.compile(separator));
        }

        @Override
        public Object parse(String s)
                throws ParseException {
            if (s == null)
                return null;
            String[] vals = separator.split(s);
            Object array = Array.newInstance(valtype, vals.length);
            for (int i = 0; i < vals.length; i++) {
                Object val = valparser.parse(vals[i]);
                Array.set(array, i, val);
            }
            return array;
        }

    }

    /**
     * @return <code>null</code> if no explicitly (or implicitly derived) type registered.
     */
    public static Parser<?> get(Class<?> clazz) {
        return registry.floor(clazz);
    }

    public static void register(Class<?> clazz, Parser<?> parser) {
        registry.put(clazz, parser);
    }

    public static boolean unregister(Class<?> clazz) {
        return registry.remove(clazz) != null;
    }

    public static int unregister(Parser<?> parser) {
        int n = 0;
        for (Map.Entry<Class<?>, Parser<?>> entry : registry.entrySet()) {
            if (entry.getValue() == parser)
                if (registry.remove(entry.getKey()) != null)
                    n++;
        }
        return n;
    }

    /**
     * Support types:
     * <ul>
     * <li>Array
     * <li>@Obtain
     * <li>ctor(String)
     * </ul>
     * 
     * @return <code>null</code> if no suitable type parser exists.
     * @throws CreateException
     *             if get from Obtain failed.
     */
    public static Parser guess(Class<?> type)
            throws CreateException {
        Parser parser = get(type);
        if (parser != null)
            return parser;

        if (type.isArray())
            return new ArrayParser(type.getComponentType(), ","); //$NON-NLS-1$

        Obtain obtain = type.getAnnotation(Obtain.class);
        if (obtain != null) {
            Class<? extends Parser<?>> parserClass = obtain.parser();
            if (parserClass == Parser.class)
                parserClass = null;
            parser = (Parser) Types.getClassInstance(parserClass);
            if (parser != null)
                return parser;
            final Class<?> registryClass = obtain.registry();
            if (registryClass != void.class)
                return new GetFromRegistryParser(registryClass);
        }

        parser = getStringCtorParser(type);
        if (parser != null)
            return parser;

        return null;
    }

    public static Parser guess(Class<?> type, String errname)
            throws ParseException {
        Parser parser;
        try {
            parser = guess(type);
        } catch (CreateException e) {
            throw new ParseException(e);
        }
        if (parser == null && errname != null) {
            throw new ParseException(TypesNLS.getString("TypeParsers.6") + errname //$NON-NLS-1$
                    + TypesNLS.getString("TypeParsers.7") + type); //$NON-NLS-1$
        }
        return parser;
    }

    public static Parser guess(Class<?> type, boolean fail)
            throws ParseException {
        return guess(type, fail ? "" : null); //$NON-NLS-1$
    }

    @SuppressWarnings ( "unchecked")
    public static <T> T parse(Class<T> type, String s)
            throws ParseException {
        Parser parser = guess(type, true);
        Object val = parser.parse(s);
        // return type.isPrimitive() ? (T) val : type.cast(val);
        return (T) val;
    }

    private static TypeHierMap<Parser> registry;
    static {
        registry = new TypeHierMap<Parser>();
        registry.put(byte.class, new ByteParser());
        registry.put(Byte.class, new ByteParser());
        registry.put(short.class, new ShortParser());
        registry.put(Short.class, new ShortParser());
        registry.put(int.class, new IntegerParser());
        registry.put(Integer.class, new IntegerParser());
        registry.put(long.class, new LongParser());
        registry.put(Long.class, new LongParser());
        registry.put(float.class, new FloatParser());
        registry.put(Float.class, new FloatParser());
        registry.put(double.class, new DoubleParser());
        registry.put(Double.class, new DoubleParser());
        registry.put(boolean.class, new BooleanParser());
        registry.put(Boolean.class, new BooleanParser());
        registry.put(char.class, new CharacterParser());
        registry.put(Character.class, new CharacterParser());
        registry.put(char[].class, new CharArrayParser());
        registry.put(String.class, new StringParser());
        registry.put(Class.class, new ClassParser());
        registry.put(Charset.class, new CharsetParser());
        registry.put(Pattern.class, new PatternParser());
        registry.put(MessageDigest.class, new MessageDigestParser());
    }

}
