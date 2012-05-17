package net.bodz.bas.lang.negotiation;

import java.util.Iterator;
import java.util.Map;

public class MapNegotiation
        extends AbstractNegotiation {

    private Map<String, IParameter> map;

    public MapNegotiation(Map<String, IParameter> map) {
        if (map == null)
            throw new NullPointerException("map");
        this.map = map;
    }

    public Map<String, IParameter> getMap() {
        return map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public IParameter getParameter(String id) {
        return map.get(id);
    }

    @Override
    public Iterator<IParameter> iterator() {
        return map.values().iterator();
    }

    public IParameter addParameter(IParameter param) {
        return map.put(param.getId(), param);
    }

    public void putOption(String id, Object value) {
        Option option = new Option(id, value);
        addParameter(option);
    }

    public <T> void putOption(Class<T> type, T value) {
        Option option = new Option(type, value);
        addParameter(option);
    }

    public void putParameter(String id, Object value) {
        Option option = new Option(id, value);
        addParameter(option);
    }

    public <T> void putParameter(Class<T> type, T value) {
        Parameter parameter = new Parameter(type, value);
        addParameter(parameter);
    }

}
