package net.bodz.bas.commons.modules;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.commons.modules.ModuleInfo;
import net.bodz.bas.commons.nls_2.NLS;

import org.junit.Test;

public class ModuleInfoTest {

    @Test
    public void testNLS_InTest() {
        NLS nls = ModuleInfo.getInstance().getNLS();
        assertEquals("value1", nls.getString("key1"));
    }

}
