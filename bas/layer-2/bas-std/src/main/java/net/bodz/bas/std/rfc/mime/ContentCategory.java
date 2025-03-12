package net.bodz.bas.std.rfc.mime;

import net.bodz.bas.meta.decl.NotNull;

public enum ContentCategory {

    APPLICATION,
    TEXT,
    IMAGE,
    AUDIO,
    VIDEO,
    OTHER,
    ;

    @NotNull
    public static ContentCategory parse(@NotNull String s) {
        switch (s) {
            case "application":
                return APPLICATION;
            case "text":
                return TEXT;
            case "image":
                return IMAGE;
            case "audio":
                return AUDIO;
            case "video":
                return VIDEO;
            default:
                return OTHER;
        }
    }

}
