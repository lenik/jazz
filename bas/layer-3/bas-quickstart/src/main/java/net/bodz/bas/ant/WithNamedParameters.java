package net.bodz.bas.ant;


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
            throw new IllegalArgumentException("Name of the parameter isn\'t specified"); 
        if (map.containsKey(name))
            throw new IllegalArgumentException(
                    "Parameter %s is already existed" + name + " is already existed");  
        Object value = parameter.parseValue();
        map.put(parameter.name, value);
    }

}
