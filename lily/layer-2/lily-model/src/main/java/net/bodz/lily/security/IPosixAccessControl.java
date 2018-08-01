package net.bodz.lily.security;

public interface IPosixAccessControl {

    int getUserId();

    int getGroupId();

    int getAccessMode();

}
