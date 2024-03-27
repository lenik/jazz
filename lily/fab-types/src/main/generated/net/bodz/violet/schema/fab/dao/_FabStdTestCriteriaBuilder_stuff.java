package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;

public class _FabStdTestCriteriaBuilder_stuff<self_t extends _FabStdTestCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

    public final IntegerField refCount = integer("nref");

}
