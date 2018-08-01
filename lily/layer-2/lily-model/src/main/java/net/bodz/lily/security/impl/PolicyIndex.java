package net.bodz.lily.security.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;

import net.bodz.lily.security.Policy;
import net.bodz.lily.security.impl.PolicyMask;

@ObjectType(Policy.class)
public class PolicyIndex
        extends CoIndex<Policy, PolicyMask> {

}
