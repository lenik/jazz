package net.bodz.bas.potato.model;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.reflect.DecoratedAnnotatedElement;
import net.bodz.bas.i18n.dom.DomainString;

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
        IAnnotatedElement _wrapped = (IAnnotatedElement) wrapped;
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
    public DomainString getDisplayName() {
        return getWrapped().getDisplayName();
    }

    @Override
    public DomainString getDescription() {
        return getWrapped().getDescription();
    }

    @Override
    public DomainString getHelpDoc() {
        return getWrapped().getHelpDoc();
    }

    @Override
    public int getPreferenceLevel() {
        return getWrapped().getPreferenceLevel();
    }

    @Override
    public int getModifiers() {
        return getWrapped().getModifiers();
    }

    @Override
    public Set<String> getTags() {
        return getWrapped().getTags();
    }

}
