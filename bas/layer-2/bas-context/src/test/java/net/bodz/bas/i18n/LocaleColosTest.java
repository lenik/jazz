package net.bodz.bas.i18n;

import java.nio.charset.Charset;
import java.util.Locale;

import net.bodz.bas.context.StaticContext;

import org.junit.Assert;
import org.junit.Test;

public class LocaleColosTest
        extends Assert {

    @Test
    public void testCharset() {
        Charset staticCharset = LocaleColos.charset.get(StaticContext.getInstance());
        assertEquals(Charset.defaultCharset(), staticCharset);

        Locale staticLocale = LocaleColos.locale.get(StaticContext.getInstance());
        assertEquals(Locale.getDefault(), staticLocale);
    }

}
