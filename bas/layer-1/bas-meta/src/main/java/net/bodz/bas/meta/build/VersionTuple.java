package net.bodz.bas.meta.build;

public class VersionTuple
        extends AbstractVersion {

    private static final long serialVersionUID = 1L;

    final String[] elements;

    public VersionTuple(String... elements) {
        if (elements.length < 2)
            throw new IllegalArgumentException("Too few version elements: at least 2 elements required.");
        this.elements = elements;
    }

    @Override
    public String[] getVersionElements() {
        return elements;
    }

}
