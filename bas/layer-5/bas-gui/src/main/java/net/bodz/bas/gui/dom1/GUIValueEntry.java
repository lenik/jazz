package net.bodz.bas.gui.dom1;

import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.ref.ValueEntry;

public class GUIValueEntry<T>
        extends ValueEntry<T>
        implements IGUIRefEntry<T> {

    private static final long serialVersionUID = 1L;

    private IGUIElementStyleDeclaration style = IGUIElementStyleDeclaration.NULL;

    public GUIValueEntry(Class<? extends T> valueType, T value) {
        super(valueType, value);
    }

    public GUIValueEntry(Class<? extends T> valueType) {
        super(valueType);
    }

    public GUIValueEntry(IGUIElement element, Class<? extends T> valueType, T value) {
        super(element, valueType, value);
        this.style = element.getStyle();
    }

    public GUIValueEntry(T value) {
        super(value);
    }

    @Override
    public IGUIElementStyleDeclaration getStyle() {
        return style;
    }

    public static <T> GUIValueEntry<T> wrap(T obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        Class<T> valueType = (Class<T>) obj.getClass();
        return wrap(valueType, obj);
    }

    public static <T> GUIValueEntry<T> wrap(Class<T> valueType, T obj) {
        MutableGUIElement element = new MutableGUIElement();
        element.setLabel(iString.fn.val("noname"));
        return new GUIValueEntry<T>(element, valueType, obj);
    }

}
