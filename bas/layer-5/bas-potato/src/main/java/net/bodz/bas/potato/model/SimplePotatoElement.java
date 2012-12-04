package net.bodz.bas.potato.model;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.i18n.dom1.IEditableElement;

public class SimplePotatoElement
        extends SimpleAnnotatedElement
        implements IPotatoElement, IEditableElement {

    private static final long serialVersionUID = 1L;

    Class<?> declaringClass;
    String name;
    DomainString displayName = new XDomainString();
    DomainString description = new XDomainString();
    DomainString helpDoc = new XDomainString();
    int userLevel;
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
    public int getUserLevel() {
        return userLevel;
    }

    @Override
    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }

    @Override
    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    @Override
    public Set<String> getTagNames() {
        return tags;
    }

    @Override
    public void addTagName(String tagName) {
        tags.add(tagName);
    }

    @Override
    public void removeTagName(String tagName) {
        tags.remove(tagName);
    }

}
