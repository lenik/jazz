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

    public static final int F_PASSWORD = 1;
    public static final int F_CERTIFICATE = 2;

    public static final int ALG_DES = 0x6601;
    public static final int ALG_RC2 = 0x6602;
    public static final int ALG_3DES_168 = 0x6603;
    public static final int ALG_3DES_112 = 0x6609;
    public static final int ALG_AES_128 = 0x660e;
    public static final int ALG_AES_192 = 0x660f;
    public static final int ALG_AES_256 = 0x6610;
    public static final int ALG_RC2_C = 0x6702;
    public static final int ALG_BLOWFISH = 0x6720;
    public static final int ALG_TWOFISH = 0x6721;
    public static final int ALG_RC4 = 0x6801;
    public static final int ALG_UNKNOWN = 0xffff;

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
