package net.bodz.lily.entity.type;

import net.bodz.lily.entity.BeanIdentityTypeInfo;

public class ExternalFooIdTypeInfo
        extends BeanIdentityTypeInfo {

    public ExternalFooIdTypeInfo() {
        super(FooId.class);
    }

    public static final ExternalFooIdTypeInfo INSTANCE = new ExternalFooIdTypeInfo();

}
