package net.bodz.jna.win32;

import java.nio.Buffer;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

/**
 * @TestBy Kernel32Test
 */
public interface Kernel32 extends W32API {

    /**
     * The file system preserves the case of file names when it places a name on
     * disk.
     */
    int FILE_CASE_PRESERVED_NAMES    = 0x00000002;

    /** The file system supports case-sensitive file names. */
    int FILE_CASE_SENSITIVE_SEARCH   = 0x00000001;

    /** The file system supports file-based compression. */
    int FILE_FILE_COMPRESSION        = 0x00000010;

    /** The file system supports named streams. */
    int FILE_NAMED_STREAMS           = 0x00040000;

    /**
     * The file system preserves and enforces access control lists (ACL). For
     * example, the NTFS file system preserves and enforces ACLs, and the FAT
     * file system does not.
     */
    int FILE_PERSISTENT_ACLS         = 0x00000008;

    /** The specified volume is read-only. */
    int FILE_READ_ONLY_VOLUME        = 0x00080000;

    /** The volume supports a single sequential write. */
    int FILE_SEQUENTIAL_WRITE_ONCE   = 0x00100000;

    /** The file system supports the Encrypted File System (EFS). */
    int FILE_SUPPORTS_ENCRYPTION     = 0x00020000;

    /** The file system supports object identifiers. */
    int FILE_SUPPORTS_OBJECT_IDS     = 0x00010000;

    /** The file system supports re-parse points. */
    int FILE_SUPPORTS_REPARSE_POINTS = 0x00000080;

    /** The file system supports sparse files. */
    int FILE_SUPPORTS_SPARSE_FILES   = 0x00000040;

    /** The volume supports transactions. */
    int FILE_SUPPORTS_TRANSACTIONS   = 0x00200000;

    /** The file system supports Unicode in file names as they appear on disk. */
    int FILE_UNICODE_ON_DISK         = 0x00000004;

    /**
     * The specified volume is a compressed volume, for example, a DoubleSpace
     * volume.
     */
    int FILE_VOLUME_IS_COMPRESSED    = 0x00008000;

    /** The file system supports disk quotas. */
    int FILE_VOLUME_QUOTAS           = 0x00000020;

    /**
     * @param rootPathName
     *            [in, optional] A pointer to a string that contains the root
     *            directory of the volume to be described.
     * 
     *            If this parameter is NULL, the root of the current directory
     *            is used. A trailing backslash is required. For example, you
     *            specify \\MyServer\MyShare as "\\MyServer\MyShare\", or the C
     *            drive as "C:\".
     * @param volumeNameBuffer
     *            [out] A pointer to a buffer that receives the name of a
     *            specified volume. The maximum buffer size is MAX_PATH+1.
     * @param volumeNameSize
     *            [in] The length of a volume name buffer, in TCHARs. The
     *            maximum buffer size is MAX_PATH+1.
     * 
     *            This parameter is ignored if the volume name buffer is not
     *            supplied.
     * @param volumeSerialNumber
     *            [out, optional] A pointer to a variable that receives the
     *            volume serial number.
     * 
     *            This parameter can be NULL if the serial number is not
     *            required.
     * 
     *            This function returns the volume serial number that the
     *            operating system assigns when a hard disk is formatted. To
     *            programmatically obtain the hard disk's serial number that the
     *            manufacturer assigns, use the Windows Management
     *            Instrumentation (WMI) Win32_PhysicalMedia property
     *            SerialNumber.
     * @param maximumComponentLength
     *            [out, optional] A pointer to a variable that receives the
     *            maximum length, in TCHARs, of a file name component that a
     *            specified file system supports.
     * 
     *            A file name component is the portion of a file name between
     *            backslashes.
     * 
     *            The value that is stored in the variable that
     *            *lpMaximumComponentLength points to is used to indicate that a
     *            specified file system supports long names. For example, for a
     *            FAT file system that supports long names, the function stores
     *            the value 255, rather than the previous 8.3 indicator. Long
     *            names can also be supported on systems that use the NTFS file
     *            system.
     * @param fileSystemFlags
     *            [out, optional] A pointer to a variable that receives flags
     *            associated with the specified file system.
     * 
     *            This parameter can be one or more of the following flags.
     *            However, FS_FILE_COMPRESSION and FS_VOL_IS_COMPRESSED are
     *            mutually exclusive.
     * @param fileSystemNameBuffer
     *            [out] A pointer to a buffer that receives the name of the file
     *            system, for example, the FAT file system or the NTFS file
     *            system. The maximum buffer size is MAX_PATH+1.
     * @param fileSystemNameSize
     *            [in] The length of the file system name buffer, in TCHARs. The
     *            maximum buffer size is MAX_PATH+1. This parameter is ignored
     *            if the file system name buffer is not supplied.
     */
    int GetVolumeInformationA(String rootPathName, Buffer volumeNameBuffer,
            int volumeNameSize, IntByReference volumeSerialNumber,
            IntByReference maximumComponentLength,
            IntByReference fileSystemFlags, Buffer fileSystemNameBuffer,
            int fileSystemNameSize);

    int QueryPerformanceFrequency(LongByReference frequency);

    int QueryPerformanceCounter(LongByReference count);

}
