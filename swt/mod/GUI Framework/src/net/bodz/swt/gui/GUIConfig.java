package net.bodz.swt.gui;

import net.bodz.bas.cli.CLIConfig;

public class GUIConfig {

    static {
        CLIConfig.conds.setAlias("bodz_swt", BasicGUI.class.getName());
    }

}
