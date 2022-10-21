package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.i18n.dom1.ElementComparator;

public class PropertyGroup
        extends ArrayList<IFormProperty> {

    private static final long serialVersionUID = 1L;

    private IFormDecl formDecl;
    private PropertyCategory category;

    public PropertyGroup(IFormDecl formDecl, PropertyCategory category) {
        if (formDecl == null)
            throw new NullPointerException("formDecl");
        if (category == null)
            throw new NullPointerException("category");
        this.formDecl = formDecl;
        this.category = category;
    }

    public IFormDecl getFormDecl() {
        return formDecl;
    }

    public PropertyCategory getCategory() {
        return category;
    }

    public List<IFormProperty> select(String... names) {
        return select(new HashSet<String>(Arrays.asList(names)), null);
    }

    public List<IFormProperty> select(Set<String> includes, Set<String> excludes) {
        List<IFormProperty> fields = new ArrayList<IFormProperty>();
        for (IFormProperty field : this) {
            String name = field.getName();
            if (excludes != null && excludes.contains(name))
                continue;
            if (includes != null && !includes.contains(name))
                continue;
            fields.add(field);
        }
        return fields;
    }

    public void sort() {
        Collections.sort(this, ElementComparator.LOCALE);
    }

}
