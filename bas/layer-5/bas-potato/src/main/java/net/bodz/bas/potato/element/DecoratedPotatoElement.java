package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.util.Map;

import net.bodz.bas.c.reflect.DecoratedAnnotatedElement;
import net.bodz.bas.i18n.dom.iString;

public class DecoratedPotatoElement
        extends DecoratedAnnotatedElement
        implements IPotatoElement {

    private static final long serialVersionUID = 1L;

    public DecoratedPotatoElement(IPotatoElement _orig) {
        super(_orig);
    }

    @Override
    public IPotatoElement getWrapped() {
        return (IPotatoElement) _orig; // super.getWrapped();
    }

    @Override
    public void findAnnotations(Map<Class<? extends Annotation>, Annotation> map) {
        IPotatoElement wrapped = getWrapped();
        IAnnotatedElement _wrapped = wrapped;
        _wrapped.findAnnotations(map);
    }

    @Override
    public Class<?> getDeclaringClass() {
        return getWrapped().getDeclaringClass();
    }

    @Override
    public String getName() {
        return getWrapped().getName();
    }

    @Override
    public iString getLabel() {
        return getWrapped().getLabel();
    }

    @Override
    public iString getDescription() {
        return getWrapped().getDescription();
    }

    @Override
    public iString getHelpDoc() {
        return getWrapped().getHelpDoc();
    }

    @Override
    public int getVerboseLevel() {
        return getWrapped().getVerboseLevel();
    }

    @Override
    public int getModifiers() {
        return getWrapped().getModifiers();
    }

}
