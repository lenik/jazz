package net.bodz.lily.model.base.security;

public interface IAccessControlled
        extends IPosixAccessControl {

    int getAcl();

}
