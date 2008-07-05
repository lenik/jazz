package net.bodz.bas.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import net.bodz.bas.io.ByteOut;
import net.bodz.bas.io.ByteOuts;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.lang.IVoid;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.ALogs;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.types.Obtain;

public class TypeParsers {

    @SuppressWarnings("unchecked")
    public static <T> TypeParser<T> guess(Class<T> type) {
        TypeParser<T> parser = get(type);
        if (parser != null)
            return parser;
        if (type.isArray())
            return new ArrayParser<T>(type.getComponentType(), ",");
        Obtain obtain = type.getAnnotation(Obtain.class);
        if (obtain != null) {
            Class<? extends TypeParser<?>> parserClass = obtain.parser();
            parser = (TypeParser<T>) Util.getClassInstance(parserClass);
            if (parser != null)
                return parser;
            final Class<?> registryClass = obtain.registry();
            if (registryClass != void.class)
                return new TypeParser<T>() {
                    @Override
                    public T parse(String text) throws ParseException {
                        try {
                            Field field = registryClass.getField(text);
                            return (T) field.get(null);
                        } catch (NoSuchFieldException e) {
                            throw new ParseException(e.getMessage(), e);
                        } catch (Exception e) {
                            throw new ParseException("Failed to load entry "
                                    + text + " in registry " + registryClass);
                        }
                    }
                };
        }
        parser = StringCtor(type);
        if (parser == null)
            throw new CLIError("don't know how to parser " + type);
        return parser;
    }

    public static class Void implements IVoid, TypeParser<Object> {
        private Void() {
        }

        @Override
        public Object parse(String text) throws ParseException {
            throw new UnexpectedException();
        }
    }

    public static <T> TypeParser<T> StringCtor(Class<T> type) {
        try {
            Constructor<T> ctor = type.getConstructor(String.class);
            return new StringCtor<T>(ctor);
        } catch (Exception e) {
        }
        return null;
    }

    public static class StringCtor<T> implements TypeParser<T> {

        private final Constructor<T> ctor;

        public StringCtor(Constructor<T> constructor) throws CLIError {
            this.ctor = constructor;
        }

        public StringCtor(Class<T> type) throws CLIError {
            try {
                ctor = type.getConstructor(String.class);
            } catch (SecurityException e) {
                throw new CLIError(e.getMessage(), e);
            } catch (NoSuchMethodException e) {
                throw new CLIError(e.getMessage(), e);
            }
        }

        @Override
        public T parse(String text) throws ParseException {
            try {
                return ctor.newInstance(text);
            } catch (IllegalArgumentException e) {
                throw new CLIError(e.getMessage(), e);
            } catch (InstantiationException e) {
                throw new CLIError(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new CLIError(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                throw new CLIError(e.getMessage(), e);
            }
        }

    }

    public static class ClassInstanceParser implements TypeParser<Object> {

        @Override
        public Object parse(String className) throws ParseException {
            try {
                Class<?> clazz = Class.forName(className);
                return Util.getClassInstance(clazz);
            } catch (ClassNotFoundException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class ArrayParser<T> implements TypeParser<T> {

        private Class<?>      valtype;
        private TypeParser<?> valparser;
        private Pattern       separator;

        public ArrayParser(Class<?> valtype, Pattern separator) {
            this.valtype = valtype;
            this.valparser = TypeParsers.guess(valtype);
            this.separator = separator;
        }

        public ArrayParser(Class<?> valtype, String separator) {
            this(valtype, Pattern.compile(separator));
        }

        @Override
        public T parse(String s) throws ParseException {
            if (s == null)
                return null;
            String[] vals = separator.split(s);
            @SuppressWarnings("unchecked")
            T array = (T) Array.newInstance(valtype, vals.length);
            for (int i = 0; i < vals.length; i++) {
                Object val = valparser.parse(vals[i]);
                Array.set(array, i, val);
            }
            return array;
        }

    }

    private static Map<Class<?>, TypeParser<?>> registry;
    static {
        registry = new HashMap<Class<?>, TypeParser<?>>();
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
        registry.put(Class.class, new ClassParser());
        registry.put(Charset.class, new CharsetParser());
        registry.put(File.class, new FileParser());
        registry.put(ByteOut.class, new ByteOutParser());
        registry.put(CharOut.class, new CharOutParser());
        registry.put(ALog.class, new ALogParser());
        registry.put(Pattern.class, new PatternParser());
    }

    @SuppressWarnings("unchecked")
    public static <T> TypeParser<T> get(Class<T> clazz) {
        return (TypeParser<T>) registry.get(clazz);
    }

    public static void register(Class<?> clazz, TypeParser<?> parser) {
        registry.put(clazz, parser);
    }

    public static boolean unregister(Class<?> clazz) {
        return registry.remove(clazz) != null;
    }

    public static int unregister(TypeParser<?> parser) {
        int n = 0;
        for (Map.Entry<Class<?>, TypeParser<?>> entry : registry.entrySet()) {
            if (entry.getValue() == parser)
                if (registry.remove(entry.getKey()) != null)
                    n++;
        }
        return n;
    }

    public static class ByteParser implements TypeParser<Byte> {

        @Override
        public Byte parse(String text) throws ParseException {
            try {
                return Byte.valueOf(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class ShortParser implements TypeParser<Short> {

        @Override
        public Short parse(String text) throws ParseException {
            try {
                return Short.valueOf(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class IntegerParser implements TypeParser<Integer> {

        @Override
        public Integer parse(String text) throws ParseException {
            try {
                return Integer.valueOf(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class LongParser implements TypeParser<Long> {

        @Override
        public Long parse(String text) throws ParseException {
            try {
                return Long.valueOf(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class FloatParser implements TypeParser<Float> {

        @Override
        public Float parse(String text) throws ParseException {
            try {
                return Float.valueOf(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class DoubleParser implements TypeParser<Double> {

        @Override
        public Double parse(String text) throws ParseException {
            try {
                return Double.valueOf(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class BooleanParser implements TypeParser<Boolean> {

        @Override
        public Boolean parse(String text) throws ParseException {
            try {
                return Boolean.valueOf(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class CharacterParser implements TypeParser<Character> {

        @Override
        public Character parse(String text) throws ParseException {
            try {
                if (text.isEmpty())
                    return '\0';
                return text.charAt(0);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class CharArrayParser implements TypeParser<char[]> {

        @Override
        public char[] parse(String text) throws ParseException {
            return text.toCharArray();
        }

    }

    public static class ClassParser implements TypeParser<Class<?>> {

        @Override
        public Class<?> parse(String name) throws ParseException {
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class CharsetParser implements TypeParser<Charset> {

        @Override
        public Charset parse(String name) throws ParseException {
            try {
                return Charset.forName(name);
            } catch (UnsupportedCharsetException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class FileParser implements TypeParser<File> {

        @Override
        public File parse(String path) throws ParseException {
            return new File(path);
        }

    }

    public static class ByteOutParser implements TypeParser<IByteOut> {

        @Override
        public IByteOut parse(String path) throws ParseException {
            FileOutputStream out;
            try {
                out = new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            return ByteOuts.get(out);
        }

    }

    public static class CharOutParser implements TypeParser<CharOut> {

        @Override
        public CharOut parse(String path) throws ParseException {
            FileOutputStream out;
            try {
                out = new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            return CharOuts.get(out);
        }

    }

    public static class ALogParser implements TypeParser<ALog> {

        @Override
        public ALog parse(String path) throws ParseException {
            FileOutputStream out;
            try {
                out = new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            return ALogs.get(LogOuts.get(CharOuts.get(out), path));
        }

    }

    public static class PatternParser implements TypeParser<Pattern> {

        private final int flags;

        public PatternParser() {
            this.flags = 0;
        }

        public PatternParser(int flags) {
            this.flags = flags;
        }

        @Override
        public Pattern parse(String regexp) throws ParseException {
            return Pattern.compile(regexp, flags);
        }

    }

    public static class PatternIParser extends PatternParser {

        public PatternIParser() {
            super(Pattern.CASE_INSENSITIVE);
        }

    }

    public static class WildcardsParser implements TypeParser<Pattern> {

        private final int flags;

        public WildcardsParser() {
            this.flags = 0;
        }

        public WildcardsParser(int flags) {
            this.flags = flags;
        }

        private static final Pattern QUOTE;
        static {
            QUOTE = Pattern.compile(//
                    "([.+^$\\[\\](){}\\\\])");
        }

        @Override
        public Pattern parse(String wildcards) throws ParseException {
            String regexp = QUOTE.matcher(wildcards).replaceAll("\\$1");
            regexp = regexp.replace("*", ".*");
            regexp = regexp.replace("?", ".");
            return Pattern.compile(regexp, flags);
        }

    }

    public static class WildcardsIParser extends WildcardsParser {

        public WildcardsIParser() {
            super(Pattern.CASE_INSENSITIVE);
        }

    }

}
