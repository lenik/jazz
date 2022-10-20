package net.bodz.lily.security;

public interface IPosixAccessControl {

    Integer getUserId();

    Integer getGroupId();

    int getAccessMode();

}
