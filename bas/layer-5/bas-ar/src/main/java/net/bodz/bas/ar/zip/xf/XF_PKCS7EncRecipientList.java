package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * PKCS#7 Encryption Recipient Certificate List
 * 
 * This field MAY contain information about each of the certificates used in encryption processing
 * and it can be used to identify who is allowed to decrypt encrypted files. This field should only
 * appear in the archive extra data record. This field is not required and serves only to aid
 * archive modifications by preserving public encryption key data. Individual security requirements
 * may dictate that this data be omitted to deter information exposure.
 * 
 * See the section describing the Strong Encryption Specification for details. Refer to the section
 * in this document entitled "Incorporating PKWARE Proprietary Technology into Your Product" for
 * more information.
 */
@ExtraFieldType(id = 0x0019)
public class XF_PKCS7EncRecipientList
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** Format version number - must 0x0001 at this time */
    public short format;

    /** CStore: PKCS#7 data blob. */
    public byte[] data;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        format = in.readWord();
        data = new byte[size - 2];
        in.readBytes(data);
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeWord(format);
        out.write(data);
    }

}
