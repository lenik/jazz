package net.bodz.bas.servlet.config;

import net.bodz.bas.t.order.IPriority;

public interface IPluginDescriptor
        extends IPriority {

    String getId();

    int getIndex();

}
