package net.bodz.bas.make.util;

import java.util.regex.Pattern;

import net.bodz.bas.meta.decl.NotNull;

public class GlobStringPatternForInt
        extends GlobStringPatternTemplate<Integer> {

    public GlobStringPatternForInt(@NotNull Pattern globPattern) {
        super(globPattern);
    }

    public GlobStringPatternForInt(@NotNull String globExpr) {
        super(globExpr);
    }

    @NotNull
    @Override
    public Class<Integer> getDataType() {
        return Integer.class;
    }

}