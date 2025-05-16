package net.bodz.bas.rtx;

import java.util.Iterator;
import java.util.Map;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;

public class MapOptions
        implements IMutableOptions {

    private final Map<String, IOption> map;

    public MapOptions() {
        this(SortOrder.NONE);
    }

    public MapOptions(SortOrder order) {
        this(order.newMap());
    }

    public MapOptions(Map<String, IOption> map) {
        this.map = map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public IOption getOption(@NotNull String id) {
        return map.get(id);
    }

    @NotNull
    @Override
    public Iterator<IOption> iterator() {
        return map.values().iterator();
    }

    @Override
    public MapOptions addOption(IOption option) {
        map.put(option.getId(), option);
        return this;
    }

    @Override
    public IOption removeOption(@NotNull String id) {
        return map.remove(id);
    }

    @Override
    public String toString() {
        return OptionsFn.dump(this);
    }

}
