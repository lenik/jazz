package net.bodz.bas.ar.zip.xf;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * Strong Encryption Header
 */
@ExtraFieldType(id = 0x0017)
class XF_StrongEncHeader
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    /** Format definition for this record */
    public short format;

    /** Encryption algorithm */
    public short algId;

    /** Bit length of encryption key */
    public short bitLen;

    /** Processing flags */
    public short flags;

    /**
     * Certificate decryption extra field data (refer to the explanation for CertData in the section
     * describing the Certificate Processing Method under the Strong Encryption Specification)
     */
    public byte[] certData;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        format = in.readWord();
        algId = in.readWord();
        bitLen = in.readWord();
        flags = in.readWord();
        certData = new byte[size - 8];
        in.readBytes(certData);
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeWord(format);
        out.writeWord(algId);
        out.writeWord(bitLen);
        out.writeWord(flags);
        out.write(certData);
    }

}
