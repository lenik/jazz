package net.bodz.bas.json;

public enum JsonVariantTreeResolveStatus {

    OK,

    INTO_NULL,

    INTO_NONJSON,

    BAD_INDEX,

    INDEX_OOBE,

    NO_KEY,

    ;

    public String getMessage() {
        switch (this) {
        case OK:
        case INTO_NULL:
        case INTO_NONJSON:
        case BAD_INDEX:
        case INDEX_OOBE:
        case NO_KEY:
        default:
        }
        return name();
    }

}
