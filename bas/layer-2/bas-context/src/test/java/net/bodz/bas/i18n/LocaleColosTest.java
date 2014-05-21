package net.bodz.bas.i18n;

import java.nio.charset.Charset;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.context.IContext;

public class LocaleColosTest
        extends Assert {

    @Test
    public void testCharset() {
        Charset defaultCharset = II18nCtlConsts.CHARSET.get(IContext.DEFAULT);
        assertEquals(Charset.defaultCharset(), defaultCharset);

        Locale defaultLocale = II18nCtlConsts.LOCALE.get(IContext.DEFAULT);
        assertEquals(Locale.getDefault(), defaultLocale);
    }

}
