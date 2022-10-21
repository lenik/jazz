package net.bodz.bas.repr.form;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.i18n.dom1.IMutableElement;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.ui.dom1.MutableUiElement;

/**
 * @see net.bodz.bas.repr.form.meta.OfGroup
 */
public class PropertyCategory
        extends MutableUiElement
        implements
            IPriority {

    private static final long serialVersionUID = 1L;

    Class<?> tagClass;
    IType groupType;
    int priority;

    public PropertyCategory(Class<?> tagClass) {
        this.tagClass = tagClass;
    }

    public Class<?> getTagClass() {
        return tagClass;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static final PropertyCategory NULL = new PropertyCategory(Object.class);
    static {
        NULL.setPriority(-100);
    }

    static Map<Class<?>, PropertyCategory> registry = new HashMap<Class<?>, PropertyCategory>();

    public static PropertyCategory fromTagClass(Class<?> tagClass) {
        PropertyCategory category = registry.get(tagClass);
        if (category == null) {
            category = new PropertyCategory(tagClass);
            IType type = PotatoTypes.getInstance().loadType(tagClass);

            IMutableElement.fn.copy1(type, category);
            category.setName(type.getType().getSimpleName());
            // category.setStyle(type.getStyle());

            Priority aPriority = type.getAnnotation(Priority.class);
            if (aPriority != null)
                category.setPriority(aPriority.value());
            DetailLevel aDetailLevel = type.getAnnotation(DetailLevel.class);
            if (aDetailLevel != null)
                category.setDetailLevel(aDetailLevel.value());

            registry.put(tagClass, category);
        }
        return category;
    }

}
