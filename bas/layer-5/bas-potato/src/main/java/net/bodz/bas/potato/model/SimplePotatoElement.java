package net.bodz.bas.potato.model;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.i18n.dom1.IEditableElement;

public class SimplePotatoElement
        extends SimpleAnnotatedElement
        implements IPotatoElement, IEditableElement {

    Class<?> declaringClass;
    String name;
    DomainString displayName = new XDomainString();
    DomainString description = new XDomainString();
    DomainString helpDoc = new XDomainString();
    int preferenceLevel;
    int modifiers;
    Set<String> tags = new HashSet<String>();

    @Override
    public Class<?> getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(Class<?> declaringClass) {
        this.declaringClass = declaringClass;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public DomainString getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(DomainString displayName) {
        this.displayName = displayName;
    }

    @Override
    public DomainString getDescription() {
        return description;
    }

    @Override
    public void setDescription(DomainString description) {
        this.description = description;
    }

    @Override
    public DomainString getHelpDoc() {
        return helpDoc;
    }

    @Override
    public void setHelpDoc(DomainString helpDoc) {
        this.helpDoc = helpDoc;
    }

    @Override
    public int getPreferenceLevel() {
        return preferenceLevel;
    }

    public void setPreferenceLevel(int preferenceLevel) {
        this.preferenceLevel = preferenceLevel;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    @Override
    public Set<String> getTags() {
        return tags;
    }

    @Override
    public void addTag(String tag) {
        tags.add(tag);
    }

    @Override
    public void removeTag(String tag) {
        tags.remove(tag);
    }

}
