package net.bodz.art.obfuz.sysid;

import net.bodz.bas.err.SystemException;

import com.sun.jna.ptr.IntByReference;

public class VolumeId
        extends AbstractSysIdProvider {

    private final String path;

    public VolumeId(String path) {
        super("-");
        this.path = path;
    }

    @Override
    public byte[] getId()
            throws SystemException {
        IntByReference pSerial = new IntByReference();
        kernel32.GetVolumeInformationA(path, null, 0, pSerial, null, null, null, 0);
        int serial = pSerial.getValue();
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (serial >> 24);
        bytes[1] = (byte) (serial >> 16);
        bytes[2] = (byte) (serial >> 8);
        bytes[3] = (byte) (serial);
        return bytes;
    }

}
