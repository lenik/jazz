package net.bodz.bas.traits.provider.builtin;

import net.bodz.bas.lang.IQueryable;

public class BasFriendTraitsProvider
        extends FriendTraitsProvider {

    static final String basPackage;
    static final boolean basFlatten;

    static {
        String basLangPackage = IQueryable.class.getPackage().getName();
        if (basLangPackage.endsWith(".lang")) {
            // The normal package hierachical
            basPackage = basLangPackage.substring(0, basLangPackage.length() - 5);
            basFlatten = false;
        } else {
            // The package hierachical is flattened.
            basPackage = basLangPackage;
            basFlatten = true;
        }
    }

    public BasFriendTraitsProvider() {
        super(basPackage, BuiltinProviderOrder.basFriend.getPriority(), basFlatten);
    }

}
