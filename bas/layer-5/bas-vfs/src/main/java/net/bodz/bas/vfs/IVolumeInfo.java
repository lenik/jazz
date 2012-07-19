package net.bodz.bas.vfs;

/**
 * <p>
 * How to get volume info?
 * 
 * If there is volume info, you can get the volume info from file by casting the file to
 * {@link IVolumeInfo}.
 */
public interface IVolumeInfo {

    /**
     * @return non-<code>null</code> volume label. If a volume doesn't have a label, an empty string
     *         should be returned.
     */
    String getLabel();

    /**
     * Get total space in bytes.
     * 
     * @return <code>null</code> if unknown.
     */
    Long getCapacity();

    /**
     * Get available space in bytes.
     * 
     * @return <code>null</code> unknown.
     */
    Long getFreeSpace();

    /**
     * @return Positive (generally 2^n) cluster size, <code>0</code> if the cluster size is unknown.
     */
    int getClusterSize();

}
