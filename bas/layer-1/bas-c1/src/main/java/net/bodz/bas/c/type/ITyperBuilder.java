package net.bodz.bas.c.type;

public interface ITyperBuilder {

    Class<?> getFeatureClass();

    Object buildTyper(Class<?> type);

}
