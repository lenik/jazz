package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * The FWKCS Contents_Signature System, used in automatically identifying files independent of file
 * name, optionally adds and uses an extra field to support the rapid creation of an enhanced
 * contents_signature:
 * 
 * <pre>
 *               Header ID = 0x4b46
 *               Data Size = 0x0013
 *               Preface   = 'M','D','5'
 *               followed by 16 bytes containing the uncompressed file's
 *               128_bit MD5 hash(1), low byte first.
 * </pre>
 * 
 * When FWKCS revises a .ZIP file central directory to add this extra field for a file, it also
 * replaces the central directory entry for that file's uncompressed file length with a measured
 * value.
 * 
 * FWKCS provides an option to strip this extra field, if present, from a .ZIP file central
 * directory. In adding this extra field, FWKCS preserves .ZIP file Authenticity Verification; if
 * stripping this extra field, FWKCS preserves all versions of AV through PKZIP version 2.04g.
 * 
 * FWKCS, and FWKCS Contents_Signature System, are trademarks of Frederick W. Kantor.
 * 
 * <hr>
 * (1) R. Rivest, RFC1321.TXT, MIT Laboratory for Computer Science and RSA Data Security, Inc.,
 * April 1992. ll.76-77: "The MD5 algorithm is being placed in the public domain for review and
 * possible adoption as a standard."
 */
@ExtraFieldType(id = 0x4b46)
public class XF_FWKCS_MD5
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public final byte[] preface = new byte[3];
    public final byte[] digest = new byte[16];

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        in.readBytes(preface);
        in.readBytes(digest);
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.write(preface);
        out.write(digest);
    }

}
