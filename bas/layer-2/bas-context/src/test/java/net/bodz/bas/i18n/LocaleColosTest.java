package net.bodz.bas.i18n;

import java.nio.charset.Charset;
import java.util.Locale;

import net.bodz.bas.context.StaticContextId;

import org.junit.Assert;
import org.junit.Test;

public class LocaleColosTest
        extends Assert {

    @Test
    public void testCharset() {
        Charset staticCharset = LocaleColos.charset.get(StaticContextId.getInstance());
        assertEquals(Charset.defaultCharset(), staticCharset);

        Locale staticLocale = LocaleColos.locale.get(StaticContextId.getInstance());
        assertEquals(Locale.getDefault(), staticLocale);
    }

}
