package net.bodz.lily.gen.model.java;

import net.bodz.bas.c.string.StringPart;
import net.bodz.lily.gen.JavaSourceBuilder;

public abstract class FragmentSourceBuilder<model_t>
        extends JavaSourceBuilder<model_t> {

    protected final String mainQName;
    protected final String fragmentQName;

    protected final String mainPackage;
    protected final String mainName;
    protected final String fragmentPackage;
    protected final String fragmentName;

    public FragmentSourceBuilder(String mainQName, String fragmentQName) {
        super(StringPart.beforeLast(fragmentQName, '.'));
        this.mainQName = mainQName;
        this.fragmentQName = fragmentQName;

        mainPackage = StringPart.beforeLast(mainQName, '.');
        mainName = StringPart.afterLast(mainQName, '.');
        fragmentPackage = StringPart.beforeLast(fragmentQName, '.');
        fragmentName = StringPart.afterLast(fragmentQName, '.');
    }

}
