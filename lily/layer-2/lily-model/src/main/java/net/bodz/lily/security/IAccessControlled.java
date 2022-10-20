package net.bodz.lily.security;

/**
 * @label Access Control
 * @label.zh 访问控制
 */
public interface IAccessControlled
        extends
            IPosixAccessControl {

    Integer getAcl();

}
