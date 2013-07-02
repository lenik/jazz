package net.bodz.swt.viz;

public class SwtViewConfig {

    public String defaultIcon = "/icons/full/obj16/genericvariable_obj.gif";
    public int defaultIconSize = 16;

    public static SwtViewConfig getDefault() {
        return instance;
    }

    static SwtViewConfig instance = new SwtViewConfig();

}
