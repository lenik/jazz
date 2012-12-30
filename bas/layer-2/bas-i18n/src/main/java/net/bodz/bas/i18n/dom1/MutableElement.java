package net.bodz.bas.i18n.dom1;

import net.bodz.bas.i18n.dom.DomainString;

public class MutableElement
        extends AbstractElement
        implements IMutableElement {

    private static final long serialVersionUID = 1L;

    private String name;
    private DomainString label;
    private DomainString description;
    private DomainString helpDoc;
    private int verboseLevel;
    private int modifiers;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public DomainString getLabel() {
        return label;
    }

    @Override
    public void setLabel(DomainString label) {
        this.label = label;
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
    public int getVerboseLevel() {
        return verboseLevel;
    }

    @Override
    public void setVerboseLevel(int verboseLevel) {
        this.verboseLevel = verboseLevel;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }

    @Override
    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

}
