package net.bodz.bas.http.config;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

public class FilterDescriptor
        extends AbstractPluginDescriptor {

    Class<? extends Filter> filterClass;
    List<String> mappings = new ArrayList<String>();
    Map<String, String> initParamMap = new HashMap<>();

    EnumSet<DispatcherType> dispatcherTypes = EnumSet.noneOf(DispatcherType.class);
    boolean suspendable = false;

    public FilterDescriptor(Class<? extends Filter> filterClass) {
        this(null, filterClass);
    }

    public FilterDescriptor(String id, Class<? extends Filter> filterClass) {
        super(id);
        if (filterClass == null)
            throw new NullPointerException("filterClass");
        this.filterClass = filterClass;
    }

    public Class<? extends Filter> getFilterClass() {
        return filterClass;
    }

    public List<String> getMappings() {
        return mappings;
    }

    public void addMapping(String mapping) {
        if (mapping == null)
            throw new NullPointerException("mapping");
        mappings.add(mapping);
    }

    public Map<String, String> getInitParamMap() {
        return initParamMap;
    }

    public void setInitParam(String key, String value) {
        initParamMap.put(key, value);
    }

    public void removeInitParam(String key) {
        initParamMap.remove(key);
    }

    public EnumSet<DispatcherType> getDispatcherTypes() {
        return dispatcherTypes;
    }

    public void addDispatcherType(DispatcherType dispatcherType) {
        dispatcherTypes.add(dispatcherType);
    }

    public void removeDispatcherType(DispatcherType dispatcherType) {
        dispatcherTypes.remove(dispatcherType);
    }

    public boolean isSuspendable() {
        return suspendable;
    }

    public void setSuspendable(boolean suspendable) {
        this.suspendable = suspendable;
    }

}
