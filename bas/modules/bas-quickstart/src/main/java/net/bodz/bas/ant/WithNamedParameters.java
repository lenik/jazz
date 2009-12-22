package net.bodz.bas.ant;

import net.bodz.bas.commons.collection.TextMap;
import net.bodz.bas.commons.exceptions.ParseException;
import net.bodz.bas.commons.typealiases.TreeTextMap;
import net.bodz.bas.nls.AppNLS;

public class WithNamedParameters {

    private final TextMap<Object> map;

    public WithNamedParameters() {
        this.map = new TreeTextMap<Object>();
    }

    public TextMap<Object> getMap() {
        return map;
    }

    public void addConfiguredParameter(NamedParameter parameter) throws ParseException {
        String name = parameter.name;
        if (name == null)
            throw new IllegalArgumentException(AppNLS.getString("WithNamedParameters.noParameterName")); //$NON-NLS-1$
        if (map.containsKey(name))
            throw new IllegalArgumentException(
                    AppNLS.getString("WithNamedParameters.parameterExisted") + name + " is already existed"); //$NON-NLS-1$ //$NON-NLS-2$
        Object value = parameter.parseValue();
        map.put(parameter.name, value);
    }

}
