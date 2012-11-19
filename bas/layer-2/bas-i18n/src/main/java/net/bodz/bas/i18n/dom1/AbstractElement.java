package net.bodz.bas.i18n.dom1;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.i18n.dom.DomainString;

public abstract class AbstractElement
        implements IEditableElement, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private DomainString displayName;
    private DomainString description;
    private DomainString helpDoc;

    private Set<String> tags;

    public AbstractElement(String name) {
        this.name = name;
    }

    protected AbstractElement() {
        this.name = getClass().getSimpleName();
    }

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
    public int getPreferenceLevel() {
        return 0;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public Set<String> getTags() {
        if (tags == null) {
            synchronized (this) {
                if (tags == null) {
                    tags = new TreeSet<String>();
                }
            }
        }
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public void addTag(String tag) {
        if (tag == null)
            throw new NullPointerException("tag");
        tags.add(tag);
    }

    @Override
    public void removeTag(String tag) {
        if (tag == null)
            throw new NullPointerException("tag");
        tags.remove(tag);
    }

}
