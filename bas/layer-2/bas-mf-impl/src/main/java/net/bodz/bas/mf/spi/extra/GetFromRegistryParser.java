package net.bodz.bas.mf.spi.extra;

import java.lang.reflect.Field;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractParser;

public class GetFromRegistryParser
        extends AbstractParser<Object> {

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
            throw new ParseException("Entry " + entry + " isn\'t defined in registry " + registryClass, e);
        } catch (Exception e) {
            throw new ParseException("Failed to load entry " + entry + " in registry " + registryClass);
        }
    }

}
