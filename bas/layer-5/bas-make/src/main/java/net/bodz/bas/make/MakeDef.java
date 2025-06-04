package net.bodz.bas.make;

import java.math.BigDecimal;

import net.bodz.bas.make.tdk.MutableTypeDerivedKeyList;
import net.bodz.bas.meta.decl.NotNull;

public abstract class MakeDef {

    protected final IMakeSession session;
    protected final IMakeRules rules;

    public MakeDef(@NotNull IMakeSession session) {
        this.session = session;
        this.rules = session.getRules();

        defineTypeDerivedList(BigDecimal.class).build();
    }

    public <K, E> MutableTypeDerivedKeyList.Builder<K, E> defineTypeDerivedList(Class<E> elementType) {
        return MutableTypeDerivedKeyList.builder();
    }

}
