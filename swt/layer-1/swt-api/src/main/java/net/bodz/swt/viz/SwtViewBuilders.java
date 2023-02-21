package net.bodz.swt.viz;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class SwtViewBuilders {

    static List<ISwtViewBuilderFactory> list = new ArrayList<>();

    static {
        for (ISwtViewBuilderFactory factory : ServiceLoader.load(ISwtViewBuilderFactory.class)) {
            list.add(factory);
        }
    }

    public static ISwtViewBuilderFactory getDefaultFactory() {
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }

}
