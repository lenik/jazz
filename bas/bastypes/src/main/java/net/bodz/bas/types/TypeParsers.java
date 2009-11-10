package net.bodz.bas.types;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Map;
import java.util.regex.Pattern;

import net.bodz.bas.io.ByteOut;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.nls.TypesNLS;
import net.bodz.bas.types.parsers.BooleanParser;
import net.bodz.bas.types.parsers.ByteOutParser;
import net.bodz.bas.types.parsers.ByteParser;
import net.bodz.bas.types.parsers.CharArrayParser;
import net.bodz.bas.types.parsers.CharOutParser;
import net.bodz.bas.types.parsers.CharacterParser;
import net.bodz.bas.types.parsers.CharsetParser;
import net.bodz.bas.types.parsers.ClassParser;
import net.bodz.bas.types.parsers.DoubleParser;
import net.bodz.bas.types.parsers.FileParser;
import net.bodz.bas.types.parsers.FloatParser;
import net.bodz.bas.types.parsers.IntegerParser;
import net.bodz.bas.types.parsers.LoggerParser;
import net.bodz.bas.types.parsers.LongParser;
import net.bodz.bas.types.parsers.MessageDigestParser;
import net.bodz.bas.types.parsers.PatternParser;
import net.bodz.bas.types.parsers.ShortParser;
import net.bodz.bas.types.parsers.StringCtorParser;
import net.bodz.bas.types.parsers.StringParser;
import net.bodz.bas.types.util.Types;
import net.bodz.bas.util.LogTerm;

public class TypeParsers {

    public static class GetFromRegistryParser implements TypeParser {

        private final Class<?> registryClass;

        public GetFromRegistryParser(Class<?> registryClass) {
            this.registryClass = registryClass;
        }

        @Override
        public Object parse(String entry) throws ParseException {
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

    public static TypeParser getStringCtorParser(Class<?> type) {
        try {
            Constructor<?> ctor = type.getConstructor(String.class);
            return new StringCtorParser(ctor);
        } catch (Exception e) {
        }
        return null;
    }

    public static class GetInstanceParser implements TypeParser {

        @Override
        public Object parse(String className) throws ParseException {
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

    public static class ArrayParser implements TypeParser {

        private Class<?> valtype;
        private TypeParser valparser;
        private Pattern separator;

        public ArrayParser(Class<?> valtype, TypeParser valparser, Pattern separator) {
            if (valparser == null)
                throw new NullPointerException("null valparser"); //$NON-NLS-1$
            this.valtype = valtype;
            this.valparser = valparser;
            this.separator = separator;
        }

        public ArrayParser(Class<?> valtype, Pattern separator) throws CreateException {
            this(valtype, TypeParsers.guess(valtype), separator);
        }

        public ArrayParser(Class<?> valtype, String separator) throws CreateException {
            this(valtype, Pattern.compile(separator));
        }

        @Override
        public Object parse(String s) throws ParseException {
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
    public static TypeParser get(Class<?> clazz) {
        return registry.floor(clazz);
    }

    public static void register(Class<?> clazz, TypeParser parser) {
        registry.put(clazz, parser);
    }

    public static boolean unregister(Class<?> clazz) {
        return registry.remove(clazz) != null;
    }

    public static int unregister(TypeParser parser) {
        int n = 0;
        for (Map.Entry<Class<?>, TypeParser> entry : registry.entrySet()) {
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
    public static TypeParser guess(Class<?> type) throws CreateException {
        TypeParser parser = get(type);
        if (parser != null)
            return parser;

        if (type.isArray())
            return new ArrayParser(type.getComponentType(), ","); //$NON-NLS-1$

        Obtain obtain = type.getAnnotation(Obtain.class);
        if (obtain != null) {
            Class<? extends TypeParser> parserClass = obtain.parser();
            if (parserClass == TypeParser.class)
                parserClass = null;
            parser = (TypeParser) Types.getClassInstance(parserClass);
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

    public static TypeParser guess(Class<?> type, String errname) throws ParseException {
        TypeParser parser;
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

    public static TypeParser guess(Class<?> type, boolean fail) throws ParseException {
        return guess(type, fail ? "" : null); //$NON-NLS-1$
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(Class<T> type, String s) throws ParseException {
        TypeParser parser = guess(type, true);
        Object val = parser.parse(s);
        // return type.isPrimitive() ? (T) val : type.cast(val);
        return (T) val;
    }

    private static TypeHierMap<TypeParser> registry;
    static {
        registry = new TypeHierMap<TypeParser>();
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
        registry.put(File.class, new FileParser());
        registry.put(ByteOut.class, new ByteOutParser());
        registry.put(CharOut.class, new CharOutParser());
        registry.put(LogTerm.class, new LoggerParser());
        registry.put(Pattern.class, new PatternParser());
        registry.put(MessageDigest.class, new MessageDigestParser());
    }

}
