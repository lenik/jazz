package net.bodz.bas.repr.form;

import java.util.*;

import net.bodz.bas.t.order.DefaultComparator;
import net.bodz.bas.t.order.DefaultDescendingComparator;
import net.bodz.bas.ui.dom1.MutableUiElement;

public class MutableFormDef
        extends MutableUiElement
        implements IFormDef {

    private static final long serialVersionUID = 1L;

    private Map<String, IFieldDef> map;

    public MutableFormDef(SortOrder sortOrder) {
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
    public IFieldDef getFieldDef(String name) {
        return map.get(name);
    }

    @Override
    public Collection<IFieldDef> getFieldDefs() {
        return map.values();
    }

    @Override
    public Collection<IFieldDef> getFieldDefs(int maxDetailLevel) {
        List<IFieldDef> list = new ArrayList<IFieldDef>(map.size());
        for (IFieldDef a : map.values())
            if (a.getDetailLevel() > maxDetailLevel)
                continue;
            else
                list.add(a);
        return list;
    }

    public void addFieldDef(String name, IFieldDef fieldDef) {
        if (name == null)
            throw new NullPointerException("name");
        if (fieldDef == null)
            throw new NullPointerException("fieldDef");
        map.put(name, fieldDef);
    }

}
