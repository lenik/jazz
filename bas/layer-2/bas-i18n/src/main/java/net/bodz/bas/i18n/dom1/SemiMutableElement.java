package net.bodz.bas.i18n.dom1;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.DomainString;

public class SemiMutableElement
        extends AbstractElement
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private DomainString displayName;
    private DomainString description;
    private DomainString helpDoc;
    private int verboseLevel = PUBLIC_LEVEL;
    private int modifiers;

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
    public int getVerboseLevel() {
        return verboseLevel;
    }

    protected void setVerboseLevel(int userLevel) {
        this.verboseLevel = userLevel;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }

    protected void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

}
