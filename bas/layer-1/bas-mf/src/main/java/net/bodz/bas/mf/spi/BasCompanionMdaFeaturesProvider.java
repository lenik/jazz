package net.bodz.bas.mf.spi;

import net.bodz.bas.BasVersion;
import net.bodz.bas.c.java.lang.IntegerMdaFeatures;

public class BasCompanionMdaFeaturesProvider
        extends FriendMdaFeaturesProvider {

    static final String basPackageName;
    static final String basCompanionPackageName;
    static final boolean flatten;

    static {
        basPackageName = BasVersion.class.getPackage().getName();

        String cJavaLangPackage = IntegerMdaFeatures.class.getPackage().getName();
        if (cJavaLangPackage.endsWith(".java.lang")) {
            basCompanionPackageName = cJavaLangPackage.substring(0, cJavaLangPackage.length() - 10);
            flatten = false;
        } else {
            basCompanionPackageName = cJavaLangPackage;
            flatten = true;
        }
    }

    public BasCompanionMdaFeaturesProvider() {
        super(basCompanionPackageName, BuiltinProviderOrder.basFriend.getPriority(), flatten);
    }

}
