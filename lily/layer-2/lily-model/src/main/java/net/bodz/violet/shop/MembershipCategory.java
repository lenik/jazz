package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.template.CoCategory;

@Table(name = "shopmemcat")
public class MembershipCategory
        extends CoCategory<MembershipCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public MembershipCategory() {
    }

}
