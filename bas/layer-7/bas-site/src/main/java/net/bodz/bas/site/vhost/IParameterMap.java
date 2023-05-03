package net.bodz.bas.site.vhost;

import java.util.Set;

public interface IParameterMap<val_t> {

    boolean hasParameter();

    Set<String> parameterNames();

    boolean containsParameter(String name);

    val_t getParameter(String name);

    default val_t getParameter(String name, val_t defaultValue) {
        val_t val = getParameter(name);
        if (val != null)
            return val;
        if (containsParameter(name))
            return null;
        return defaultValue;
    }

    val_t putParameter(String name, val_t val);

    default boolean addParameter(String name, val_t val) {
        if (containsParameter(name))
            return false;
        putParameter(name, val);
        return true;
    }

    default val_t getOrAddParameter(String name, val_t initial) {
        if (addParameter(name, initial))
            return initial;
        else
            return getParameter(name);
    }

    val_t removeParameter(String name);

    void removeAllParameters();

}
