package net.bodz.bas.repr.viz.web;

import net.bodz.bas.c.type.IClassFunction;
import net.bodz.bas.c.type.ModifiedClassNameResolver;

public class NameConventions {

    public static IClassFunction foo_bar_htm = new ModifiedClassNameResolver(null, "_htm", true);
    public static IClassFunction foo_htm_htm = new ModifiedClassNameResolver(null, 1, "htm.", "_htm", true);
    public static IClassFunction foo_bar_impl_htm = new ModifiedClassNameResolver(null, 0, "impl.", "_htm", true);

}
