package net.bodz.bas.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import net.bodz.bas.cli.CLIError;
import net.bodz.bas.io.ByteOut;
import net.bodz.bas.io.ByteOuts;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.ALogs;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.text.encodings.Encodings;
import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Types;

public class TypeParsers {

    public static class GetFromRegistryParser extends _TypeParser {

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
                throw new ParseException("Entry " + entry
                        + " isn't defined in registry " + registryClass, e);
            } catch (Exception e) {
                throw new ParseException("Failed to load entry " + entry
                        + " in registry " + registryClass);
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

    public static class StringCtorParser extends _TypeParser {

        private final Constructor<?> ctor;

        public StringCtorParser(Constructor<?> constructor) throws CLIError {
            this.ctor = constructor;
        }

        public StringCtorParser(Class<?> type) throws CLIError {
            try {
                ctor = type.getConstructor(String.class);
            } catch (SecurityException e) {
                throw new CLIError(e.getMessage(), e);
            } catch (NoSuchMethodException e) {
                throw new CLIError(e.getMessage(), e);
            }
        }

        @Override
        public Object parse(String text) throws ParseException {
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

    public static class GetInstanceParser extends _TypeParser {

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

    public static class ArrayParser extends _TypeParser {

        private Class<?>   valtype;
        private TypeParser valparser;
        private Pattern    separator;

        public ArrayParser(Class<?> valtype, Pattern separator)
                throws CreateException {
            this.valtype = valtype;
            this.valparser = TypeParsers.guess(valtype);
            this.separator = separator;
        }

        public ArrayParser(Class<?> valtype, String separator)
                throws CreateException {
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
     * @throws OutOfDomainException
     *             if no suitable type parser exists.
     */
    public static TypeParser guess(Class<?> type) throws CreateException {
        TypeParser parser = get(type);
        if (parser != null)
            return parser;
        if (type.isArray())
            return new ArrayParser(type.getComponentType(), ",");

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

        throw new OutOfDomainException("don't know how to parser " + type);
    }

    private static TreeMap<Class<?>, TypeParser> registry;
    static {
        registry = new TreeMap<Class<?>, TypeParser>(Comparators.TYPE_HIER);
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
        registry.put(ALog.class, new ALogParser());
        registry.put(Pattern.class, new PatternParser());
        registry.put(MessageDigest.class, new MessageDigestParser());
    }

    /**
     * @return <code>null</code> if no explicitly (or implicitly derived) type
     *         registered.
     */
    public static TypeParser get(Class<?> clazz) {
        TypeParser registered = registry.get(clazz);
        if (registered != null)
            return registered;
        Class<?> baseClass = clazz;
        Entry<Class<?>, TypeParser> base = registry.floorEntry(baseClass);
        while (base != null) {
            baseClass = base.getKey();
            if (!baseClass.isAssignableFrom(clazz))
                break;
            TypeParser baseParser = base.getValue();
            if (baseParser.variant())
                return baseParser;
            base = registry.lowerEntry(baseClass);
        }
        return null;
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

    public static class ByteParser extends _TypeParser {

        @Override
        public Byte parse(String text) throws ParseException {
            try {
                return AutoType.toByte(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class ShortParser extends _TypeParser {

        @Override
        public Short parse(String text) throws ParseException {
            try {
                return AutoType.toShort(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class IntegerParser extends _TypeParser {

        @Override
        public Integer parse(String text) throws ParseException {
            try {
                return AutoType.toInt(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class LongParser extends _TypeParser {

        @Override
        public Long parse(String text) throws ParseException {
            try {
                return AutoType.toLong(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class FloatParser extends _TypeParser {

        @Override
        public Float parse(String text) throws ParseException {
            try {
                return AutoType.toFloat(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class DoubleParser extends _TypeParser {

        @Override
        public Double parse(String text) throws ParseException {
            try {
                return AutoType.toDouble(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class BooleanParser extends _TypeParser {

        @Override
        public Boolean parse(String text) throws ParseException {
            try {
                return AutoType.toBoolean(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class CharacterParser extends _TypeParser {

        @Override
        public Character parse(String text) throws ParseException {
            try {
                return AutoType.toChar(text);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class CharArrayParser extends _TypeParser {

        @Override
        public char[] parse(String text) throws ParseException {
            return text.toCharArray();
        }

    }

    public static class StringParser extends _TypeParser {

        @Override
        public String parse(String text) throws ParseException {
            return text;
        }

    }

    public static class ClassParser extends _TypeParser {

        @Override
        public Class<?> parse(String name) throws ParseException {
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class CharsetParser extends _TypeParser {

        @Override
        public Charset parse(String name) throws ParseException {
            try {
                return Charset.forName(name);
            } catch (UnsupportedCharsetException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class FileParser extends _TypeParser {

        @Override
        public File parse(String path) throws ParseException {
            return Files.canoniOf(path);
        }

    }

    public static class ByteOutParser extends _TypeParser {

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

    public static class CharOutParser extends _TypeParser {

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

    public static class ALogParser extends _TypeParser {

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

    public static class PatternParser extends _TypeParser {

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

    public static class WildcardsParser extends _TypeParser {

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

    public static class MessageDigestParser extends _TypeParser {

        @Override
        public MessageDigest parse(String name) throws ParseException {
            String provider = null;
            int d = name.indexOf("::");
            if (d != -1) {
                provider = name.substring(0, d);
                name = name.substring(d + 2);
            }
            try {
                if (provider == null)
                    return MessageDigest.getInstance(name);
                else
                    return MessageDigest.getInstance(name, provider);
            } catch (NoSuchAlgorithmException e) {
                throw new ParseException(e.getMessage(), e);
            } catch (NoSuchProviderException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

    }

    public static class HexParser extends _TypeParser {

        @Override
        public byte[] parse(String hex) throws ParseException {
            return Encodings.HEX.decode(hex);
        }

    }

    public static class Base64Parser extends _TypeParser {

        @Override
        public byte[] parse(String hex) throws ParseException {
            return Encodings.BASE64.decode(hex);
        }

    }

}
