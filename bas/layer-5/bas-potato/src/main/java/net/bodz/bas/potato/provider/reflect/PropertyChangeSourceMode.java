package net.bodz.bas.potato.provider.reflect;

import net.bodz.bas.t.event.IPropertyChangeSource;

public enum PropertyChangeSourceMode {

    /**
     * It doesn't support the property change event.
     */
    UNSUPPORTED,

    /**
     * It implemented the {@link IPropertyChangeSource} interface.
     */
    INTERFACE,

    /**
     * It uses the <i>add/remove*Listener</i> bean naming convention.
     */
    BEAN,

}
