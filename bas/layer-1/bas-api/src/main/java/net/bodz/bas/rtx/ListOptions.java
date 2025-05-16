package net.bodz.bas.rtx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

public class ListOptions
        implements IMutableOptions {

    private final List<IOption> list;

    public ListOptions() {
        list = new ArrayList<IOption>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public IOption getOption(@NotNull String id) {
        for (IOption parameter : list) {
            String paramId = parameter.getId();
            if (id.equals(paramId))
                return parameter;
        }
        return null;
    }

    @NotNull
    @Override
    public Iterator<IOption> iterator() {
        return list.iterator();
    }

    @Override
    public ListOptions addOption(IOption option) {
        list.add(option);
        return this;
    }

    @Override
    public IOption removeOption(@NotNull String id) {
        Iterator<IOption> iterator = list.iterator();
        while (iterator.hasNext()) {
            IOption option = iterator.next();
            String optionId = option.getId();
            if (id.equals(optionId)) {
                iterator.remove();
                return option;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return OptionsFn.dump(this);
    }

}
