package net.bodz.bas.traits.provider.builtin;

import net.bodz.bas.lang.IQueryable;

public class BasFriendTraitsProvider
        extends FriendTraitsProvider {

    public BasFriendTraitsProvider() {
        super(getBasPackageName(), BuiltinProviderOrder.basFriend.getPriority());
    }

    static String getBasPackageName() {
        String basLangPackage = IQueryable.class.getPackage().getName();
        String basPackage;
        if (basLangPackage.endsWith(".lang"))
            // The normal package hierachical
            basPackage = basLangPackage.substring(0, basLangPackage.length() - 5);
        else
            // The package hierachical is flattened.
            basPackage = basLangPackage;
        return basPackage;
    }

}
