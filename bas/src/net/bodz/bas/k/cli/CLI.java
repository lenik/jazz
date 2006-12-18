package net.bodz.bas.k.cli;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CLI {

    String[] alias();

    /** 要求 CLIData 中必须有对应项 */
    boolean required() default false;

    String description();

    int flags() default 0;

    /** boolean 类型允许使用 --no-xxx 实现禁用 */
    int INVERSABLE = 1;

}
