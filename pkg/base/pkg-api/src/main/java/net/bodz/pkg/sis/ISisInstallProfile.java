package net.bodz.pkg.sis;

import net.bodz.bas.gui.dom1.IGUIElement;

public interface ISisInstallProfile
        extends IGUIElement {

    void preset(ISisComponent component);

    public static final ISisInstallProfile STANDARD = new Standard();
    public static final ISisInstallProfile MINIMUM = new Minimum();
    public static final ISisInstallProfile MAXIMUM = new Maximum();
    public static final ISisInstallProfile CUSTOM = new Custom();
    public static final ISisInstallProfile DEFAULT = STANDARD;

}

/**
 * Installs most recommended components
 * 
 * @label Standard
 */
class Standard
        extends AbstractSisInstallProfile {

    @Override
    public void preset(ISisComponent component) {
        int verboseLevel = component.getVerboseLevel();
        component.setSelected(verboseLevel <= PUBLIC_LEVEL);
    }

}

/**
 * Only installs the required components
 * 
 * @label Minimum
 */
class Minimum
        extends AbstractSisInstallProfile {

    @Override
    public void preset(ISisComponent component) {
        int verboseLevel = component.getVerboseLevel();
        component.setVisible(verboseLevel <= PREFERRED_LEVEL);
    }

}

/**
 * Install all components
 * 
 * @label Maximum
 */
class Maximum
        extends AbstractSisInstallProfile {

    @Override
    public void preset(ISisComponent component) {
        int verboseLevel = component.getVerboseLevel();
        component.setSelected(verboseLevel <= EXTENDEDLEVEL);
    }

}

/**
 * Custom components to satisfy what you need
 * 
 * @label Custom
 */
class Custom
        extends AbstractSisInstallProfile {

    @Override
    public void preset(ISisComponent component) {
        int verboseLevel = component.getVerboseLevel();
        component.setSelected(verboseLevel <= PUBLIC_LEVEL);
    }

}