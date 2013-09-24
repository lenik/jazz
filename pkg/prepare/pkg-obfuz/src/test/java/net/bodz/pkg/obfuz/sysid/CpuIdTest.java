package net.bodz.pkg.obfuz.sysid;

import org.junit.Assert;
import org.junit.Test;

@Deprecated
public class CpuIdTest
        extends Assert {

    @Test
    public void test()
            throws Exception {
        int cpuCount = CpuId.getCpuCount();
        for (int i = 0; i < cpuCount; i++) {
            CpuId cpuId = new CpuId(i);
            String idString = cpuId.getIdString();
            System.out.println("CPUID." + i + " = " + idString);
        }
    }

}
