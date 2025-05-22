package net.bodz.bas.make.reflect;

public class InjectNameOptions {

    boolean qualified;
    boolean fullQualified;
    boolean includeNonPublicFields;

    public boolean isQualified() {
        return qualified;
    }

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
        if (!qualified)
            fullQualified = false;
    }

    public boolean isFullQualified() {
        return fullQualified;
    }

    public void setFullQualified(boolean fullQualified) {
        this.fullQualified = fullQualified;
        if (fullQualified)
            qualified = true;
    }

    public boolean isIncludeNonPublicFields() {
        return includeNonPublicFields;
    }

    public void setIncludeNonPublicFields(boolean includeNonPublicFields) {
        this.includeNonPublicFields = includeNonPublicFields;
    }

}
