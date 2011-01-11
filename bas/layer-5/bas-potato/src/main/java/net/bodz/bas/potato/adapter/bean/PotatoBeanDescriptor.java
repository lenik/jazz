package net.bodz.bas.potato.adapter.bean;

import java.beans.BeanDescriptor;

import net.bodz.bas.potato.IPotatoType;

public class PotatoBeanDescriptor
        extends BeanDescriptor {

    public PotatoBeanDescriptor(IPotatoType<?> potatoType) {
        super(potatoType.getJavaType());
        FeatureDescriptorUtil.initFeatureDescriptorFromPotatoElement(this, potatoType);
    }

}
