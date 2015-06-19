package net.bodz.lily.model.sea;

import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;

public abstract class AbstractTextParametric
        implements ITextParametric {

    @Override
    public final void populate(IVariantMap<String> variantMap)
            throws ParseException {
        QVariantMap<String> qMap = new QVariantMap<>(variantMap);
        populate(qMap);
    }

    public final void populate(Map<String, String[]> parameterMap)
            throws ParseException {
        ParameterMapVariantMap variantMap = new ParameterMapVariantMap(parameterMap);
        QVariantMap<String> qMap = new QVariantMap<>(variantMap);
        populate(qMap);
    }

    protected abstract void populate(QVariantMap<String> map)
            throws ParseException;

}
