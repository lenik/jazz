package net.bodz.art.obfuz.sysid;

import net.bodz.art.obfuz.sysid.CpuId;

import org.junit.Test;

public class CpuIdTest {

    @Test
    public void test() throws Exception {
        int cpuCount = CpuId.getCpuCount();
        for (int i = 0; i < cpuCount; i++) {
            CpuId cpuId = new CpuId(i);
            String idString = cpuId.getIdString();
            System.out.println("CPUID." + i + " = " + idString);
        }
    }

}
