package net.bodz.bas.t.set;

public class QmiTaggedSetTest
        extends AbstractTaggedSetTest {

    public QmiTaggedSetTest()
            throws ReflectiveOperationException {
        super();
    }

    @Override
    protected TaggedSet<Object> createTaggedSet() {
        return new QmiTaggedSet<>();
    }

}
