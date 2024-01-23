package net.bodz.lily.security.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class CoPrincipalCriteriaBuilder<self_t extends CoPrincipalCriteriaBuilder<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");
    public final StringField name = string("name");

//    public String getName1() {
//        String s = getName();
//        if (s != null)
//            s = StringUtil.enc1(s);
//        return s;
//    }

}
