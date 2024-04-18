package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public interface ICriteriaBuilder<This> {

    ICriterion get();

    // self_t and(self_t other);

    // default This or() { return orOther(); }

    This aboveOr();

    This beginOr();

    This not();

    // self_t begin();

    This end();

    FieldCriterionBuilder<?, ?, Object> getField(String name);

}
