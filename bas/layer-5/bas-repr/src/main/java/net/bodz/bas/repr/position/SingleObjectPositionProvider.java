package net.bodz.bas.repr.position;

public abstract class SingleObjectPositionProvider
        implements IObjectPositionProvider {

    @Override
    public IObjectOccurrences find(Object object, int maxOccurrences) {
        if (maxOccurrences < 0)
            throw new IllegalArgumentException("maxOccurrences is negative: " + maxOccurrences);

        ObjectOccurrences occurrences = new ObjectOccurrences(object);

        IObjectOccurrence occurrence = find(object);
        if (occurrence != null)
            occurrences.addOccurrence(occurrence);

        occurrences.setComplete(true);

        return occurrences;
    }

    protected abstract IObjectOccurrence find(Object object);

}
