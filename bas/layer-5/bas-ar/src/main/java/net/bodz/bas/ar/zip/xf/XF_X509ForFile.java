package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * X.509 Certificate ID and Signature for individual file
 * 
 * This field contains the information about which certificate in the PKCS#7 store was used to sign
 * a particular file. It also contains the signature data. This field can appear multiple times, but
 * can only appear once per certificate.
 */
@ExtraFieldType(id = 0x0015)
public class XF_X509ForFile
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
