package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.i18n.dom1.ElementComparator;

public class FieldDeclGroup
        extends ArrayList<IFieldDecl> {

    private static final long serialVersionUID = 1L;

    private IFormDecl formDecl;
    private FieldCategory category;

    public FieldDeclGroup(IFormDecl formDecl, FieldCategory category) {
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

    public FieldCategory getCategory() {
        return category;
    }

    public List<IFieldDecl> select(String... names) {
        return select(new HashSet<String>(Arrays.asList(names)), null);
    }

    public List<IFieldDecl> select(Set<String> includes, Set<String> excludes) {
        List<IFieldDecl> fields = new ArrayList<IFieldDecl>();
        for (IFieldDecl field : this) {
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
