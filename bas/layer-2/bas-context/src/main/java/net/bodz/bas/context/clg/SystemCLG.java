package net.bodz.bas.context.clg;

import net.bodz.bas.context.ContextLocalGroup;
import net.bodz.bas.i18n.ContextCharset;
import net.bodz.bas.i18n.ContextLocale;
import net.bodz.bas.io.ContextDirectory;

public class SystemCLG
        extends ContextLocalGroup {

    public static final ContextDirectory cwd = ContextDirectory.getInstance();
    public static final ContextLocale locale = ContextLocale.getInstance();
    public static final ContextCharset charset = ContextCharset.getInstance();

}
