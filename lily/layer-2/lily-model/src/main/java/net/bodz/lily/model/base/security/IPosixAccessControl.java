package net.bodz.lily.model.base.security;

public interface IPosixAccessControl {

    int getUserId();

    int getGroupId();

    int getAccessMode();

}
