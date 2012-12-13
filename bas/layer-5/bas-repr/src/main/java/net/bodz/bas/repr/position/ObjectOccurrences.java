package net.bodz.bas.repr.position;

import java.util.ArrayList;
import java.util.List;

public class ObjectOccurrences
        implements IObjectOccurrences {

    private final Object object;
    private List<IObjectOccurrence> occurrences = new ArrayList<>();
    private boolean complete;

    public ObjectOccurrences(Object object) {
        this.object = object;
    }

    @Override
    public Object getObject() {
        return object;
    }

    @Override
    public List<IObjectOccurrence> getOccurrences() {
        return occurrences;
    }

    public void addOccurrence(IObjectOccurrence occurrence) {
        if (occurrence == null)
            throw new NullPointerException("occurrence");
        occurrences.add(occurrence);
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
