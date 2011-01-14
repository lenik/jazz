package net.bodz.bas.traits.provider.builtin;

import net.bodz.bas.traits.provider.ITraitsProvider;

interface BuiltinProviderOrder {

    int PRIORITY_COMPANION = ITraitsProvider.PRIORITY_HIGH;
    int PRIORITY_GETMETHOD = ITraitsProvider.PRIORITY_HIGH + 10;
    int PRIORITY_ANNOTATION = ITraitsProvider.PRIORITY_HIGH + 20;
    int PRIORITY_CAST = ITraitsProvider.PRIORITY_LOW;

}
