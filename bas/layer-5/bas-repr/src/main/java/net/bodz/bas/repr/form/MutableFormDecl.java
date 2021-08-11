package net.bodz.bas.repr.form;

import java.util.*;

import net.bodz.bas.t.order.DefaultComparator;
import net.bodz.bas.t.order.DefaultDescendingComparator;

public class MutableFormDecl
        extends AbstractFormDecl {

    private static final long serialVersionUID = 1L;

    private Object source;
    private Map<String, IFieldDecl> map;

    public MutableFormDecl(Object source, SortOrder sortOrder) {
        this.source = source;
        switch (sortOrder) {
        case KEEP:
            map = new LinkedHashMap<String, IFieldDecl>();
            break;
        case ASCENDING:
            map = new TreeMap<String, IFieldDecl>(DefaultComparator.getInstance());
        case DESCENDING:
            map = new TreeMap<String, IFieldDecl>(DefaultDescendingComparator.getInstance());
        case NONE:
        default:
            map = new HashMap<String, IFieldDecl>();
            break;
        }
    }

    public Object getDeclaringSource() {
        return source;
    }

    @Override
    public IFieldDecl getFieldDecl(String name) {
        return map.get(name);
    }

    @Override
    public Collection<IFieldDecl> getFieldDecls() {
        return map.values();
    }

    @Override
    public Collection<IFieldDecl> getFieldDecls(IFieldDeclFilter filter) {
        List<IFieldDecl> list = new ArrayList<IFieldDecl>(map.size());
        for (IFieldDecl a : map.values())
            if (filter.accept(a))
                list.add(a);
        return list;
    }

    public void addFieldDecl(String name, IFieldDecl fieldDecl) {
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
