package net.bodz.bas.repr.form;

import java.util.*;

import net.bodz.bas.t.order.DefaultComparator;
import net.bodz.bas.t.order.DefaultDescendingComparator;
import net.bodz.bas.ui.dom1.MutableUiElement;

public class MutableFormDecl
        extends MutableUiElement
        implements IFormDecl {

    private static final long serialVersionUID = 1L;

    private Map<String, IFieldDecl> map;

    public MutableFormDecl(SortOrder sortOrder) {
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

    @Override
    public IFieldDecl getFieldDef(String name) {
        return map.get(name);
    }

    @Override
    public Collection<IFieldDecl> getFieldDefs() {
        return map.values();
    }

    @Override
    public Collection<IFieldDecl> getFieldDefs(int maxDetailLevel) {
        List<IFieldDecl> list = new ArrayList<IFieldDecl>(map.size());
        for (IFieldDecl a : map.values())
            if (a.getDetailLevel() > maxDetailLevel)
                continue;
            else
                list.add(a);
        return list;
    }

    public void addFieldDef(String name, IFieldDecl fieldDecl) {
        if (name == null)
            throw new NullPointerException("name");
        if (fieldDecl == null)
            throw new NullPointerException("fieldDecl");
        map.put(name, fieldDecl);
    }

}
