package net.bodz.dist.pro.sysid;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.SystemException;
import net.bodz.bas.text.encodings.Encodings;

/**
 * 
 */
public class DiskId extends _SysIdProvider {

    private final int diskIndex;

    public DiskId(int diskIndex) {
        super("-"); //$NON-NLS-1$
        this.diskIndex = diskIndex;
    }

    @Override
    public byte[] getId() throws SystemException {
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
            byte[] bytes = Encodings.HEX.decode(hex);
            return bytes;
        } catch (WMIException e) {
            throw new SystemException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

    public static int getDiskCount() throws SystemException {
        try {
            WMISystemInfo info = WMISystemInfo.getInstance();
            int count = info.getInt("disk.count");
            return count;
        } catch (WMIException e) {
            throw new SystemException(e.getMessage(), e);
        }
    }

}
