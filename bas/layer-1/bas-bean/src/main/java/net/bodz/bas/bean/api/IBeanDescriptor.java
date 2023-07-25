package net.bodz.bas.bean.api;

public interface IBeanDescriptor
        extends
            IFeatureDescriptor {

    Class<?> getBeanClass();

    Class<?> getCustomizerClass();

}