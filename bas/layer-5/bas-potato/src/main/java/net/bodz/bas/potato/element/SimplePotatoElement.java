package net.bodz.bas.potato.element;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.i18n.dom1.IMutableElement;

public class SimplePotatoElement
        extends SimpleAnnotatedElement
        implements IPotatoElement, IMutableElement {

    private static final long serialVersionUID = 1L;

    Class<?> declaringClass;
    String name;
    DomainString label = new XDomainString();
    DomainString description = new XDomainString();
    DomainString helpDoc = new XDomainString();
    int verboseLevel = PUBLIC_LEVEL;
    int modifiers;

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
    public void setVerboseLevel(int userLevel) {
        this.verboseLevel = userLevel;
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
