package net.bodz.bas.i18n;

import java.nio.charset.Charset;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.scope.id.IScopeDescriptor;

public class LocaleScrTest
        extends Assert {

    @Test
    public void testCharset() {
        Charset defaultCharset = II18nScrConsts.CHARSET.get(IScopeDescriptor.DEFAULT);
        assertEquals(Charset.defaultCharset(), defaultCharset);

        Locale defaultLocale = II18nScrConsts.LOCALE.get(IScopeDescriptor.DEFAULT);
        assertEquals(Locale.getDefault(), defaultLocale);
    }

}
