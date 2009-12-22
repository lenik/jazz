package net.bodz.bas.api;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.api.types.NLS;

import org.junit.Test;

public class ModuleInfoTest {

    @Test
    public void testNLS_InTest() {
        NLS nls = ModuleInfo.getInstance().getNLS();
        assertEquals("value1", nls.getString("key1"));
    }

}
