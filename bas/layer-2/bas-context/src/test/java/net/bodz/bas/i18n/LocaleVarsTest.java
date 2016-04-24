package net.bodz.bas.i18n;

import java.nio.charset.Charset;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.scope.id.IScopeDescriptor;

public class LocaleVarsTest
        extends Assert {

    @Test
    public void testCharset() {
        Charset defaultCharset = II18nVarConsts.CHARSET.get(IScopeDescriptor.DEFAULT);
        assertEquals(Charset.defaultCharset(), defaultCharset);

        Locale defaultLocale = II18nVarConsts.LOCALE.get(IScopeDescriptor.DEFAULT);
        assertEquals(Locale.getDefault(), defaultLocale);
    }

}
