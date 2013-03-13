package net.bodz.bas.potato.element;

import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.IMutableElement;

public class SimplePotatoElement
        extends SimpleAnnotatedElement
        implements IPotatoElement, IMutableElement {

    private static final long serialVersionUID = 1L;

    Class<?> declaringClass;
    String name;
    iString label = new XiString();
    iString description = new XiString();
    iString helpDoc = new XiString();
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
