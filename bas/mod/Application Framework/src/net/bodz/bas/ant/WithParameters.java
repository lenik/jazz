package net.bodz.bas.ant;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;

public class WithParameters {

    private final List<Class<?>> types;
    private final List<Object>   values;

    public WithParameters() {
        this.types = new ArrayList<Class<?>>();
        this.values = new ArrayList<Object>();
    }

    public void addConfiguredParameter(TypedParameter param)
            throws ParseException {
        Class<?> type = param.type;
        if (type == null)
            type = String.class;
        // throw new IllegalArgumentException("parameter type isn't specified");

        String valueText = param.valueText;
        TypeParser typeParser = TypeParsers.get(type);
        Object value = valueText == null ? null : typeParser.parse(valueText);

        types.add(type);
        values.add(value);
    }

    public List<Class<?>> getTypes() {
        return new ArrayList<Class<?>>(types);
    }

    public List<Class<?>> prependTypes(Class<?>... prependTypes) {
        if (prependTypes == null || prependTypes.length == 0)
            return getTypes();
        ArrayList<Class<?>> list = new ArrayList<Class<?>>(prependTypes.length
                + types.size());
        for (Class<?> t : prependTypes)
            list.add(t);
        list.addAll(types);
        return list;
    }

    public List<Object> getValues() {
        return new ArrayList<Object>(values);
    }

    public List<Object> prependValues(Object... prependValues) {
        if (prependValues == null || prependValues.length == 0)
            return getValues();
        ArrayList<Object> list = new ArrayList<Object>(prependValues.length
                + values.size());
        for (Object v : prependValues)
            list.add(v);
        list.addAll(values);
        return list;
    }

}
