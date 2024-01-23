package net.bodz.lily.site;

import java.util.Map;

public interface INamingGroup<T> {

    Map<String, ? extends T> getNameMap();

}
