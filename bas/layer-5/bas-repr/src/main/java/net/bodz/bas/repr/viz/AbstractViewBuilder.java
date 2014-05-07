package net.bodz.bas.repr.viz;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.UiPropertyRefMap;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.ui.dom1.UiValue;

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
    public final Object buildView(Object ctx, IUiRef<T> ref)
            throws ViewBuilderException {
        return buildView(ctx, ref, IOptions.NULL);
    }

    protected static UiPropertyRefMap explode(Object obj) {
        return explode(UiValue.wrap(obj));
    }

    protected static UiPropertyRefMap explode(IRefEntry<?> objRef) {
        UiPropertyRefMap refMap = new UiPropertyRefMap(objRef, false);
        refMap.importProperties();
        return refMap;
    }

}
