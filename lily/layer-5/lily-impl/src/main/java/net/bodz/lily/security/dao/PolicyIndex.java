package net.bodz.lily.security.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.Policy;

/**
* @label Policy
*/
@ObjectType(Policy.class)
public class PolicyIndex
        extends CoIndex<Policy, PolicyMask> {

    public PolicyIndex() {
    }

}
