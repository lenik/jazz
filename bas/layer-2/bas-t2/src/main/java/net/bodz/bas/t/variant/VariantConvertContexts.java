package net.bodz.bas.t.variant;

public class VariantConvertContexts {

    public static IVariantConvertContext getContext() {
        return global;
    }

    static final IVariantConvertContext global = new DefaultVariantConvertContext();

}
