package net.bodz.bas.t.set;

public class QmiTaggedSetTest
        extends AbstractTaggedSetTest {

    public QmiTaggedSetTest()
            throws ReflectiveOperationException {
        super();
    }

    @Override
    protected <T> TaggedSet<T> createTaggedSet() {
        return new QmiTaggedSet<T>();
    }

}
