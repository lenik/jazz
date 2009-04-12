package net.bodz.bas.types.parsers;

import java.lang.reflect.Field;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.nls.TypesNLS;
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
            throw new ParseException(TypesNLS.getString("GetFromRegistryParser.0") + entry //$NON-NLS-1$
                    + TypesNLS.getString("GetFromRegistryParser.1") + registryClass, e); //$NON-NLS-1$
        } catch (Exception e) {
            throw new ParseException(TypesNLS.getString("GetFromRegistryParser.2") + entry //$NON-NLS-1$
                    + TypesNLS.getString("GetFromRegistryParser.3") + registryClass); //$NON-NLS-1$
        }
    }

}
