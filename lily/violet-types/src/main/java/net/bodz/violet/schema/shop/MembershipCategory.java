package net.bodz.violet.schema.shop;

import javax.persistence.Table;

import net.bodz.lily.concrete.CoCategory;

@Table(name = "shopmemcat")
public class MembershipCategory
        extends CoCategory<MembershipCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public MembershipCategory() {
    }

}
