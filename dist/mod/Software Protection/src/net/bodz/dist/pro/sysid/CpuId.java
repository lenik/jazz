package net.bodz.dist.pro.sysid;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.SystemException;
import net.bodz.bas.text.encodings.Encodings;

/**
 * @test {@link CpuIdTest}
 */
public class CpuId extends _SysIdProvider {

    private final int cpuIndex;

    public CpuId(int cpuIndex) {
        super("-"); //$NON-NLS-1$
        this.cpuIndex = cpuIndex;
    }

    @Override
    public byte[] getId() throws SystemException {
        String processorId;
        try {
            WMISystemInfo info = WMISystemInfo.getInstance();
            processorId = info.getString("cpu." + cpuIndex + ".ProcessorId");
            if (processorId == null)
                return null;

            // LONG: BF EB FB FF 00 01 06 7A
            byte[] bytes = Encodings.HEX.decode(processorId);
            return bytes;
        } catch (WMIException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    public static int getCpuCount() throws SystemException {
        try {
            WMISystemInfo info = WMISystemInfo.getInstance();
            int count = info.getInt("cpu.count");
            return count;
        } catch (WMIException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

}
