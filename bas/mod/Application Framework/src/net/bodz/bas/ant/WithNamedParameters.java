package net.bodz.bas.ant;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;

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
            throw new IllegalArgumentException("Name of the parameter isn't specified");
        if (map.containsKey(name))
            throw new IllegalArgumentException("Parameter " + name + " is already existed");

        Class<?> type = parameter.type;
        String valueText = parameter.valueText;
        TypeParser typeParser = TypeParsers.get(type);
        Object value = valueText == null ? null : typeParser.parse(valueText);

        map.put(parameter.name, value);
    }

}
