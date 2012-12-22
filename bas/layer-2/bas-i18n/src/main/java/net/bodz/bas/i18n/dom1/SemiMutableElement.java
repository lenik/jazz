package net.bodz.bas.i18n.dom1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.i18n.dom.DomainString;

public class SemiMutableElement
        extends AbstractElement
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private DomainString displayName;
    private DomainString description;
    private DomainString helpDoc;
    private int userLevel;
    private int modifiers;
    private Set<String> tagNames;

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    @Override
    public DomainString getDisplayName() {
        return displayName;
    }

    protected void setDisplayName(DomainString displayName) {
        this.displayName = displayName;
    }

    @Override
    public DomainString getDescription() {
        return description;
    }

    protected void setDescription(DomainString description) {
        this.description = description;
    }

    @Override
    public DomainString getHelpDoc() {
        return helpDoc;
    }

    protected void setHelpDoc(DomainString helpDoc) {
        this.helpDoc = helpDoc;
    }

    @Override
    public int getUserLevel() {
        return userLevel;
    }

    protected void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }

    protected void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    @Override
    public synchronized Set<String> getTagNames() {
        if (tagNames == null)
            tagNames = new HashSet<String>();
        return tagNames;
    }

    public void addTagName(String tagName) {
        if (tagName == null)
            throw new NullPointerException("tagName");
        getTagNames().add(tagName);
    }

    public void removeTagName(String tagName) {
        if (tagName == null)
            throw new NullPointerException("tagName");
        if (tagNames != null)
            tagNames.remove(tagName);
    }

}
