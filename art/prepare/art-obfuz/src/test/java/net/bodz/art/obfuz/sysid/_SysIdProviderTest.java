package net.bodz.art.obfuz.sysid;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class _SysIdProviderTest {

    @Test
    public void testFormat()
            throws Exception {
        byte[] id = { 3, 4, (byte) 0x80, (byte) 0xff };
        ConstId prov = new ConstId(id);
        String s = prov.getIdString();
        if (s != null)
            s = s.toUpperCase();
        assertEquals("03-04-80-FF", s);
        byte[] id2 = prov.parseId(s);
        assertArrayEquals(id, id2);
    }

}
