package net.bodz.bas.servlet.viz;

import net.bodz.bas.c.type.ITypeMapper;
import net.bodz.bas.c.type.NameConventionTypeMapper;

public class NameConventions {

    public static ITypeMapper foo_bar_htm = new NameConventionTypeMapper(null, "_htm", true);
    public static ITypeMapper foo_htm_htm = new NameConventionTypeMapper(null, 1, "htm.", "_htm", true);
    public static ITypeMapper foo_bar_impl_htm = new NameConventionTypeMapper(null, 0, "impl.", "_htm", true);

}
