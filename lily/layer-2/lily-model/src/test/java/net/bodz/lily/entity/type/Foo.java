package net.bodz.lily.entity.type;

import net.bodz.lily.entity.IId;

public class Foo
        implements
            IId<FooId> {

    FooId id = new FooId();

    @Override
    public Class<FooId> idType() {
        return FooId.class;
    }

    @Override
    public FooId id() {
        return id;
    }

    @Override
    public void id(FooId id) {
        this.id = id;
    }

}