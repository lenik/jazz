package net.bodz.bas.log.event;

import net.bodz.bas.log.ILogLayer;
import net.bodz.bas.log.objects.ILogEvent;

public interface ILogListener {

    /**
     * <code>eventType</code> may be one of:
     * <ul>
     * <li> {@link ILogLayer#STDOUT}
     * <li> {@link ILogLayer#ERROR}
     * <li> {@link ILogLayer#WARN}
     * <li> {@link ILogLayer#INFO}
     * <li> {@link ILogLayer#DEBUG}
     * <li> {@link ILogLayer#TRACE}
     * </ul>
     * or other user defined id.
     */
    void log(int eventType, int verboseLevel, Object simpleMessage);

    void log(int eventType, int verboseLevel, ILogEvent event);

}
