package net.bodz.bas.module;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.module.ModuleInfo;
import net.bodz.bas.nls_2.NLS;

import org.junit.Test;

public class ModuleInfoTest {

    @Test
    public void testNLS_InTest() {
        NLS nls = ModuleInfo.getInstance().getNLS();
        assertEquals("value1", nls.getString("key1"));
    }

}
