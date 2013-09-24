package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * PKCS#7 Store for X.509 Certificates
 * 
 * This field MUST contain information about each of the certificates files may be signed with. When
 * the Central Directory Encryption feature is enabled for a ZIP file, this record will appear in
 * the Archive Extra Data Record, otherwise it will appear in the first central directory record and
 * will be ignored in any other record.
 */
@ExtraFieldType(id = 0x0014)
public class XF_PKCS7Store
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** Data about the store */
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
