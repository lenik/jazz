package net.bodz.art.obfuz.pm;

public interface Section {

    int USER     = 0;
    int CHECKSUM = 1;
    int PRECRYPT = 2;
    int PCL      = 3;

    int getType();

    SectionMetaData getMetaData();

    void lock(KeyBytes key) throws ProtectException;

    void unlock(KeyBytes KEY) throws ProtectException;

}
