package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * X.509 Certificate ID for Central Directory
 * 
 * This field contains the information about which certificate in the PKCS#7 store was used to sign
 * the central directory structure. When the Central Directory Encryption feature is enabled for a
 * ZIP file, this record will appear in the Archive Extra Data Record, otherwise it will appear in
 * the first central directory record.
 */
@ExtraFieldType(id = 0x0016)
class XF_X509ForCenDir
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** Signature Data */
    public byte[] data;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        data = new byte[size];
        in.readBytes(data);
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.write(data);
    }

}
