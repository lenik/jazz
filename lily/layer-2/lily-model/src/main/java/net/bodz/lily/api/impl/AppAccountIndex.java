package net.bodz.lily.api.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;

import net.bodz.lily.api.AppAccount;
import net.bodz.lily.api.impl.AppAccountMask;

@ObjectType(AppAccount.class)
public class AppAccountIndex
        extends CoIndex<AppAccount, AppAccountMask> {

}
