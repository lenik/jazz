package net.bodz.bas.i18n.dom1;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.iString;

public class SemiMutableElement
        extends AbstractElement
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private iString label;
    private iString description;
    private iString helpDoc;
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
    public iString getLabel() {
        return label;
    }

    protected void setLabel(iString label) {
        this.label = label;
    }

    @Override
    public iString getDescription() {
        return description;
    }

    protected void setDescription(iString description) {
        this.description = description;
    }

    @Override
    public iString getHelpDoc() {
        return helpDoc;
    }

    protected void setHelpDoc(iString helpDoc) {
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
