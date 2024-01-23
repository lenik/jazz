package net.bodz.lily.security;

public interface IAccessMode {

    /** 私密 */
    int M_PRIVATE = 0600;

    /** 共享 */
    int M_SHARED = 0640;

    /** 协作 */
    int M_COOP = 0660;

    /** 公开 */
    int M_PUBLIC = 0644;

    /** 协作公开 */
    int M_PUBLIC_COOP = 0664;

    int getAccessMode();

}
