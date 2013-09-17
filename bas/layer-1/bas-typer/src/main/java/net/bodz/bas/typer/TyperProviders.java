package net.bodz.bas.typer;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.TreeSet;

import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.typer.spi.ITyperProvider;

public class TyperProviders {

    private static final TreeSet<ITyperProvider> sortedSet;
    private static final List<ITyperProvider> aggresiveList;

    static {
        sortedSet = new TreeSet<ITyperProvider>(PriorityComparator.INSTANCE);
        aggresiveList = new ArrayList<ITyperProvider>();

        reload();
    }

    static void reload() {
        ServiceLoader<ITyperProvider> loader = ServiceLoader.load(ITyperProvider.class);
        loader.reload();

        sortedSet.clear();

        for (ITyperProvider provider : loader)
            sortedSet.add(provider);

        for (ITyperProvider provider : sortedSet)
            if (provider.isAggressive())
                aggresiveList.add(provider);
    }

    public static TreeSet<ITyperProvider> sorted() {
        return sortedSet;
    }

    public static List<ITyperProvider> aggresives() {
        return aggresiveList;
    }

}
