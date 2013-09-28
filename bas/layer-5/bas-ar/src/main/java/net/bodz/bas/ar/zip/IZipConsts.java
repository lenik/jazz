package net.bodz.bas.ar.zip;

import net.bodz.bas.sugar.IConstants;

/**
 * @version 6.3.3
 * @see http://www.pkware.com/documents/casestudies/APPNOTE.TXT
 * @see java.util.zip.ZipConstants
 */
public interface IZipConsts
        extends IConstants {

    /**
     * Version Made By
     * 
     * 4.4.2.1 The upper byte indicates the compatibility of the file attribute information. If the
     * external file attributes are compatible with MS-DOS and can be read by PKZIP for DOS version
     * 2.04g then this value will be zero. If these attributes are not compatible, then this value
     * will identify the host system on which the attributes are compatible. Software can use this
     * information to determine the line record format for text files etc.
     * 
     * 4.4.2.3 The lower byte indicates the ZIP specification version (the version of this document)
     * supported by the software used to encode the file. The value/10 indicates the major version
     * number, and the value mod 10 is the minor version number.
     */
    int OS_FAT = 0; // MS-DOS and OS/2 (FAT / VFAT / FAT32 file systems)
    int OS_Amiga = 1;
    int OS_OpenVMS = 2;
    int OS_UNIX = 3;
    int OS_VM_CMS = 4;
    int OS_Atari_ST = 5;
    int OS_OS2_HPFS = 6;
    int OS_Mac = 7;
    int OS_Z_System = 8;
    int OS_CP_M = 9;
    int OS_NTFS = 10;
    int OS_MVS = 11; // OS/390 - Z/OS
    int OS_VSE = 12;
    int OS_Acorn_Risc = 13;
    int OS_VFAT = 14;
    int OS_alt_MVS = 15;
    int OS_BeOS = 16;
    int OS_Tandem = 17;
    int OS_OS_400 = 18;
    int OS_OS_X = 19; // Darwin

    /**
     * Version Needed
     * 
     * 4.4.3.1 The minimum supported ZIP specification version needed to extract the file, mapped as
     * above. This value is based on the specific format features a ZIP program MUST support to be
     * able to extract the file. If multiple features are applied to a file, the minimum version
     * MUST be set to the feature having the highest value. New features or feature changes
     * affecting the published format specification will be implemented using higher version numbers
     * than the last published value to avoid conflict.
     */
    ;

    int VN_Default = 10;
    int VN_VolumeLabel = 11;
    int VN_FileIsDir = 20;
    int VN_Deflate = 20;
    int VN_Encrypted = 20;
    int VN_Deflate64 = 21;
    int VN_Implode = 25;
    int VN_PatchDataSet = 27;

    /**
     * When using ZIP64 extensions, the corresponding value in the zip64 end of central directory
     * record MUST also be set. This field should be set appropriately to indicate whether Version 1
     * or Version 2 format is in use.
     */
    int VN_Zip64 = 45;

    /**
     * Early 7.x (pre-7.2) versions of PKZIP incorrectly set the version needed to extract for BZIP2
     * compression to be 50 when it should have been 46.
     */
    int VN_Bzip2 = 46;
    int VN_DES = 50;
    int VN_3DES = 50;
    int VN_RC2 = 50;
    int VN_AES = 51;

    /**
     * Refer to the section on Strong Encryption Specification for additional information regarding
     * RC2 corrections.
     */
    int VN_RC2_Corrected = 51;
    int VN_RC2_64 = 52;

    /**
     * Certificate encryption using non-OAEP key wrapping is the intended mode of operation for all
     * versions beginning with 6.1. Support for OAEP key wrapping MUST only be used for backward
     * compatibility when sending ZIP files to be opened by versions of PKZIP older than 6.1 (5.0 or
     * 6.0).
     */
    int VN_non_OAEP_Key = 61;

    int VN_EncryptedCen = 62;
    int VN_LAMZ = 63;

    /**
     * Files compressed using PPMd MUST set the version needed to extract field to 6.3, however, not
     * all ZIP programs enforce this and may be unable to decompress data files compressed using
     * PPMd if this value is set.
     */
    int VN_PPMd_plus = 63;
    int VN_Blowfish = 63;
    int VN_Twofish = 63;

    /** If set, indicates that the file is encrypted. */
    int F_ENC = 1;

    /**
     * (For Method 6 - Imploding) Bit 1: If the compression method used was type 6, Imploding, then
     * this bit, if set, indicates an 8K sliding dictionary was used. If clear, then a 4K sliding
     * dictionary was used.
     */
    int F_IMPLODE_8K = 2;

    /**
     * (For Method 6 - Imploding) Bit 2: If the compression method used was type 6, Imploding, then
     * this bit, if set, indicates 3 Shannon-Fano trees were used to encode the sliding dictionary
     * output. If clear, then 2 Shannon-Fano trees were used.
     */
    int F_IMPLODE_3TREES = 4;

    /**
     * (For Methods 8 and 9 - Deflating)
     * 
     * <pre>
     *         Bit 2  Bit 1
     *           0      0    Normal (-en) compression option was used.
     *           0      1    Maximum (-exx/-ex) compression option was used.
     *           1      0    Fast (-ef) compression option was used.
     *           1      1    Super Fast (-es) compression option was used.
     * </pre>
     */
    int F_DEFLATE_LEVEL_MASK = 6;

    /**
     * (For Method 14 - LZMA) Bit 1: If the compression method used was type 14, LZMA, then this
     * bit, if set, indicates an end-of-stream (EOS) marker is used to mark the end of the
     * compressed data stream. If clear, then an EOS marker is not present and the compressed data
     * size must be known to extract.
     */
    int F_LZMA_EOS = 2;

    /**
     * Bit 3: If this bit is set, the fields crc-32, compressed size and uncompressed size are set
     * to zero in the local header. The correct values are put in the data descriptor immediately
     * following the compressed data. (Note: PKZIP version 2.04g for DOS only recognizes this bit
     * for method 8 compression, newer versions of PKZIP recognize this bit for any compression
     * method.)
     */
    int F_DATA_DESCRIPTOR = 8;

    /** Bit 4: Reserved for use with method 8, for enhanced deflating. */
    int F_DEFLATE_ENH = 1 << 4;

    /**
     * Bit 5: If this bit is set, this indicates that the file is compressed patched data. (Note:
     * Requires PKZIP version 2.70 or greater)
     */
    int F_PATCH = 1 << 5;

    /**
     * Bit 6: Strong encryption. If this bit is set, you MUST set the version needed to extract
     * value to at least 50 and you MUST also set bit 0. If AES encryption is used, the version
     * needed to extract value MUST be at least 51. See the section describing the Strong Encryption
     * Specification for details. Refer to the section in this document entitled
     * "Incorporating PKWARE Proprietary Technology into Your Product" for more information.
     */
    int F_ENC_STRONG = 1 << 6;

    /**
     * Bit 11: Language encoding flag (EFS). If this bit is set, the filename and comment fields for
     * this file MUST be encoded using UTF-8. (see APPENDIX D)
     */
    int F_EFS = 1 << 11;
    int F_UTF8 = F_EFS;

    /**
     * Bit 13: Set when encrypting the Central Directory to indicate selected data values in the
     * Local Header are masked to hide their actual values. See the section describing the Strong
     * Encryption Specification for details. Refer to the section in this document entitled
     * "Incorporating PKWARE Proprietary Technology into Your Product" for more information.
     */
    int F_ENC_CEN_DIR = 1 << 13;

    int M_STORE = 0;
    int M_SHRUNK = 1;
    int M_REDUCE_1 = 2;
    int M_REDUCE_2 = 3;
    int M_REDUCE_3 = 4;
    int M_REDUCE_4 = 5;
    int M_IMPLODE = 6;
    int M_TOKEN_ALG = 7;
    int M_DEFLATE = 8;
    int M_DEFLATE64 = 9;
    int M_TERSE_OLD = 10;
    int M_BZIP2 = 12;
    int M_LZMA = 14;
    int M_TERSE_NEW = 18;
    int M_LZ77 = 19;
    int M_WAVPACK = 97;
    int M_PPMD = 98;

    /**
     * The CRC-32 algorithm was generously contributed by David Schwaderer and can be found in his
     * excellent book "C Programmers Guide to NetBIOS" published by Howard W. Sams & Co. Inc. The
     * 'magic number' for the CRC is 0xdebb20e3. The proper CRC pre and post conditioning is used,
     * meaning that the CRC register is pre-conditioned with all ones (a starting value of
     * 0xffffffff) and the value is post-conditioned by taking the one's complement of the CRC
     * residual. If bit 3 of the general purpose flag is set, this field is set to zero in the local
     * header and the correct value is put in the data descriptor and in the central directory. When
     * encrypting the central directory, if the local header is not in ZIP64 format and general
     * purpose bit flag 13 is set indicating masking, the value stored in the Local Header will be
     * zero.
     */
    int CRC32_MAGIC = 0xdebb20e3;

    /**
     * The size of the file compressed (4.4.8) and uncompressed, (4.4.9) respectively. When a
     * decryption header is present it will be placed in front of the file data and the value of the
     * compressed file size will include the bytes of the decryption header. If bit 3 of the general
     * purpose bit flag is set, these fields are set to zero in the local header and the correct
     * values are put in the data descriptor and in the central directory. If an archive is in ZIP64
     * format and the value in this field is 0xFFFFFFFF, the size will be in the corresponding 8
     * byte ZIP64 extended information extra field. When encrypting the central directory, if the
     * local header is not in ZIP64 format and general purpose bit flag 13 is set indicating
     * masking, the value stored for the uncompressed size in the Local Header will be zero.
     */
    int SIZE_MAGIC = 0xffffffff;

    /**
     * Internal File Attributes:
     * 
     * 4.4.14.1 The lowest bit of this field indicates, if set, that the file is apparently an ASCII
     * or text file. If not set, that the file apparently contains binary data. The remaining bits
     * are unused in version 1.0.
     */
    int IFA_ASCII = 1;

    /**
     * Internal File Attributes:
     * 
     * 4.4.14.2 The 0x0002 bit of this field indicates, if set, that a 4 byte variable record length
     * control field precedes each logical record indicating the length of the record. The record
     * length control field is stored in little-endian byte order. This flag is independent of text
     * control characters, and if used in conjunction with text data, includes any control
     * characters in the total length of the record. This value is provided for mainframe data
     * transfer support.
     */
    int IFA_VRL = 2;

    /**
     * External File Attributes:
     * 
     * The mapping of the external attributes is host-system dependent (see 'version made by'). For
     * MS-DOS, the low order byte is the MS-DOS directory attribute byte. If input came from
     * standard input, this field is set to zero.
     */
    int EFA_DOSATTR_MASK = 0x00ff;

}
