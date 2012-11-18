package net.bodz.bas.vfs.path;

public class PathFormats {

    /**
     * The representation path format is generally the same as the request path.
     */
    public static final PathFormat REPR;

    /**
     * The display path format looks pretty and suitable for dispaly purpose.
     * <p>
     * However, it maybe not usable as a file path.
     */
    public static final PathFormat DISPLAY;

    /**
     * The verbose path format shows information as much as possible, this includes username,
     * password in a URL, etc.
     */
    public static final PathFormat VERBOSE;

    static {
        REPR = new PathFormat();
        // REPR.setEncodeOptions(PathFormat.ENCODE_ALL);
        REPR.setSpaceMode(PathSpaceMode.asPlus);
        REPR.setDisplaySafe(false);
        REPR.setRange(true);
        REPR.lock();

        DISPLAY = new PathFormat();
        DISPLAY.setSpaceMode(PathSpaceMode.normalized);
        DISPLAY.setDisplaySafe(true);
        DISPLAY.setRange(false);
        DISPLAY.lock();

        VERBOSE = new PathFormat();
        VERBOSE.setSpaceMode(PathSpaceMode.preserved);
        VERBOSE.setDisplaySafe(false);
        VERBOSE.setRange(true);
        VERBOSE.lock();
    }

}
