package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MutableFormDecl
        extends AbstractFormDecl {

    private static final long serialVersionUID = 1L;

    private Object source;
    private Map<String, IFormProperty> map;

    public MutableFormDecl(Object source, SortOrder sortOrder) {
        this.source = source;
        this.map = sortOrder.newMap();
    }

    public Object getDeclaringSource() {
        return source;
    }

    @Override
    public IFormProperty getProperty(String name) {
        return map.get(name);
    }

    @Override
    public Collection<IFormProperty> getProperties() {
        return map.values();
    }

    @Override
    public Collection<IFormProperty> getProperties(PropertyFilter filter) {
        List<IFormProperty> list = new ArrayList<IFormProperty>(map.size());
        for (IFormProperty a : map.values())
            if (filter.accept(a))
                list.add(a);
        return list;
    }

    public void addFieldDecl(String name, IFormProperty fieldDecl) {
        if (name == null)
            throw new NullPointerException("name");
        if (fieldDecl == null)
            throw new NullPointerException("fieldDecl");
        map.put(name, fieldDecl);
    }

    @Override
    public String toString() {
        return String.valueOf(source);
    }

}
