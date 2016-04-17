package net.bodz.bas.i18n.dom1;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.bean.DetailLevel;

/**
 * Semi-mutable is for serializable, however, setters are protected.
 */
public class SemiMutableElement
        extends AbstractElement
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private iString label;
    private iString description;
    private iString helpDoc;
    private int detailLevel = DetailLevel.NORMAL;
    private int modifiers;
    private int priority;

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
    public int getDetailLevel() {
        return detailLevel;
    }

    protected void setDetailLevel(int userLevel) {
        this.detailLevel = userLevel;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }

    protected void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    protected void setPriority(int priority) {
        this.priority = priority;
    }

}
