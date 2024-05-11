package net.bodz.bas.db.ibatis.sql;

public interface ISelectionRange {

    long UNDEFINED = -1;
    long UNLIMIT = Long.MAX_VALUE;

    long getOffset();

    long getLimit();

    long getCountLimit();

    boolean isLimited();

    boolean isUnlimited();

    boolean isCountLimited();

    boolean isCountUnlimited();

}
