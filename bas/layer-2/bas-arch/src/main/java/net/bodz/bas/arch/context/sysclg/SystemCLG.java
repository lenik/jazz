package net.bodz.bas.arch.context.sysclg;

import net.bodz.bas.arch.context.ContextLocalGroup;

/**
 * @test {@link SystemCLGTest}
 */
public class SystemCLG
        extends ContextLocalGroup {

    public static final ContextCWD cwd = ContextCWD.getInstance();
    public static final ContextLocale locale = ContextLocale.getInstance();
    public static final ContextCharset charset = ContextCharset.getInstance();

}
