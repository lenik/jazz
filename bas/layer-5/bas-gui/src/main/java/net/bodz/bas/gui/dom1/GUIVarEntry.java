package net.bodz.bas.gui.dom1;

import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.ref.VarEntry;

public class GUIVarEntry<T>
        extends VarEntry<T>
        implements IGUIRefEntry<T> {

    private static final long serialVersionUID = 1L;

    private IGUIElementStyleDeclaration style = IGUIElementStyleDeclaration.NULL;

    public GUIVarEntry(Class<? extends T> valueType, T initialValue) {
        super(valueType, initialValue);
    }

    public GUIVarEntry(Class<? extends T> valueType) {
        super(valueType);
    }

    public GUIVarEntry(IGUIElement element, Class<? extends T> valueType, T initialValue) {
        super(element, valueType, initialValue);
        this.style = element.getStyle();
    }

    public GUIVarEntry(T initialValue) {
        super(initialValue);
    }

    @Override
    public IGUIElementStyleDeclaration getStyle() {
        return style;
    }

    public static <T> GUIVarEntry<T> wrap(T obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        Class<T> valueType = (Class<T>) obj.getClass();
        return wrap(valueType, obj);
    }

    public static <T> GUIVarEntry<T> wrap(Class<T> valueType, T obj) {
        MutableGUIElement element = new MutableGUIElement();
        element.setLabel(iString.fn.val("noname"));
        return new GUIVarEntry<T>(element, valueType, obj);
    }

}
