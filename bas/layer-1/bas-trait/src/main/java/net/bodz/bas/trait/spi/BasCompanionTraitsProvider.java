package net.bodz.bas.trait.spi;

import net.bodz.bas.BasVersion;
import net.bodz.bas.c.java.lang.IntegerTraits;

public class BasCompanionTraitsProvider
        extends FriendTraitsProvider {

    static final String basPackageName;
    static final String basCompanionPackageName;
    static final boolean flatten;

    static {
        basPackageName = BasVersion.class.getPackage().getName();

        String cJavaLangPackage = IntegerTraits.class.getPackage().getName();
        if (cJavaLangPackage.endsWith(".java.lang")) {
            basCompanionPackageName = cJavaLangPackage.substring(0, cJavaLangPackage.length() - 10);
            flatten = false;
        } else {
            basCompanionPackageName = cJavaLangPackage;
            flatten = true;
        }
    }

    public BasCompanionTraitsProvider() {
        super(basCompanionPackageName, BuiltinProviderOrder.basFriend.getPriority(), flatten);
    }

}
