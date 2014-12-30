package net.bodz.bas.repr.form;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

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
public class FieldCategory
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

    public static final FieldCategory NULL = new FieldCategory();

    static Map<Class<?>, FieldCategory> registry = new HashMap<Class<?>, FieldCategory>();

    public static FieldCategory fromTagClass(Class<?> clazz) {
        FieldCategory instance = registry.get(clazz);
        if (instance == null) {
            instance = new FieldCategory();
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

    public static Map<FieldCategory, Collection<IFieldDef>> group(Iterable<IFieldDef> fieldDefs) {
        Map<FieldCategory, Collection<IFieldDef>> map;
        map = new TreeMap<>(FieldCategoryComparator.INSTANCE);

        for (IFieldDef fieldDef : fieldDefs) {
            FieldCategory fg = fieldDef.getCategory();
            if (fg == null)
                fg = FieldCategory.NULL;

            Collection<IFieldDef> coll = map.get(fg);
            if (coll == null)
                map.put(fg, coll = new TreeSet<IFieldDef>(FieldDefComparator.INSTANCE));

            coll.add(fieldDef);
        }
        return map;
    }

}
