package net.bodz.lily.model.sea;

import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;

public interface ITextParametric {

    void populate(IVariantMap<String> variantMap)
            throws ParseException;

    void populate(Map<String, String[]> parameterMap)
            throws ParseException;

}
