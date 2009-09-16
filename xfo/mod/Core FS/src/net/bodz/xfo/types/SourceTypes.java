package net.bodz.xfo.types;

import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;

public class SourceTypes {

    /**
     * Extension -> {@link SourceType}
     */
    private static TextMap<SourceType> types;
    static {
        types = new TreeTextMap<SourceType>();
        // types.add(".sh", )
    }

    public static void add(String extension, SourceType type) {
        if (extension == null)
            throw new NullPointerException("extension");
        if (type == null)
            throw new NullPointerException("type");
        types.put(extension, type);
    }

    public static void remove(String extension) {
        types.remove(extension);
    }

    public static SourceType getByExtension(String extension) {
        SourceType sourceType = types.get(extension);
        return sourceType;
    }

}
