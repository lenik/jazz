package net.bodz.bas.t.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.t.preorder.IPreorderMap;

public interface IListPmap<K, E>
        extends IPreorderMap<K, List<E>> {

    default List<E> meetOrEmpty(K key) {
        List<E> list = meet(key);
        if (list != null)
            return list;
        else
            return Collections.emptyList();
    }

    default Entry<K, List<E>> meetEntryOrEmpty(K key) {
        Entry<K, List<E>> entry = meetEntry(key);
        if (entry == null)
            return Pair.of(key, Collections.emptyList());
        List<E> list = entry.getValue();
        if (list == null)
            return Pair.of(key, Collections.emptyList());
        return entry;
    }


    @NotNull
    default List<E> joinConcatenated(K key) {
        List<E> cat = new ArrayList<>();
        for (List<E> list : join(key)) {
            cat.addAll(list);
        }
        return cat;
    }

    @NotNull
    default Set<E> joinUnion(K key) {
        Set<E> union = new LinkedHashSet<>();
        for (List<E> list : join(key)) {
            union.addAll(list);
        }
        return union;
    }

}
