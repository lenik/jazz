package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public interface ICriteriaBuilder<self_t extends ICriteriaBuilder<self_t>> {

    ICriterion get();

    // self_t and(self_t other);

    self_t or();

    self_t not();

    // self_t begin();

    self_t end();

}
