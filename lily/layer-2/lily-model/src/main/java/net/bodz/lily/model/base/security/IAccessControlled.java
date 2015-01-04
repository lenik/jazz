package net.bodz.lily.model.base.security;

/**
 * @label Access Control
 * @label.zh 访问控制
 */
public interface IAccessControlled
        extends IPosixAccessControl {

    int getAcl();

}
