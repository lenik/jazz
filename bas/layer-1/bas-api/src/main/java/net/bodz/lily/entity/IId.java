package net.bodz.lily.entity;

public interface IId<Id> {

    Class<Id> idType();

    Id id();

    void id(Id id);

}
