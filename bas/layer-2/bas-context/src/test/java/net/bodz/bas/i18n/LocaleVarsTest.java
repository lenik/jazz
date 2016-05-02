package net.bodz.bas.i18n;

import java.nio.charset.Charset;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.scope.IScopeInstance;

public class LocaleVarsTest
        extends Assert {

    @Test
    public void testCharset() {
        Charset defaultCharset = II18nVarConsts.CHARSET.get(IScopeInstance.STATIC);
        assertEquals(Charset.defaultCharset(), defaultCharset);

        Locale defaultLocale = II18nVarConsts.LOCALE.get(IScopeInstance.STATIC);
        assertEquals(Locale.getDefault(), defaultLocale);
    }

}
