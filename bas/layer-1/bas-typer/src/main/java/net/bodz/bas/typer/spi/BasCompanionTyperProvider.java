package net.bodz.bas.typer.spi;

import net.bodz.bas.BasVersion;
import net.bodz.bas.c.java.lang.IntegerTypers;

public class BasCompanionTyperProvider
        extends FriendTyperProvider {

    static final String basPackageName;
    static final String basCompanionPackageName;
    static final boolean flatten;

    static {
        basPackageName = BasVersion.class.getPackage().getName();

        String cJavaLangPackage = IntegerTypers.class.getPackage().getName();
        if (cJavaLangPackage.endsWith(".java.lang")) {
            basCompanionPackageName = cJavaLangPackage.substring(0, cJavaLangPackage.length() - 10);
            flatten = false;
        } else {
            basCompanionPackageName = cJavaLangPackage;
            flatten = true;
        }
    }

    public BasCompanionTyperProvider() {
        super(basCompanionPackageName, BuiltinProviderOrder.basFriend.getPriority(), flatten);
    }

}
