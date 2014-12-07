package net.bodz.bas.repr.form;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import net.bodz.bas.t.order.DefaultComparator;
import net.bodz.bas.t.order.DefaultDescendingComparator;
import net.bodz.bas.ui.dom1.MutableUiElement;

public class MutableFormStruct
        extends MutableUiElement
        implements IFormStruct {

    private static final long serialVersionUID = 1L;

    private Map<String, IFormField> map;

    public MutableFormStruct(SortOrder sortOrder) {
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
    public IFormField getField(String name) {
        return map.get(name);
    }

    @Override
    public Collection<IFormField> getFields() {
        return map.values();
    }

    @Override
    public Map<FieldGroup, Collection<IFormField>> getFieldsGrouped() {
        Map<FieldGroup, Collection<IFormField>> fgMap = new TreeMap<>(FieldGroupComparator.INSTANCE);

        for (IFormField field : map.values()) {
            FieldGroup fg = field.getFieldGroup();
            if (fg == null)
                fg = FieldGroup.NULL;

            Collection<IFormField> coll = fgMap.get(fg);
            if (coll == null)
                fgMap.put(fg, coll = new TreeSet<IFormField>(FormFieldComparator.INSTANCE));

            coll.add(field);
        }
        return fgMap;
    }

    public void addField(String name, IFormField dataField) {
        if (name == null)
            throw new NullPointerException("name");
        if (dataField == null)
            throw new NullPointerException("dataField");
        map.put(name, dataField);
    }

}
