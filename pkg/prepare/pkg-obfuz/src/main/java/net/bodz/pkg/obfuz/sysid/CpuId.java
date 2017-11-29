package net.bodz.pkg.obfuz.sysid;

import net.bodz.bas.data.codec.builtin.HexCodec;
import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.SystemException;

/**
 * Processor serial numbers were basically only in Pentium III processors. Intel removed it from
 * later models due to the privacy concerns that were raised. As such, unless you're on a PIII AND
 * your BIOS settings let you read the serial number, all you'll get are 0's.
 * 
 * @see http://stackoverflow.com/questions/5045450/how-to-get-cpu-serial
 */
@Deprecated
public class CpuId
        extends AbstractSysIdProvider {

    private final int cpuIndex;

    public CpuId(int cpuIndex) {
        super("-");
        this.cpuIndex = cpuIndex;
    }

    @Override
    public byte[] getId()
            throws SystemException {
        String processorId;
        try {
            WMISystemInfo info = WMISystemInfo.getInstance();
            processorId = info.getString("cpu." + cpuIndex + ".ProcessorId");
            if (processorId == null)
                return null;

            // LONG: BF EB FB FF 00 01 06 7A
            byte[] bytes = HexCodec.getInstance().decode(processorId);
            return bytes;
        } catch (WMIException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (DecodeException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    public static int getCpuCount()
            throws SystemException {
        try {
            WMISystemInfo info = WMISystemInfo.getInstance();
            int count = info.getInt("cpu.count", 0);
            return count;
        } catch (WMIException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

}
