package net.bodz.bas.repr.viz;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.PropertyGUIRefMap;
import net.bodz.bas.rtx.IOptions;

public abstract class AbstractViewBuilder<T>
        implements IViewBuilder<T> {

    static final Logger logger = LoggerFactory.getLogger(AbstractViewBuilder.class);

    private Class<?> valueType;
    private String[] supportedFeatures;

    public AbstractViewBuilder(Class<?> valueClass, String... supportedFeatures) {
        if (valueClass == null)
            throw new NullPointerException("valueClass");
        if (supportedFeatures == null)
            throw new NullPointerException("supportedFeatures");
        if (valueClass == getClass())
            throw new IllegalArgumentException("You should separate the view builder from the class.");
        this.valueType = valueClass;
        this.supportedFeatures = supportedFeatures;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Class<?> getValueType() {
        return valueType;
    }

    @Override
    public String[] getSupportedFeatures() {
        return supportedFeatures;
    }

    @Override
    public final Object buildView(Object ctx, IRefEntry<T> entry)
            throws ViewBuilderException {
        return buildView(ctx, entry, IOptions.NULL);
    }

    protected static PropertyGUIRefMap explode(Object obj) {
        return new PropertyGUIRefMap(obj.getClass(), obj, null);
    }

}
