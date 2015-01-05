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
        case NONE:
            map = new HashMap<>();
            break;
        case NO_SORT:
            map = new LinkedHashMap<>();
            break;
        case ASCENDING:
            map = new TreeMap<>(DefaultComparator.getInstance());
        case DESCENDING:
            map = new TreeMap<>(DefaultDescendingComparator.getInstance());
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

}
