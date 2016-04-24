package net.bodz.bas.t.set;

public class FullSearchTaggedSetTest
        extends AbstractTaggedSetTest {

    public FullSearchTaggedSetTest()
            throws ReflectiveOperationException {
        super();
    }

    @Override
    protected <T> TaggedSet<T> createTaggedSet() {
        return new FullSearchTaggedSet<T>();
    }

}
