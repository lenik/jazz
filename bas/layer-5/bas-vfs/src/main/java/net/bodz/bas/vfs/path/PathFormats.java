package net.bodz.bas.vfs.path;

import net.bodz.bas.vfs.util.FeatureOption;

public class PathFormats {

    /**
     * The default path format is generally the same as the request path.
     */
    public static final PathFormat DEFAULT;

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
        DEFAULT = new PathFormat();
        DISPLAY = new PathFormat( //
                new FeatureOption(PathFormat.KEY_DISPLAY_SAFE, true));
        VERBOSE = new PathFormat(//
                new FeatureOption(PathFormat.KEY_DISPLAY_SAFE, false));
    }

}
