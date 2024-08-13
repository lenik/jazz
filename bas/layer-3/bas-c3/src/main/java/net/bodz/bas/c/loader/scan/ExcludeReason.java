package net.bodz.bas.c.loader.scan;

public enum ExcludeReason {

    Accepted,
    NotIndexed,
    NoNormalClass,
    NoInnerClass,
    NoAbstract,
    NotPublic,
    NoAnnotation,
    NotMaven,
    NoResourceDir,
    BadPath,
    BadEntry,

    ;

    public boolean isAccepted() {
        return this == Accepted;
    }

    public boolean isExcluded() {
        return this != Accepted;
    }

    public ExcludeReason join(ExcludeReason o) {
        return this != Accepted ? this : o;
    }

}
