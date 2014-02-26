package net.bodz.bas.repr.viz;

import net.bodz.bas.c.object.IEmptyConsts;
import net.bodz.bas.potato.ref.IRefEntries;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.InstanceProperties;
import net.bodz.bas.rtx.IOptions;

public abstract class AbstractViewBuilder<T>
        implements IViewBuilder<T> {

    private Class<?>[] supportedClasses;

    public AbstractViewBuilder(Class<?>... supportedClasses) {
        this.supportedClasses = supportedClasses;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Class<?>[] getSupportedClasses() {
        return supportedClasses;
    }

    @Override
    public String[] getSupportedFeatures() {
        return IEmptyConsts.emptyStringArray;
    }

    @Override
    public final Object buildView(Object ctx, IRefEntry<T> entry)
            throws ViewBuilderException {
        return buildView(ctx, entry, IOptions.NULL);
    }

    protected static IRefEntries properties(Object obj) {
        return new InstanceProperties(obj);
    }

}
