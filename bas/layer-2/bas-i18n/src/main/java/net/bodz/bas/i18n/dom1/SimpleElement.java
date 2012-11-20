package net.bodz.bas.i18n.dom1;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.i18n.dom.DomainString;

public class SimpleElement
        extends AbstractElement
        implements IEditableElement {

    private static final long serialVersionUID = 1L;

    String name;
    DomainString displayName;
    DomainString description;
    DomainString helpDoc;
    int userLevel;
    int modifiers;
    Set<String> tags;

    @Override
    public String getName() {
        return name;
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
    public synchronized Set<String> getTags() {
        if (tags == null)
            tags = new HashSet<String>();
        return tags;
    }

    @Override
    public void addTag(String tag) {
        if (tag == null)
            throw new NullPointerException("tag");
        getTags().add(tag);
    }

    @Override
    public void removeTag(String tag) {
        if (tag == null)
            throw new NullPointerException("tag");
        if (tags != null)
            tags.remove(tag);
    }

}
