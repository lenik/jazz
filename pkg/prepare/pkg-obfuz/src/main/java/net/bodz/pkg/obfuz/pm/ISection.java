package net.bodz.pkg.obfuz.pm;

public interface ISection {

    int USER = 0;
    int CHECKSUM = 1;
    int PRECRYPT = 2;
    int PCL = 3;

    int getType();

    SectionMetaData getMetaData();

    void lock(KeyBytes key)
            throws ProtectException;

    void unlock(KeyBytes KEY)
            throws ProtectException;

}
