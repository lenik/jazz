package net.bodz.lily.security;

public interface IUnixLikeAccessControl {

    Integer getOwnerUserId();

    Integer getOwnerGroupId();

    int getAccessMode();

}
