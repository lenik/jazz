package net.bodz.bas.commons.typeparser;

import java.lang.reflect.Field;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.nls.TypesNLS;

public class GetFromRegistryParser extends Parser {

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
