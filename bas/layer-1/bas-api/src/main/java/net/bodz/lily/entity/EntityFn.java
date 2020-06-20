package net.bodz.lily.entity;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.meta.source.FnHelper;

@FnHelper
public class EntityFn {

    public static <T extends IId<K>, K> T ref(T skel, K id) {
        skel.setId(id);
        return skel;
    }

    public static <T extends IId<K>, K> T ref(T skel, K id, T nullref) {
        if (id == null)
            return nullref;
        skel.setId(id);
        return skel;
    }

    public static <T extends IId<K>, K> T ref(Class<T> type, K id) {
        if (type == null)
            throw new NullPointerException("type");
        try {
            T instance = type.newInstance();
            instance.setId(id);
            return instance;
        } catch (ReflectiveOperationException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

}
