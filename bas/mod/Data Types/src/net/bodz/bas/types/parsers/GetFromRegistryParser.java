package net.bodz.bas.types.parsers;

import java.lang.reflect.Field;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class GetFromRegistryParser implements TypeParser {

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
