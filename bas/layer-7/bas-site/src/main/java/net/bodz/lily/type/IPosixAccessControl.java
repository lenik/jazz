package net.bodz.lily.type;

public interface IPosixAccessControl {

    int getOwnerId();

    int getGroupId();

    int getAccessMode();

}
