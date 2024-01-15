package net.bodz.lily.criteria;

public interface ICriteriaBuilder<end_target, self_t extends ICriteriaBuilder<end_target, self_t>> {

    // self_t and(self_t other);

    self_t or();

    self_t not();

    // self_t begin();

    end_target end();

}
