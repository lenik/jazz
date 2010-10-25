package net.bodz.bas.log.event;

import net.bodz.bas.log.ILogEntry;
import net.bodz.bas.log.ILogComposite;

public interface ILogListener {

    /**
     * <code>eventType</code> may be one of:
     * <ul>
     * <li> {@link ILogComposite#STDOUT}
     * <li> {@link ILogComposite#ERROR}
     * <li> {@link ILogComposite#WARN}
     * <li> {@link ILogComposite#INFO}
     * <li> {@link ILogComposite#DEBUG}
     * <li> {@link ILogComposite#TRACE}
     * </ul>
     * or other user defined id.
     */
    void log(int eventType, int verboseLevel, Object simpleMessage);

    void log(int eventType, int verboseLevel, ILogEntry event);

}
