package net.bodz.bas.ui.dom1;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.ref.VarEntry;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public class UiVar<T>
        extends VarEntry<T>
        implements IUiRef<T> {

    private static final long serialVersionUID = 1L;

    private IUiElementStyleDeclaration style = IUiElementStyleDeclaration.NULL;

    public UiVar(Class<? extends T> valueType, T initialValue) {
        super(valueType, initialValue);
    }

    public UiVar(Class<? extends T> valueType) {
        super(valueType);
    }

    public UiVar(IUiElement element, Class<? extends T> valueType, T initialValue) {
        super(element, valueType, initialValue);
        this.style = element.getStyle();
    }

    public UiVar(T initialValue) {
        super(initialValue);
    }

    @Override
    public IUiElementStyleDeclaration getStyle() {
        return style;
    }

    public static <T> UiVar<T> wrap(T obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        @SuppressWarnings("unchecked")
        Class<T> valueType = (Class<T>) obj.getClass();
        return wrap(valueType, obj);
    }

    public static <T> UiVar<T> wrap(Class<T> valueType, T obj) {
        MutableUiElement element = new MutableUiElement();
        element.setLabel(iString.fn.wrap("noname"));
        return new UiVar<T>(element, valueType, obj);
    }

}
