package net.bodz.bas.bean;

import net.bodz.bas.arch.context.ContextLocal;

public class BeanCLG {

    public static ContextLocal<IBeanPreference> preferences;

    static {
        // preferences =new ContextLocal<IBeanPreference>(defaultBeanPreference);
    }

}
