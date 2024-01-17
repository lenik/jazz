package net.bodz.violet.store.dao;

import net.bodz.lily.template.CoCategoryCriteriaBuilder;

/**
 * @see net.bodz.violet.store.StoreCategory
 */
public class StoreCategoryCriteriaBuilder
        extends CoCategoryCriteriaBuilder {

    public static StoreCategoryCriteriaBuilder below(int maxDepth) {
        StoreCategoryCriteriaBuilder mask = new StoreCategoryCriteriaBuilder();
        mask.maxDepth = 1;
        return mask;
    }

}
