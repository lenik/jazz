package net.bodz.bas.types.set;

public enum SetRelation {

    EMPTY, // 0
    SAME, // SHARE
    EMPTY_MORE, // MORE
    SHARE_MORE, // MORE | SHARE
    EMPTY_LESS, // LESS
    SHARE_LESS, // LESS | SHARE
    ISOLATED, // LESS | MORE
    CUT, // LESS | MORE | SHARE
    ;

    static final int BIT_SHARE = 1;
    static final int BIT_MORE = 2;
    static final int BIT_LESS = 4;

    public boolean isSuperset() {
        switch (this) {
        case EMPTY:
        case SAME:
        case EMPTY_MORE:
        case SHARE_MORE:
            return true;
        }
        return false;
    }

    public boolean isTrueSuperset() {
        return this == EMPTY_MORE || this == SHARE_MORE;
    }

    public boolean isSubset() {
        switch (this) {
        case EMPTY:
        case SAME:
        case EMPTY_LESS:
        case SHARE_LESS:
            return true;
        }
        return false;
    }

    public boolean isTrueSubset() {
        return this == EMPTY_LESS || this == SHARE_LESS;
    }

    public boolean hasShare() {
        return (ordinal() & BIT_SHARE) != 0;
    }

    public boolean hasMore() {
        return (ordinal() & BIT_MORE) != 0;
    }

    public boolean hasLess() {
        return (ordinal() & BIT_LESS) != 0;
    }

    public boolean equals() {
        return this == EMPTY || this == SAME;
    }

    public SetRelation inverse() {
        // swap BIT_MORE x BIT_LESS
        int bits = ordinal();
        int bitLess = (bits & BIT_MORE) << 1;
        int bitMore = (bits & BIT_LESS) >> 1;
        bits = (bits & ~(BIT_MORE | BIT_LESS)) | bitMore | bitLess;
        return values()[bits];
    }

}
