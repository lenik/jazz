package net.bodz.lily.entity;

public interface IId<Id> {

    Class<Id> idType();

    Id getId();

    void setId(Id id);

    class fn
            extends EntityFn {
    }

}
