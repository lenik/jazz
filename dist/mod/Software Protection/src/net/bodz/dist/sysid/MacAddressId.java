package net.bodz.dist.sysid;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import net.bodz.bas.lang.err.SystemException;
import net.bodz.bas.types.util.Strings;

public class MacAddressId extends _SysIdProvider {

    private int nicIndex;

    public MacAddressId(int nicIndex) {
        super("-");
        this.nicIndex = nicIndex;
    }

    @Override
    public byte[] getId() throws SystemException {
        return getMac(nicIndex);
    }

    public static byte[] getMac(int nicIndex) throws SystemException {
        try {
            Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
            int i = 0;
            while (nics.hasMoreElements()) {
                NetworkInterface n = nics.nextElement();
                byte[] mac = n.getHardwareAddress();
                if (mac == null) // ignore nic without mac
                    continue;
                if (i++ == nicIndex) {
                    return mac;
                }
            }
            return null;
        } catch (SocketException e) {
            throw new SystemException(e);
        }
    }

    public static String getMacString(int nicIndex, String separator) throws SystemException {
        byte[] mac = getMac(nicIndex);
        if (mac == null)
            return null;
        String s = Strings.join(separator, mac);
        return s;
    }

}
