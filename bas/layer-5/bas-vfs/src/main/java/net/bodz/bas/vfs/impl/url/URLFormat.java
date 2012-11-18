package net.bodz.bas.vfs.impl.url;

public enum URLFormat {

    /**
     * [ "//" ] path
     */
    plain,

    /**
     * "//" [user "@"] host [":" port "/" path "?" query]
     */
    hostPathQuery,

    /**
     * user "@" host [":" port "?" query]
     */
    hostQuery,

    /**
     * nested "!" inner
     */
    nestedPath,

    ;

}
