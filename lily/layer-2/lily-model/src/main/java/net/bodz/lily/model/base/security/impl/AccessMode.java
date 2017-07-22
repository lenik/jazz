package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefMetadata;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocAware;

public class AccessMode
        extends Predef<AccessMode, Integer> {

    private static final long serialVersionUID = 1L;

    public static final PredefMetadata<AccessMode, Integer> meta = PredefMetadata.forClass(AccessMode.class);

    AccessMode(int key, String name) {
        super(key, name, meta);
    }

    /**
     * @label.zh.cn 公开
     */
    public static final AccessMode _666 = new AccessMode(0666, "666");

    /**
     * @label.zh.cn 协同发布
     */
    public static final AccessMode _664 = new AccessMode(0664, "664");

    /**
     * @label.zh.cn 发布
     */
    public static final AccessMode _644 = new AccessMode(0644, "644");

    /**
     * @label.zh.cn 协同管理
     */
    public static final AccessMode _660 = new AccessMode(0660, "660");

    /**
     * @label.zh.cn 共享
     */
    public static final AccessMode _640 = new AccessMode(0640, "640");

    /**
     * @label.zh.cn 私有
     */
    public static final AccessMode _600 = new AccessMode(0600, "600");

    /**
     * @label.zh.cn 发布/锁定
     */
    public static final AccessMode _444 = new AccessMode(0444, "444");

    /**
     * @label.zh.cn 共享/锁定
     */
    public static final AccessMode _440 = new AccessMode(0440, "440");

    /**
     * @label.zh.cn 私有/锁定
     */
    public static final AccessMode _400 = new AccessMode(0400, "400");

    static {
        IXjdocAware.fn.injectFields(AccessMode.class, false);
    }

}
