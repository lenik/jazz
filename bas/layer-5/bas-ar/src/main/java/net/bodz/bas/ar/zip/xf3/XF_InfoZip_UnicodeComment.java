package net.bodz.bas.ar.zip.xf3;

import java.io.IOException;

import net.bodz.bas.ar.zip.ExtraField;
import net.bodz.bas.ar.zip.ExtraFieldType;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;

/**
 * Stores the UTF-8 version of the file comment as stored in the central directory header. (Last
 * Revision 20070912)
 * 
 * Currently Version is set to the number 1. If there is a need to change this field, the version
 * will be incremented. Changes may not be backward compatible so this extra field should not be
 * used if the version is not recognized.
 * 
 * The ComCRC32 is the standard zip CRC32 checksum of the File Comment field in the central
 * directory header. This is used to verify that the comment field has not changed since the Unicode
 * Comment extra field was created. This can happen if a utility changes the File Comment field but
 * does not update the UTF-8 Comment extra field. If the CRC check fails, this Unicode Comment extra
 * field should be ignored and the File Comment field in the header should be used instead.
 * 
 * The UnicodeCom field is the UTF-8 version of the File Comment field in the header. As UnicodeCom
 * is defined to be UTF-8, no UTF-8 byte order mark (BOM) is used. The length of this field is
 * determined by subtracting the size of the previous fields from TSize. If both the File Name and
 * Comment fields are UTF-8, the new General Purpose Bit Flag, bit 11 (Language encoding flag
 * (EFS)), can be used to indicate both the header File Name and Comment fields are UTF-8 and, in
 * this case, the Unicode Path and Unicode Comment extra fields are not needed and should not be
 * created. Note that, for backward compatibility, bit 11 should only be used if the native
 * character set of the paths and comments being zipped up are already in UTF-8. It is expected that
 * the same file comment storage method, either general purpose bit 11 or extra fields, be used in
 * both the Local and Central Directory Header for a file.
 */
@ExtraFieldType(id = 0x6375, sizeTotal = true)
public class XF_InfoZip_UnicodeComment
        extends ExtraField {

    private static final long serialVersionUID = 1L;

    public byte version;
    public int crc32;
    public byte[] commentRaw;

    @Override
    protected void _readObject(IDataIn in)
            throws IOException {
        version = in.readByte();
        crc32 = in.readDword();
        commentRaw = new byte[size - 9];
        in.readBytes(commentRaw);
    }

    @Override
    protected void _writeObject(IDataOut out)
            throws IOException {
        out.writeByte(version);
        out.writeDword(crc32);
        out.write(commentRaw);
    }

}
