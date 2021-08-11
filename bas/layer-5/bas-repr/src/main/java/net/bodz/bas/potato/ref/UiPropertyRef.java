package net.bodz.bas.potato.ref;

import java.lang.annotation.Annotation;
import java.util.Map;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.potato.element.IAnnotated;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.ui.dom1.IUiElement;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiValue;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class UiPropertyRef<T>
        extends PropertyRefEntry<T>
        implements
            IUiRef<T>,
            IAnnotated {

    private static final long serialVersionUID = 1L;

    public UiPropertyRef(IRefEntry<?> instance, IProperty property) {
        super(instance, property);
    }

    /** ⇱ Implementation Of {@link IUiElement}. */
    /* _____________________________ */static section.iface __GUI__;

    @Override
    public IUiElementStyleDeclaration getStyle() {
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

    public static UiPropertyRefMap map(Object obj) {
        return map(UiValue.wrap(obj));
    }

    public static UiPropertyRefMap map(IRefEntry<?> objRef) {
        return map(objRef, null);
    }

    public static UiPropertyRefMap map(IRefEntry<?> objRef, SortOrder order) {
        return new UiPropertyRefMap(objRef, order);
    }

}
