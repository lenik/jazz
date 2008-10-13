package net.bodz.swt.gui;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.log.LogOut;
import net.bodz.bas.log.LogOuts;

public class GUIClass implements GUIItems {

    private static LogOut        out          = LogOuts.debug;

    public static final int      FIELDS       = 1;
    public static final int      PROPERTIES   = 2;
    public static final int      METHODS      = 4;

    /** include those private/protected members */
    public static final int      ALLDECLARED  = 32;

    /** setAccessible on the members */
    public static final int      FORCE_ACCESS = 64;

    /** Properties with same name will overwrite the fields */
    public static final int      NODUP        = 0x100;

    private final GUIClass       prev;
    private GUIHint              hint;

    private List<GUIItem>        items;
    private Map<String, GUIItem> names;

    public GUIClass(GUIClass prev, Class<?> clazz, int flags)
            throws GUIAccessException {
        this.prev = prev;
        this.hint = new GUIHint(prev == null ? null : prev.hint, //
                clazz);
        boolean nodup = (flags & NODUP) != 0;
        if (nodup)
            names = new HashMap<String, GUIItem>();
        else
            this.items = new ArrayList<GUIItem>();

        boolean all = (flags & ALLDECLARED) != 0;
        boolean force = (flags & FORCE_ACCESS) != 0;
        if ((flags & FIELDS) != 0)
            for (Field field : //
            all ? clazz.getDeclaredFields() : clazz.getFields()) {
                if (force)
                    field.setAccessible(true);
                add(field);
            }
        if ((flags & METHODS) != 0)
            for (Method method : //
            all ? clazz.getMethods() : clazz.getMethods()) {
                if (force)
                    method.setAccessible(true);
                add(method);
            }
        if ((flags & PROPERTIES) != 0)
            addProperties(clazz);
    }

    void add(String name, GUIItem item) {
        if (names != null) {
            if (names.containsKey(name))
                out.P("item ", name, " is overwritten: ", item);
            names.put(name, item);
        } else
            items.add(item);
    }

    // void add(Member member, GUIItem item) {
    // if (members.containsKey(member))
    // throw new IllegalArgumentException("member " + member
    // + " is already existed");
    // }

    public void add(Field field) throws GUIAccessException {
        String name = field.getName();
        GUIField guifield = new GUIField(field);
        add(name, guifield);
    }

    public void add(Method method) throws GUIAccessException {
        String name = method.getName();
        GUIMethod guimethod = new GUIMethod(method);
        add(name, guimethod);
    }

    void addProperties(Class<?> clazz) throws GUIAccessException {
        BeanInfo bean;
        try {
            bean = Introspector.getBeanInfo(clazz, Object.class);
        } catch (IntrospectionException e) {
            throw new GUIAccessException(e);
        }
        for (PropertyDescriptor property : bean.getPropertyDescriptors()) {
            add(property);
        }
    }

    public void add(PropertyDescriptor property) {
        String name = property.getName();
        GUIProperty guiproperty = new GUIProperty(property);
        add(name, guiproperty);
    }

    protected void getItems(List<GUIItem> list) {
        if (items != null)
            list.addAll(items);
        else
            list.addAll(names.values());
        if (prev != null)
            prev.getItems(list);
    }

    public List<GUIItem> getItems() {
        List<GUIItem> list = new ArrayList<GUIItem>( //
                items != null ? items.size() : names.size());
        getItems(list);
        Collections.sort(list);
        return list;
    }

}
