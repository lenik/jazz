package net.bodz.bas.http.config;

import net.bodz.bas.t.order.IPriority;

public interface IPluginDescriptor
        extends IPriority {

    String getId();

    int getIndex();

}
