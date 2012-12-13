package net.bodz.bas.repr.position;

import java.util.Collection;

public interface IObjectOccurrences {

    Object getObject();

    Collection<IObjectOccurrence> getOccurrences();

    boolean isComplete();

}
