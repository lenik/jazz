package net.bodz.lily.meta;

import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.GroupDef;
import net.bodz.bas.repr.form.meta.StdGroup;

/**
 * @see StdGroup
 */
@GroupDef
public @interface LilyGroups {

    /** 会计 */
    @Priority(0)
    @interface Accounting {
    }

    /** 颜色管理 */
    @interface ColorManagement {
    }

    /** 通信 */
    @interface Communication {
    }

    /** 定制扩展 */
    @interface CustomExtension {
    }

    /** 包装 */
    @interface Packaging {
    }

    /** 位置 */
    @interface Position {
    }

    /** 交易 */
    @interface Trade {
    }

    /** 运输 */
    @interface Transportation {
    }

    /** 用户交互 */
    @interface UserInteraction {
    }

}
