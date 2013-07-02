package net.bodz.swt.viz.grid;

import net.bodz.swt.viz.SwtViewConfig;

public class GridConfig
        extends SwtViewConfig {

    public int margin = 3;

    public static GridConfig getDefault() {
        return instance;
    }

    static GridConfig instance = new GridConfig();

}
