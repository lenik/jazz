package net.bodz.lily.vapp.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.vapp.VApp;

/**
* @label VApp
*/
@ObjectType(VApp.class)
public class VAppIndex
        extends CoIndex<VApp, VAppCriteriaBuilder> {

    public VAppIndex() {
    }

}
