package net.bodz.bas.type.traits.impl;

import java.util.Map;

import net.bodz.bas.exceptions.CreateException;
import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.IParser;

public class ParserResolve {

    /**
     * @return <code>null</code> if no explicitly (or implicitly derived) type registered.
     */
    public static IParser<?> get(Class<?> clazz) {
        return registry.floor(clazz);
    }

    public static void register(Class<?> clazz, IParser<?> parser) {
        registry.put(clazz, parser);
    }

    public static boolean unregister(Class<?> clazz) {
        return registry.remove(clazz) != null;
    }

    public static int unregister(IParser<?> parser) {
        int n = 0;
        for (Map.Entry<Class<?>, IParser<?>> entry : registry.entrySet()) {
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
    public static IParser<?> guess(Class<?> type)
            throws CreateException {
        IParser<?> parser = get(type);
        if (parser != null)
            return parser;

        if (type.isArray())
            return new ArrayParser(type.getComponentType(), ","); 

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

    public static IParser<?> guess(Class<?> type, String errname)
            throws ParseException {
        IParser<?> parser;
        try {
            parser = guess(type);
        } catch (CreateException e) {
            throw new ParseException(e);
        }
        if (parser == null && errname != null) {
            throw new ParseException("can\'t parse " + errname 
                    + ": no suitable parser for " + type); 
        }
        return parser;
    }

    public static IParser<?> guess(Class<?> type, boolean fail)
            throws ParseException {
        return guess(type, fail ? "" : null); 
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(Class<T> type, String s)
            throws ParseException {
        Parser parser = guess(type, true);
        Object val = parser.parse(s);
        // return type.isPrimitive() ? (T) val : type.cast(val);
        return (T) val;
    }

    private static TypeHierMap<IParser<?>> registry;
    static {
        registry = new TypeHierMap<Parser>();
    }

}
