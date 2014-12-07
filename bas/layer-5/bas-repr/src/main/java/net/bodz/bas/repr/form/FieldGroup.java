package net.bodz.bas.repr.form;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.i18n.dom1.IMutableElement;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.ui.dom1.MutableUiElement;

/**
 * @see OfGroup
 */
public class FieldGroup
        extends MutableUiElement
        implements IPriority {

    private static final long serialVersionUID = 1L;

    IType groupType;
    int priority;

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static final FieldGroup NULL = new FieldGroup();

    static Map<Class<?>, FieldGroup> registry = new HashMap<Class<?>, FieldGroup>();

    public static FieldGroup forClass(Class<?> clazz) {
        FieldGroup instance = registry.get(clazz);
        if (instance == null) {
            instance = new FieldGroup();
            IType type = PotatoTypes.getInstance().forClass(clazz);

            // instance.setName(type.getType().getSimpleName());
            IMutableElement.fn.copy1(type, instance);
            // instance.setStyle(type.getStyle());

            Priority aPriority = type.getAnnotation(Priority.class);
            if (aPriority != null)
                instance.setPriority(aPriority.value());
            DetailLevel aDetailLevel = type.getAnnotation(DetailLevel.class);
            if (aDetailLevel != null)
                instance.setDetailLevel(aDetailLevel.value());

            registry.put(clazz, instance);
        }
        return instance;
    }

}
