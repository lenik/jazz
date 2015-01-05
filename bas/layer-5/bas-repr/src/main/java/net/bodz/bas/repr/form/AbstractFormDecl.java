package net.bodz.bas.repr.form;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.ui.dom1.MutableUiElement;

public abstract class AbstractFormDecl
        extends MutableUiElement
        implements IFormDecl {

    private static final long serialVersionUID = 1L;

    @Override
    public Collection<IFieldDecl> getFieldDecls() {
        return getFieldDecls(IFieldDeclFilter.TRUE);
    }

    @Override
    public Collection<FieldDeclGroup> getFieldGroups() {
        return getFieldGroups(IFieldDeclFilter.TRUE);
    }

    @Override
    public Collection<FieldDeclGroup> getFieldGroups(IFieldDeclFilter filter) {
        Map<FieldCategory, FieldDeclGroup> map;
        map = new TreeMap<>(FieldCategoryComparator.INSTANCE);

        for (IFieldDecl fieldDecl : getFieldDecls(filter)) {
            FieldCategory category = fieldDecl.getCategory();
            if (category == null)
                category = FieldCategory.NULL;

            FieldDeclGroup group = map.get(category);
            if (group == null)
                map.put(category, group = new FieldDeclGroup(this, category));

            group.add(fieldDecl);
        }
        return map.values();
    }

}
