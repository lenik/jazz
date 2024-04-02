package net.bodz.lily.schema.io.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.io.Storage;

@ForEntityType(Storage.class)
public class _StorageCriteriaBuilder_stuff<self_t extends _StorageCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField name = string("\"name\"");

}
