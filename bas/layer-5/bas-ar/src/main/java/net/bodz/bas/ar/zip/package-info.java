/**
 * New Zip Archive Support
 */
package net.bodz.bas.ar.zip;

class package_info {

    /**
     * Problems:
     * 
     * Local file must be used, stream/byte-array aren't supported.
     */
    java.util.zip.ZipFile zipFile;

    /**
     * Problems:
     * 
     * <ul>
     * <li>Extra field like {@link net.bodz.bas.ar.zip.xf3.XF_InfoZip_UnicodePath} is not supported
     * at all. User must specify the encoding for the pathname in the local file header.
     * </ul>
     */
    java.util.zip.ZipInputStream zipIn;

}
