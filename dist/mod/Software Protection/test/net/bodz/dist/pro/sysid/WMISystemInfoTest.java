package net.bodz.dist.pro.sysid;

import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Maps;

import org.junit.Test;

public class WMISystemInfoTest {

    @Test
    public void testWmic() throws Exception {
        String output = WMISystemInfo.wmic("diskdrive", "list", "full");
        System.out.println(output);
    }

    @Test
    public void testInstance() throws Exception {
        WMISystemInfo info = WMISystemInfo.getInstance();
        String dump = Maps.dump(info, Comparators.STD);
        System.out.println(dump);
    }

}
