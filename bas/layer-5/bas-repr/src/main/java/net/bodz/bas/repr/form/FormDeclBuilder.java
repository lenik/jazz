package net.bodz.bas.repr.form;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class FormDeclBuilder {

    FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();
    SortOrder sortOrder = SortOrder.NONE;

    int modifierMask = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE | Modifier.STATIC;
    int modifierPattern = Modifier.PUBLIC;
    Class<?> stopClass = Object.class;

    int maxDetailLevel = DetailLevel.EXPERT2;
    Set<String> excludes = new HashSet<String>();
    boolean xjdocRequired;

    public FieldDeclBuilder getFormFieldBuilder() {
        return fieldDeclBuilder;
    }

    public void setFormFieldBuilder(FieldDeclBuilder fieldDeclBuilder) {
        if (fieldDeclBuilder == null)
            throw new NullPointerException("fieldDeclBuilder");
        this.fieldDeclBuilder = fieldDeclBuilder;
    }

    public int getModifierMask() {
        return modifierMask;
    }

    public void setModifierMask(int modifierMask) {
        this.modifierMask = modifierMask;
    }

    public int getModifierPattern() {
        return modifierPattern;
    }

    public void setModifierPattern(int modifierPattern) {
        this.modifierPattern = modifierPattern;
    }

    public Class<?> getStopClass() {
        return stopClass;
    }

    public void setStopClass(Class<?> stopClass) {
        this.stopClass = stopClass;
    }

    public int getMaxDetailLevel() {
        return maxDetailLevel;
    }

    public void setMaxDetailLevel(int maxDetailLevel) {
        this.maxDetailLevel = maxDetailLevel;
    }

    public Set<String> getExcludes() {
        return excludes;
    }

    public MutableFormDecl build(IType type)
            throws ParseException {
        MutableFormDecl result = new MutableFormDecl(type, SortOrder.NONE);

        for (IProperty property : type.getProperties()) {
            if (!filter(property))
                continue;

            IFieldDecl fieldDecl = fieldDeclBuilder.build(property);
            result.addFieldDecl(property.getName(), fieldDecl);
        }
        return result;
    }

    protected boolean filter(IProperty property) {
        int modifiers = property.getModifiers();
        if ((modifiers & modifierMask) != modifierPattern)
            return false;

        int detailLevel = property.getDetailLevel();
        if (detailLevel > maxDetailLevel)
            return false;

        if (excludes.contains(property.getName()))
            return false;

        // ignore properties like: Object.class
        if (property.getDeclaringClass().isAssignableFrom(stopClass))
            return false;

        if (xjdocRequired) {
            IElementDoc xjdoc = property.getXjdoc();
            if (xjdoc == null)
                return false;
        }
        return true;
    }

}
