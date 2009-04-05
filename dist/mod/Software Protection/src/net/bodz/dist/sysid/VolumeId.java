package net.bodz.dist.sysid;

import static net.bodz.jna.win32.Win32.kernel32;
import net.bodz.bas.lang.err.SystemException;

import com.sun.jna.ptr.IntByReference;

public class VolumeId extends _SysIdProvider {

    private final String path;

    public VolumeId(String path) {
        super("-");
        this.path = path;
    }

    @Override
    public byte[] getId() throws SystemException {
        IntByReference pSerial = new IntByReference();
        kernel32.GetVolumeInformationA(path, null, 0, pSerial, null, null,
                null, 0);
        int serial = pSerial.getValue();
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (serial >> 24);
        bytes[1] = (byte) (serial >> 16);
        bytes[2] = (byte) (serial >> 8);
        bytes[3] = (byte) (serial);
        return bytes;
    }

}
