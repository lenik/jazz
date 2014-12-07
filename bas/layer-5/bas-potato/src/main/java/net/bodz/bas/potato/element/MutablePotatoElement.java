package net.bodz.bas.potato.element;

import java.util.Map;

import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.i18n.dom1.IMutableElement;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;

public class MutablePotatoElement
        extends MutableAnnotations
        implements IPotatoElement, IMutableElement {

    private Class<?> declaringClass;
    private Map<Class<?>, Object> typerMap;

    private String name;
    private int detailLevel = DetailLevel.NORMAL;
    private int modifiers;

    private IElementDoc xjdoc;
    private iString labelOverride = new XiString();
    private iString descriptionOverride = new XiString();
    private iString helpDocOverride = new XiString();

    /** ⇱ Implementation Of {@link IPotatoElement}. */
    /* _____________________________ */static section.iface __POTATO__;

    @Override
    public Class<?> getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(Class<?> declaringClass) {
        this.declaringClass = declaringClass;
    }

    @Override
    public <T> T getTyper(Class<T> typerClass) {
        T typer = (T) typerMap.get(typerClass);
        return typer;
    }

    public <T> boolean addTyper(Class<T> typerClass, T typer) {
        if (typerClass == null)
            throw new NullPointerException("typerClass");
        if (typer == null)
            throw new NullPointerException("typer");

        if (typerMap.containsKey(typerClass))
            return false;

        typerMap.put(typerClass, typer);
        return true;
    }

    public boolean removeTyper(Class<?> typerClass) {
        Object removed = typerMap.remove(typerClass);
        return removed != null;
    }

    /** ⇱ Implementation Of {@link IElement}. */
    /* _____________________________ */static section.iface __ELEMENT__;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public iString getLabel() {
        if (labelOverride != null)
            return labelOverride;
        if (xjdoc != null)
            return xjdoc.getTextTag(IElementDoc.LABEL);
        return null;
    }

    @Override
    public iString getDescription() {
        if (descriptionOverride != null)
            return descriptionOverride;
        if (xjdoc != null)
            return xjdoc.getTextTag(IElementDoc.DESCRIPTION);
        return null;
    }

    @Override
    public iString getHelpDoc() {
        if (helpDocOverride != null)
            return helpDocOverride;
        if (xjdoc != null)
            return xjdoc.getText();
        return null;
    }

    @Override
    public int getDetailLevel() {
        return detailLevel;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }

    /** ⇱ Implementation Of {@link IMutableElement}. */
    /* _____________________________ */static section.iface __MUTABLE__;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setLabel(iString label) {
        this.labelOverride = label;
    }

    @Override
    public void setDescription(iString description) {
        this.descriptionOverride = description;
    }

    @Override
    public void setHelpDoc(iString helpDoc) {
        this.helpDocOverride = helpDoc;
    }

    @Override
    public void setDetailLevel(int detailLevel) {
        this.detailLevel = detailLevel;
    }

    @Override
    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    /** ⇱ Implementation Of {@link IXjdocElement}. */
    /* _____________________________ */static section.iface __XJDOC__;

    @Override
    public IElementDoc getXjdoc() {
        return xjdoc;
    }

    public void setXjdoc(IElementDoc xjdoc) {
        this.xjdoc = xjdoc;
    }

}
