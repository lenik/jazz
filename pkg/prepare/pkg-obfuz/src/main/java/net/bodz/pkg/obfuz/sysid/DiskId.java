package net.bodz.pkg.obfuz.sysid;

import net.bodz.bas.data.codec.builtin.HexCodec;
import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.SystemException;

/**
 * 
 */
public class DiskId
        extends AbstractSysIdProvider {

    private final int diskIndex;

    public DiskId(int diskIndex) {
        super("-");
        this.diskIndex = diskIndex;
    }

    @Override
    public byte[] getId()
            throws SystemException {
        String diskId;
        try {
            WMISystemInfo info = WMISystemInfo.getInstance();
            // disk serial property??
            diskId = info.getString("disk." + diskIndex + ".Signature");
            if (diskId == null)
                return null;

            // INT: -1556511043
            int n = Integer.parseInt(diskId);
            String hex = Integer.toHexString(n);
            byte[] bytes = HexCodec.getInstance().decode(hex);
            return bytes;
        } catch (WMIException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (DecodeException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    public static int getDiskCount()
            throws SystemException {
        try {
            WMISystemInfo info = WMISystemInfo.getInstance();
            int count = info.getInt("disk.count", 0);
            return count;
        } catch (WMIException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

}
