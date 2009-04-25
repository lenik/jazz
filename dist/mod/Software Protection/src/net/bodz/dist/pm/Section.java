package net.bodz.dist.pm;

public interface Section {

    int USER     = 0;
    int CHECKSUM = 1;
    int PRECRYPT = 2;
    int PCL      = 3;

    int getType();

    SectionMetaData getMetaData();

    void lock(Key key) throws ProtectException;

    void unlock(Key KEY) throws ProtectException;

}
