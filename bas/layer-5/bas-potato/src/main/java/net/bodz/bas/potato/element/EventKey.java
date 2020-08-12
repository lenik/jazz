package net.bodz.bas.potato.element;

import com.googlecode.openbeans.EventSetDescriptor;
import java.io.Serializable;

import net.bodz.bas.c.object.Nullables;

public class EventKey
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    public EventKey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isMatched(EventSetDescriptor eventSetDescriptor) {
        String eventSetName = eventSetDescriptor.getName();
        return eventSetName.equals(name);
    }

    @Override
    public int hashCode() {
        int result = 31;
        if (name != null)
            result += name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventKey) {
            EventKey o = (EventKey) obj;
            return Nullables.equals(name, o.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return "event=" + name;
    }

}
