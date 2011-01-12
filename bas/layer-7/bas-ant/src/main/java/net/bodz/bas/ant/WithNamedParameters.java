package net.bodz.bas.ant;

import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.util.exception.ParseException;

public class WithNamedParameters {

    private final Map<String, Object> map;

    public WithNamedParameters() {
        this.map = new TreeMap<String, Object>();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void addConfiguredParameter(NamedParameter parameter)
            throws ParseException {
        String name = parameter.name;
        if (name == null)
            throw new IllegalArgumentException("Name of the parameter isn\'t specified");
        if (map.containsKey(name))
            throw new IllegalArgumentException("Parameter %s is already existed" + name + " is already existed");
        Object value = parameter.parseValue();
        map.put(parameter.name, value);
    }

}
