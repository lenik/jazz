package net.bodz.bas.rtx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Options
        extends AbstractOptions {

    private List<IOption> list;

    public Options() {
        list = new ArrayList<IOption>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public IOption getOption(String id) {
        if (id == null)
            throw new NullPointerException("id");

        for (IOption parameter : list) {
            String paramId = parameter.getId();
            if (id.equals(paramId))
                return parameter;
        }
        return null;
    }

    @Override
    public Iterator<IOption> iterator() {
        return list.iterator();
    }

    @Override
    public AbstractOptions addOption(IOption option) {
        list.add(option);
        return this;
    }

}
