package net.bodz.bas.ui.dom1;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.ref.ValueEntry;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public class UiValue<T>
        extends ValueEntry<T>
        implements IUiRef<T> {

    private static final long serialVersionUID = 1L;

    private IUiElementStyleDeclaration style = IUiElementStyleDeclaration.NULL;

    public UiValue(Class<? extends T> valueType, T value) {
        super(valueType, value);
    }

    public UiValue(Class<? extends T> valueType) {
        super(valueType);
    }

    public UiValue(IUiElement element, Class<? extends T> valueType, T value) {
        super(element, valueType, value);
        this.style = element.getStyle();
    }

    public UiValue(T value) {
        super(value);
    }

    @Override
    public IUiElementStyleDeclaration getStyle() {
        return style;
    }

    public static <T> UiValue<T> wrap(T obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        Class<T> valueType = (Class<T>) obj.getClass();
        return wrap(valueType, obj);
    }

    public static <T> UiValue<T> wrap(Class<T> valueType, T obj) {
        MutableUiElement element = new MutableUiElement();
        element.setLabel(iString.fn.val("noname"));
        return new UiValue<T>(element, valueType, obj);
    }

}
