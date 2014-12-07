package net.bodz.bas.i18n.dom1;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.iString;

public class MutableElement
        extends AbstractElement
        implements IMutableElement, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private iString label;
    private iString description;
    private iString helpDoc;
    private int detailLevel;
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
    public iString getLabel() {
        return label;
    }

    @Override
    public void setLabel(iString label) {
        this.label = label;
    }

    @Override
    public iString getDescription() {
        return description;
    }

    @Override
    public void setDescription(iString description) {
        this.description = description;
    }

    @Override
    public iString getHelpDoc() {
        return helpDoc;
    }

    @Override
    public void setHelpDoc(iString helpDoc) {
        this.helpDoc = helpDoc;
    }

    @Override
    public int getDetailLevel() {
        return detailLevel;
    }

    @Override
    public void setDetailLevel(int detailLevel) {
        this.detailLevel = detailLevel;
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
