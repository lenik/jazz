package net.bodz.bas.context.clg;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Locale;

import org.junit.Assert;
import net.bodz.bas.context.StaticContext;
import net.bodz.bas.context.clg.SystemCLG;

import org.junit.Test;

public class SystemCLGTest
        extends Assert {

    @Test
    public void testDefault()
            throws Exception {
        Charset staticCharset = SystemCLG.charset.get(StaticContext.getInstance());
        assertEquals(Charset.defaultCharset(), staticCharset);

        Locale staticLocale = SystemCLG.locale.get(StaticContext.getInstance());
        assertEquals(Locale.getDefault(), staticLocale);

        File cwd = SystemCLG.cwd.get(StaticContext.getInstance());
        assert cwd != null : "null cwd";
        String cwdPath = cwd.getPath();
        String userDir = System.getProperty("user.dir");
        assertEquals(userDir, cwdPath);
    }

}
