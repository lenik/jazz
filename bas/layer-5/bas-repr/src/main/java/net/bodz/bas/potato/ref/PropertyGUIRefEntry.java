package net.bodz.bas.potato.ref;

import java.lang.annotation.Annotation;
import java.util.Map;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.gui.dom1.IGUIElement;
import net.bodz.bas.gui.dom1.IGUIRefEntry;
import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.potato.element.IAnnotated;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class PropertyGUIRefEntry<T>
        extends PropertyRefEntry<T>
        implements IGUIRefEntry<T>, IAnnotated {

    private static final long serialVersionUID = 1L;

    public PropertyGUIRefEntry(T instance, IProperty property) {
        super(instance, property);
    }

    /** ⇱ Implementation Of {@link IGUIElement}. */
    /* _____________________________ */static section.iface __GUI__;

    @Override
    public IGUIElementStyleDeclaration getStyle() {
        IElementDoc xjdoc = getProperty().getXjdoc();
        xjdoc.getTag("style");
        // TODO
        throw new NotImplementedException();
    }

    /** ⇱ Implementation Of {@link IAnnotated}. */
    /* _____________________________ */static section.iface __ANNOTATED__;

    @Override
    public Map<Class<?>, Annotation> getAnnotationMap() {
        return getProperty().getAnnotationMap();
    }

    @Override
    public Map<Class<?>, Annotation> getDeclaredAnnotationMap() {
        return getProperty().getDeclaredAnnotationMap();
    }

    @Override
    public void dumpAnnotations(Map<Class<?>, Annotation> map) {
        getProperty().dumpAnnotations(map);
    }

    public static PropertyGUIRefMap map(Object obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        return map(obj.getClass(), obj, null);
    }

    public static PropertyGUIRefMap map(Class<?> clazz, Object obj, Boolean order) {
        return new PropertyGUIRefMap(clazz, obj, order);
    }

}
