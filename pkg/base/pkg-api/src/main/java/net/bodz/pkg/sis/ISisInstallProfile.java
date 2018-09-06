package net.bodz.pkg.sis;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.ui.dom1.IUiElement;

public interface ISisInstallProfile
        extends IUiElement {

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
        int detailLevel = component.getDetailLevel();
        component.setSelected(detailLevel <= DetailLevel.NORMAL);
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
        int detailLevel = component.getDetailLevel();
        component.setVisible(detailLevel <= DetailLevel.CRITICAL);
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
        int detailLevel = component.getDetailLevel();
        component.setSelected(detailLevel < DetailLevel.EXPERT);
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
        int detailLevel = component.getDetailLevel();
        component.setSelected(detailLevel <= DetailLevel.NORMAL);
    }

}