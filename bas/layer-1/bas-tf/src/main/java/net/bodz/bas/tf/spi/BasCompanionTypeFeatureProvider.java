package net.bodz.bas.tf.spi;

import net.bodz.bas.BasVersion;
import net.bodz.bas.c.java.lang.IntegerTypeFeatures;

public class BasCompanionTypeFeatureProvider
        extends FriendTypeFeatureProvider {

    static final String basPackageName;
    static final String basCompanionPackageName;
    static final boolean flatten;

    static {
        basPackageName = BasVersion.class.getPackage().getName();

        String cJavaLangPackage = IntegerTypeFeatures.class.getPackage().getName();
        if (cJavaLangPackage.endsWith(".java.lang")) {
            basCompanionPackageName = cJavaLangPackage.substring(0, cJavaLangPackage.length() - 10);
            flatten = false;
        } else {
            basCompanionPackageName = cJavaLangPackage;
            flatten = true;
        }
    }

    public BasCompanionTypeFeatureProvider() {
        super(basCompanionPackageName, BuiltinProviderOrder.basFriend.getPriority(), flatten);
    }

}
