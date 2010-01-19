package net.bodz.bas.arch.context.sysclg;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Locale;

import net.bodz.bas.arch.context.StaticContext;

import org.junit.Test;

public class SystemCLGTest {

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
