package net.bodz.bas.lang;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.i18n.nls_2.NLS;
import net.bodz.bas.lang.ModuleInfo;

import org.junit.Test;

public class ModuleInfoTest {

    @Test
    public void testNLS_InTest() {
        NLS nls = ModuleInfo.getInstance().getNLS();
        assertEquals("value1", nls.getString("key1"));
    }

}
