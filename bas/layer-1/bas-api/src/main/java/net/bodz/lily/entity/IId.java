package net.bodz.lily.entity;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.meta.bean.Internal;

public interface IId<Id> {

    Class<Id> idType();

    Id id();

    void id(Id id);

    @Internal
    default List<Object> getIdComponents() {
        return Arrays.asList(id());
    }

    @Internal
    default String getIdPath() {
        List<Object> components = getIdComponents();
        int n = components.size();
        if (n == 0)
            return null;
        if (n == 1) {
            Object single = components.get(0);
            return Nullables.toString(single);
        }
        StringBuilder sb = new StringBuilder(n * 16);
        for (int i = 0; i < n; i++) {
            if (i != 0)
                sb.append('/');
            sb.append(components.get(i));
        }
        return sb.toString();
    }

}
