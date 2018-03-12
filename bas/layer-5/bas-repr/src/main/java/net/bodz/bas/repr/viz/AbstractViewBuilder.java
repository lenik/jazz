package net.bodz.bas.repr.viz;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.ctx.LocalScope;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.ui.dom1.IUiRef;

@LocalScope
public abstract class AbstractViewBuilder<T>
        implements IViewBuilder<T> {

    static final Logger logger = LoggerFactory.getLogger(AbstractViewBuilder.class);

    private Class<?> valueType;

    public AbstractViewBuilder() {
        valueType = TypeParam.infer1(getClass(), AbstractViewBuilder.class, 0);
    }

    public AbstractViewBuilder(Class<?> valueClass) {
        if (valueClass == null)
            throw new NullPointerException("valueClass");
        if (valueClass == getClass())
            throw new IllegalArgumentException("You should separate the view builder from the class.");
        this.valueType = valueClass;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public <_T> _T getAttribute(String name) {
        return null;
    }

    public <_T> _T getAttribute(String name, _T defaultValue) {
        return defaultValue;
    }

    @Override
    public void setAttribute(String name, Object value) {
    }

    @Override
    public Class<?> getValueType() {
        return valueType;
    }

    @Override
    public Object buildViewEnd(IQueryable ctx, Object parent, Object o, IUiRef<T> ref)
            throws ViewBuilderException {
        return o;
    }

}
